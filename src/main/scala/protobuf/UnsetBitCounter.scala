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

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// Compute number of mask bits set
class UnsetBitCounter(bit_width: Int) extends Module {
  val io = IO(new Bundle{
    val input  = Input(UInt(bit_width.W))
    val output = Output(UInt(chisel3.util.log2Up(bit_width).W))
  })

  val bits_unset: ArrayBuffer[UInt] = mutable.ArrayBuffer[UInt]()
  val bits_unset_wire: UInt = Wire(UInt(chisel3.util.log2Up(bit_width).W))
  bits_unset_wire := 0.U
  bits_unset += bits_unset_wire
  for (i <- 0 until bit_width) {
    bits_unset += Mux(io.input(i), bits_unset(i), bits_unset(i) + 1.U)
  }

  io.output := bits_unset(bit_width)
}
