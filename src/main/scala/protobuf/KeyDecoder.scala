package protobuf

import chisel3._

class KeyDecoder(decode_bytes: Int) extends Module {

  val io = IO(new Bundle{
    val input  = Input(Vec(decode_bytes, UInt(8.W)))
    val wire_type = Output(UInt(3.W))
    val field_number = Output(UInt((decode_bytes * 8).W))
  })

  // Decode to integer
  val varint_decode = Module(new Varint(decode_bytes))
  val raw_key = Wire(UInt((decode_bytes * 8).W))
  varint_decode.io.input := io.input
  raw_key := varint_decode.io.output

  io.wire_type := raw_key
  io.field_number := raw_key
}
