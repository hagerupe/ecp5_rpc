package protobuf

import chisel3._
import util.Cat

import scala.collection.mutable

//noinspection TypeAnnotation
// Largest index is MSB
class Varint(byte_count: Int) extends Module {
  val io = IO(new Bundle{
    val input  = Input(Vec(byte_count, UInt(8.W)))
    val output = Output(UInt((byte_count * 8).W))
  })

  // Compute if MSB is set for byte
  val bitmask = mutable.ArrayBuffer[Bool]()
  for ( i <- 0 until byte_count) {
    bitmask += io.input(io.input.length - 1  - i)(7)
  }
  val bitmask_int = Cat(bitmask)

  // Determine mask for valid bytes
  val bitmask_cmp = mutable.ArrayBuffer[Bool]()
  for (i <- 0 until byte_count) {
    bitmask_cmp += !(bitmask_int < math.pow(2, io.input.length - i - 1).toInt.U)
  }
  val bitmask_cmp_int = Cat(bitmask_cmp)

  // Compute if MSB is set for byte
  val results = Wire(Vec(byte_count, UInt(7.W)))
  for (i <- 0 until io.input.length) {
    val shifted = Wire(UInt(7.W))
    shifted := (io.input(io.input.length - 1 - i))
    val mask = bitmask_cmp(i)
    results(io.input.length - 1 - i) := Mux(mask, shifted, 0.U)
  }

  // Compute number of mask bits set
  val unset_bit_counter = Module(new proto_utils.UnsetBitCounter(byte_count))
  unset_bit_counter.io.input := bitmask_cmp_int

  // Shift based on unused bytes
  val results_int = Cat(results)
  val shifted = results_int >> (7.U * unset_bit_counter.io.output)
  io.output := Cat(shifted)
}

object ToVerilog extends App {
  chisel3.Driver.execute(args, () => new Varint(16))
}