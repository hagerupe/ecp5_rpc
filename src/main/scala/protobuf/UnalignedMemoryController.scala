package protobuf

import chisel3._
import chisel3.experimental.ChiselEnum
import chisel3.util._

// TODO come back to this use sync memory and pipelining
class UnalignedMemoryController(addrWidth  :Int,
                                dataWidth  :Int,
                                bytes      :Array[Byte]) extends Module {

  assert(dataWidth % 8 == 0) // Only allow multiples of a byte
  val dataBytes = dataWidth / 8
  val memoryAddresses = math.pow(2, addrWidth).toInt / log2Down(dataBytes)

  val io = IO(new Bundle {
    val readAddress = Flipped(Decoupled(UInt(addrWidth.W)))
    val readData = Decoupled(Vec(dataBytes, UInt(8.W)))
  })

  object State extends ChiselEnum {
    val idle, readU, readA = Value
  }

  val readAddressQueue = Queue(io.readAddress, pipe = true, flow = true)
  val mem = SyncReadMem(memoryAddresses, Vec(dataBytes, UInt(8.W)))
  for (i <- bytes.indices) { mem(i / dataBytes)(i % dataBytes) := (bytes(i) & 0xFF).asUInt() } // Load initial state

  val state        = RegInit(State.idle)
  val readUAddress = Reg(UInt(addrWidth.W))
  val readAddress  = Wire(UInt(addrWidth.W))
  val readCurrent  = mem.read(readAddress)
  val readLast     = RegNext(readCurrent)

  io.readData.noenq()
  readAddressQueue.nodeq()
  readAddress := io.readAddress.bits / dataBytes.U

  when (state === State.idle) {
    when (io.readData.ready && readAddressQueue.valid) {
      val address = readAddressQueue.deq()
      readUAddress := readAddressQueue.deq() + dataBytes.U
      state := Mux(address % dataBytes.U === 0.U, State.readA, State.readU)
    }
  }
  when (state === State.readU) {
    readAddress := readUAddress / dataBytes.U
    state := State.readA
  }
  when (state ===  State.readA) {
    io.readData.enq(combineAndShift(readLast, readCurrent, readUAddress % dataBytes.U))
    state := State.idle
  }


  def combineAndShift(v1: Vec[UInt], v2: Vec[UInt], shift: UInt): Vec[UInt] = {
    // Concatenated last read and current read for unaligned reads
    val memReadUnaligned = Wire(Vec(v1.size * 2, UInt(8.W)))
    for (i <- 0 until dataBytes) { memReadUnaligned(i) := v1(i); memReadUnaligned(i + dataBytes) := v2(i) }

    // Shift and store based on address of unaligned read
    val shifted = VectorShift(memReadUnaligned, shift)
    VectorSubSequence(shifted, 0, dataBytes)
  }
}
