package lz4

import chisel3._
import chisel3.util._

class LSICDecoder extends Module {

  val io = IO(new Bundle{
    val clear  = Input(Bool())
    val input  = Flipped(Decoupled(UInt(8.W)))
    val output = Decoupled(UInt(16.W))
  })

  val accum = RegInit(0.U(16.W))

  io.output.valid := io.input.bits != 0xFF.U
  io.input.ready := Mux(io.output.valid && !io.output.ready, 0.B, 1.B)
  io.output.bits := accum

  when (io.clear) {      // Reset
    accum := 0.U
  }.
  elsewhen(io.input.valid && !io.output.valid) { // Loading
    accum := accum + io.input.bits
  }.
  otherwise { // Holding
    accum := accum
  }

  printf(p"SequenceDeserializer: ${io}\n")
}
