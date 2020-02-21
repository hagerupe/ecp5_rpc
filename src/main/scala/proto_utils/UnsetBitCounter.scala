package proto_utils

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
