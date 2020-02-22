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

class RingBuffer(data_width: Int, address_width: Int) extends Module {

  val io = IO(new Bundle{
    val wr_valid     = Input(Bool())
    val wr_data      = Input(Vec(data_width, UInt(8.W)))

    val rd_increment = Input(UInt(address_width.W))
    val rd_data      = Output(Vec(data_width, UInt(8.W)))

    val wr_add_view  = Output(UInt(address_width.W))
    val rd_add_view  = Output(UInt(address_width.W))
  })

  val mem = SyncReadMem(math.pow(2, address_width).toInt, Vec(data_width, UInt(8.W)))
  val readAddr = RegInit(0.U(address_width.W))
  val writeAddr = RegInit(0.U(address_width.W))
  io.wr_add_view := writeAddr
  io.rd_add_view := readAddr
  readAddr := readAddr + io.rd_increment
  writeAddr := Mux(io.wr_valid, writeAddr + 1.U, writeAddr)

  val mask: Vec[Bool] = Wire(Vec(data_width, Bool()))
  for (i <- 0 until data_width) { mask(i) := io.wr_valid }

  mem.write(writeAddr, io.wr_data, mask)
  io.rd_data := mem.read(readAddr + io.rd_increment)

  printf("RingBuffer:io.wr_add_view: [%d]\n", io.wr_add_view)
  printf("RingBuffer:io.rd_add_view: [%d]\n", io.rd_add_view)
  printf("RingBuffer:io.rd_data(0):  [%d]\n\n", io.rd_data(0))
}
