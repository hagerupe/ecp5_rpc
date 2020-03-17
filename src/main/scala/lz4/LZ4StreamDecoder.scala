package lz4

import chisel3._
import chisel3.experimental.ChiselEnum
import chisel3.util._

class LZ4StreamDecoder extends Module {

  val io = IO(new Bundle{
    val input  = Flipped(Decoupled(Vec(8, UInt(8.W))))
  })

  object State extends ChiselEnum {
    val idle, readToken, copyLiteral, readOffset, copyBuffer, Nil = Value
  }


  val inputBuffer = Module(new Queue(chiselTypeOf(io.input.bits), 2))
  inputBuffer.io.enq.valid := io.input.valid
  inputBuffer.io.enq.bits := io.input.bits
  io.input.ready := inputBuffer.io.enq.ready

  val state         = RegInit(State.idle)
  val literalLength = RegInit(0.U)
  val copyLength    = RegInit(0.U)
  val curInputOff   = RegInit(0.U)
  val curInput      = Reg(Vec(8, UInt(8.W)))

  // When input offset passed current buffer, read second buffer and transfer
  curInputOff >= 8.U

  switch (state) {
    is (State.idle) {
      when (inputBuffer.io.deq.valid) {
        state := State.readToken
        curInput := inputBuffer.io.deq.deq()
        // Read first byte
      }
    }
    is (State.readToken) {

    }
    is (State.copyLiteral) {

    }
    is (State.readOffset) {

    }
    is (State.copyBuffer) {

    }
    is (Nil) { }
  }
}
