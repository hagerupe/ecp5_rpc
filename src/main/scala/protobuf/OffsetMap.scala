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
import chisel3.util.Cat;

class OffsetMap(bytes: Int) extends Module {

  val io = IO(new Bundle{
    val input        = Input(Vec(bytes, UInt(8.W)))
    val offset       = Input(UInt(8.W))
    val output       = Output(Vec(bytes, UInt(8.W)))
  })

  for (i <- 0 until bytes) {
    io.output(i) := Cat(io.input) >> ((bytes.U - i.U - 1.U - io.offset) * 8.U)
  }

  printf("OffsetMap:io.input:       %d\n", Cat(io.input))
  printf("OffsetMap:io.output:      %d\n", Cat(io.output))
}
