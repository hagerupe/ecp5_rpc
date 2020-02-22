// Copyright (C) 2020 Erik Hagerup
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

// http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package protobuf;

import chisel3._
import chisel3.util._

class Top(add_bit_width: Int,
          word_byte_ct: Int,
          bytes: Array[Byte]) extends Module {

  val io = IO(new Bundle{
    val in1  = Input(UInt(2.W))
    val in2  = Input(UInt(2.W))
    val out1 = Output(UInt(2.W))
  })

  val producer0 = Wire(Flipped(Decoupled(UInt(2.W))))
  val producer1 = Wire(Flipped(Decoupled(UInt(2.W))))
  val consumer = Wire(Decoupled(UInt(2.W)))

  val arb = Module(new Arbiter(UInt(), 2))
  arb.io.in(0) <> producer0
  arb.io.in(1) <> producer1
  consumer <> arb.io.out

  producer0.bits := 1.U
  producer1.bits := io.in1
  producer0.valid := 0.B
  producer1.valid := io.in2
  consumer.ready := 1.B
  io.out1 := consumer.bits


  val abs_read_addr = RegInit(0.U(add_bit_width.W))

  // Message buffer
  val mem = Mem(math.pow(2, add_bit_width).toInt, Vec(word_byte_ct, UInt(8.W)))
  for (i <- bytes.indices) { mem(i / word_byte_ct)(i % word_byte_ct) := (bytes(i) & 0xFF).asUInt() }

  // Compute memory address from byte index
  val read_add_0 = Wire(UInt(add_bit_width.W))
  val read_add_1 = Wire(UInt(add_bit_width.W))
  read_add_0 := abs_read_addr >> log2Down(word_byte_ct)
  read_add_1 := read_add_0 + 1.U

  printf("Probe:%d\n", read_add_0)

  // Read from both ports, concat result vector
  val mem_rd_0 = Wire(Vec(word_byte_ct, UInt(8.W)))
  val mem_rd_1 = Wire(Vec(word_byte_ct, UInt(8.W)))
  val mem_rd = Wire(Vec(word_byte_ct * 2, UInt(8.W)))
  mem_rd_0 := mem.read(read_add_0)
  mem_rd_1 := mem.read(read_add_1)

  for (i <- 0 until word_byte_ct) {
    mem_rd(i) := mem_rd_0(i)
    mem_rd(i + word_byte_ct) := mem_rd_1(i)
  }

  // Align to byte offset based on absolute address
  val shifted = Wire(Vec(word_byte_ct * 2, UInt(8.W)))
  val map_shift = Module(new OffsetMap(word_byte_ct * 2))
  map_shift.io.input := mem_rd
  map_shift.io.offset := (abs_read_addr % word_byte_ct.U)
  shifted := map_shift.io.output
  val reduced = Wire(Vec(word_byte_ct, UInt(8.W)))
  for (i <- 0 until word_byte_ct) { reduced(i) := shifted(i) }

  printf("Shift:    %d\n", map_shift.io.offset)
  printf("Top:mem:   %x\n", Cat(mem_rd))
  printf("Top:shift: %x\n", Cat(shifted))

  // First key decoder
  val proto_key = ProtobufKey(reduced)

  abs_read_addr := abs_read_addr + proto_key.key_size + proto_key.value_size
}
