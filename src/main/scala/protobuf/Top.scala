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
    val wireType = Output(UInt(3.W))
    val fieldNumber = Output(UInt(8.W))
    val keySize = Output(UInt(8.W))
    val valueSize = Output(UInt(16.W))
    val valid = Output(Bool())
  })

  var memory = Module(new UnalignedMemoryController(add_bit_width, word_byte_ct * 8, bytes))
  val abs_read_addr = RegInit(0.U(add_bit_width.W))

  val proto_key = ProtobufKey(memory.io.readData.bits)
  io.wireType    := proto_key.wire_type
  io.fieldNumber := proto_key.field_number
  io.keySize     := proto_key.key_size
  io.valueSize   := proto_key.value_size
  io.valid       := memory.io.readData.valid

  memory.io.readData.ready := 1.B // No backpressure, purely combinatorical

  memory.io.readAddress.noenq()

  when (memory.io.readData.valid) {
    abs_read_addr := abs_read_addr + proto_key.key_size + proto_key.value_size
  }.
  elsewhen(memory.io.readAddress.ready) {
    memory.io.readAddress.enq(abs_read_addr)
  }
}
