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
import chisel3.util.Cat

class ProtobufKey {
  val wire_type    = Wire(UInt(3.W))
  val field_number = Wire(UInt(8.W))
  val value_size   = Wire(UInt(16.W))
  val key_size     = Wire(UInt(8.W))

  def toPrintable: Printable = {
    p"ProtobufKey:\n" +
      p"  wire_type    : ${wire_type}\n" +
      p"  field_number : ${field_number}\n" +
      p"  key_size   : ${key_size}\n" +
      p"  value_size   : ${value_size}\n"
  }
}

object ProtobufKey {
  def apply(in: Vec[UInt]): ProtobufKey = {

    val key = new ProtobufKey()
    val decode_bytes = in.length

    // Decode to integer
    val varint_decode = Module(new VarintDecoder(decode_bytes))
    val raw_key = Wire(UInt((decode_bytes * 8).W))
    varint_decode.io.input := in
    raw_key := varint_decode.io.output

    key.wire_type := raw_key
    key.field_number := raw_key >> 3


    val offset_map = Module(new OffsetMap(decode_bytes))
    offset_map.io.offset := varint_decode.io.bytes_read
    offset_map.io.input := in
    val decoded_length = DecodeVarint(offset_map.io.output)

    {
      key.key_size := varint_decode.io.bytes_read
      key.value_size := 0.U
      when (0.U === key.wire_type){ // Varint
        key.value_size := decoded_length._2
      }
      when (1.U === key.wire_type) { // 64 bit
        key.value_size := 8.U
      }
      when (2.U === key.wire_type) { // Length delimited
        key.key_size := decoded_length._2 + varint_decode.io.bytes_read
        key.value_size := decoded_length._1
      }
      when (5.U === key.wire_type) { // 32 bit
        key.value_size := 4.U
      }
    }

    key
  }
}

class KeyDecoder(decode_bytes: Int) extends Module {

  val io = IO(new Bundle{
    val input        = Input(Vec(decode_bytes, UInt(8.W)))
    val wire_type    = Output(UInt(3.W))
    val field_number = Output(UInt(8.W))
    val key_size     = Output(UInt(8.W))
    val value_size   = Output(UInt(16.W))
  })

  val key = ProtobufKey(io.input)
  io.wire_type := key.wire_type
  io.field_number := key.field_number
  io.key_size := key.key_size
  io.value_size := key.value_size
}