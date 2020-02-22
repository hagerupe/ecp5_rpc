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
import util.{Cat, PopCount}

import scala.collection.mutable

object DecodeVarint {

  def apply(in: Vec[UInt]): (UInt, UInt) = {
    val byte_count = in.size
    val output     = Wire(UInt((byte_count * 8).W))
    val bytes_read = Wire(UInt(8.W))

    // Compute if MSB is set for byte
    val bitmask = mutable.ArrayBuffer[Bool]()
    bitmask += 0.B
    for ( i <- byte_count - 1 until 0 by -1) {
      bitmask += !in(in.length - 1  - i)(7)
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
    for (i <- 0 until in.length) {
      val shifted = Wire(UInt(7.W))
      shifted := (in(in.length - 1 - i))
      val mask = bitmask_cmp(i)
      results(i) := Mux(mask, shifted, 0.U)
    }

    // Compute number of mask bits set
    bytes_read := byte_count.U - (byte_count.U - PopCount(bitmask_cmp_int))
    output := Cat(results)

    (output, bytes_read)
  }
}

//noinspection TypeAnnotation
class VarintDecoder(byte_count: Int) extends Module {
  val io = IO(new Bundle{
    val input      = Input(Vec(byte_count, UInt(8.W)))
    val output     = Output(UInt((byte_count * 8).W))
    val bytes_read = Output(UInt(8.W))
  })

  val decoded = DecodeVarint(io.input)
  io.output := decoded._1
  io.bytes_read := decoded._2
}