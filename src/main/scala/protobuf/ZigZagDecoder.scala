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

class ZigZagDecoder(bit_width: Int) extends Module {

  val io = IO(new Bundle{
    val input  = Input(UInt(bit_width.W))
    val output = Output(SInt(bit_width.W))
  })

  val left_side = Wire(UInt(bit_width.W))
  val right_side = Wire(UInt(bit_width.W))

  // (n >> 1) ^ (-(n & 1))
  left_side := io.input >> 1
  right_side := -(io.input & 1.U)
  val value = left_side ^ right_side
  io.output := value.asSInt()

  printf("ZigZagDecoder:[%d]=>[%d]\n", io.input, io.output)
}
