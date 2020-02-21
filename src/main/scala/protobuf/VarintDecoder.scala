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
package protobuf

import chisel3._

import util.Cat
import scala.collection.mutable

//noinspection TypeAnnotation
class VarintDecoder(byte_count: Int) extends Module {
  val io = IO(new Bundle{
    val input      = Input(Vec(byte_count, UInt(8.W)))
    val output     = Output(UInt((byte_count * 8).W))
    val bytes_read = Output(UInt(8.W))
  })

  // Compute if MSB is set for byte
  val bitmask = mutable.ArrayBuffer[Bool]()
  bitmask += 0.B
  for ( i <- byte_count - 1 until 0 by -1) {
    bitmask += !io.input(io.input.length - 1  - i)(7)
  }
  val bitmask_int = Cat(bitmask)

  // Determine mask for valid bytes
  val bitmask_cmp = mutable.ArrayBuffer[Bool]()
  for (i <- 0 until byte_count) {
    bitmask_cmp += (bitmask_int < math.pow(2, i).toInt.U)
  }
  val bitmask_cmp_int = Cat(bitmask_cmp)

  // Compute if MSB is set for byte
  val results = Wire(Vec(byte_count, UInt(7.W)))
  for (i <- 0 until io.input.length) {
    val shifted = Wire(UInt(7.W))
    shifted := (io.input(io.input.length - 1 - i))
    val mask = bitmask_cmp(i)
    results(i) := Mux(mask, shifted, 0.U)
  }

  // Compute number of mask bits set
  val unset_bit_counter = Module(new UnsetBitCounter(byte_count))
  unset_bit_counter.io.input := bitmask_cmp_int
  io.bytes_read := byte_count.U - unset_bit_counter.io.output
  io.output := Cat(results)

  printf("VarintDecoder:io.input:                    %d\n", Cat(io.input))
  printf("VarintDecoder:bitmask_cmp_int:             %d\n", bitmask_cmp_int)
  printf("VarintDecoder:io.bytes_read:               %d\n", io.bytes_read)
  printf("VarintDecoder:io.output:                   %d\n", io.output)
}