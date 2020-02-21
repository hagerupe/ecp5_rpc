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

class KeyDecoder(decode_bytes: Int) extends Module {

  val io = IO(new Bundle{
    val input        = Input(Vec(decode_bytes, UInt(8.W)))
    val wire_type    = Output(UInt(3.W))
    val field_number = Output(UInt((decode_bytes * 8).W))
    val bytes_read   = Output(UInt(8.W))
    val value_size   = Output(UInt(32.W))
  })

  // Decode to integer
  val varint_decode = Module(new VarintDecoder(decode_bytes))
  val raw_key = Wire(UInt((decode_bytes * 8).W))
  varint_decode.io.input := io.input
  raw_key := varint_decode.io.output

  io.wire_type := raw_key
  io.field_number := raw_key >> 3

  val length_varint_decoder = Module(new VarintDecoder(decode_bytes))
  val offset_map = Module(new OffsetMap(decode_bytes))
  offset_map.io.offset := varint_decode.io.bytes_read
  offset_map.io.input := io.input
  length_varint_decoder.io.input := offset_map.io.output

  {
    io.bytes_read := varint_decode.io.bytes_read
    io.value_size := 0.U
    when (0.U === io.wire_type){ // Varint
      io.value_size := length_varint_decoder.io.bytes_read
    }
    when (1.U === io.wire_type) { // 64 bit
      io.value_size := 8.U
    }
    when (2.U === io.wire_type) { // Length delimited
      io.bytes_read := varint_decode.io.output
    }
    when (5.U === io.wire_type) { // 32 bit
      io.value_size := 4.U
    }
  }

  printf("KeyDecoder:io.wire_type: %d\n", io.wire_type)
  printf("KeyDecoder:io.field_number: %d\n", io.field_number)
  printf("KeyDecoder:io.bytes_read: %d\n", io.bytes_read)
  printf("KeyDecoder:io.value_size: %d\n", io.value_size)
}

object KeyDecoderGenerate extends App {
  chisel3.Driver.execute(args, () => new KeyDecoder(8))
}