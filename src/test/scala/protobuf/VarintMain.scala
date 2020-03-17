// See README.md for license details.

package protobuf

import chisel3._
import chisel3.iotesters.PeekPokeTester
import lz4.LSICDecoder
import simple.Test1

object VarintMain extends App {

  val add_bit_width = 10
  val word_byte_ct = 4

  val abs_read_addr = 0.U
  val msg = Test1()
    .withInt(150)
    .withLong(2047)
    .withFloat(1.5f)
    .withVarchar("DEADBEEF")
  val bytes = msg.toByteArray
  println("Bytes:" + convertBytesToHex(bytes))

  assert(iotesters.Driver.execute(args, () => new Top(add_bit_width, word_byte_ct, bytes)) {
    c => new TopTester(c)
  })


  /*

  assert(iotesters.Driver.execute(args, () => new UnalignedMemoryController(10, 32, bytes)) {
    c => new UnalignedMemoryControllerTester(c)
  })

  assert(iotesters.Driver.execute(args, () => new LSICDecoder()) {
    c => new SequenceDeserializerTester(c)
  })

  assert(iotesters.Driver.execute(args, () => new RingBuffer(4, 10)) {
    c => new RingBufferTester(c)
  })

  assert(iotesters.Driver.execute(args, () => new ZigZagDecoder(32)) {
    c => new ZigZagDecoderTester(c)
  })

  assert(iotesters.Driver.execute(args, () => new VarintDecoder(4)) {
    c => new VarintUnitTester(c)
  })

  assert(iotesters.Driver.execute(args, () => new KeyDecoder(8)) {
    c => new KeyDecoderUnitTester(c)
  })
  */

  def convertBytesToHex(bytes: Seq[Byte]): String = {
    val sb = new StringBuilder
    for (b <- bytes) {
      sb.append(String.format("%02x ", Byte.box(b)))
    }
    sb.toString
  }
}

class UnalignedMemoryControllerTester(t: UnalignedMemoryController) extends PeekPokeTester(t) {

  // No backpressure
  poke(t.io.readData.ready, 1.B)

  read(6.U)
  read(4.U)
  read(10.U)
  read(7.U)

  step(5)

  def read(address: UInt): Seq[BigInt] = {
    while (peek(t.io.readAddress.ready) != 1) {
      step(1)
    }
    poke(t.io.readAddress.valid, 1.B)
    poke(t.io.readAddress.bits, address)
    step(1)
    poke(t.io.readAddress.valid, 0.B)
    while (peek(t.io.readData.valid) != 1) {
      step(1)
    }
    val result = peek(t.io.readData.bits)
    return result
  }

  /*poke(t.io.readAddress.valid, 1.B)
  poke(t.io.readAddress.bits, 6.U)
  step(1) // Hold one cycle
  poke(t.io.readAddress.valid, 0.B)
  step(1) // Await results
  // TODO expects

  poke(t.io.readAddress.valid, 1.B)
  poke(t.io.readAddress.bits, 4.U)
  step(1)*/
  // TODO expects
}

class SequenceDeserializerTester(t: LSICDecoder) extends  PeekPokeTester(t) {

  poke(t.io.input.valid, 1.B)
  poke(t.io.input.bits, 255.U)
  poke(t.io.output.ready, 0.B)
  step(10) // Load 1 byte
  poke(t.io.input.bits, 10.U)
  step(3)
  poke(t.io.input.valid, 0.B)
  poke(t.io.output.ready, 1.B)
  step(1)
}

class TopTester(t: Top) extends PeekPokeTester(t) {
  step(20)
}

class RingBufferTester(rb: RingBuffer) extends PeekPokeTester(rb) {

  // Perform one write / don't read
  poke(rb.io.wr_valid, 1.B)
  poke(rb.io.wr_data(0), 123.U)
  poke(rb.io.rd_increment, 0.U)
  expect(rb.io.rd_data(0), 0)
  step(1)

  // Perform one write / read current
  poke(rb.io.wr_valid, 1.B)
  poke(rb.io.wr_data(0), 59.U)
  poke(rb.io.rd_increment, 1.U)
  expect(rb.io.rd_data(0), 123) // 0
  step(1)

  // Perform one write / read current
  poke(rb.io.wr_valid, 1)
  poke(rb.io.wr_data(0), 43)
  poke(rb.io.rd_increment, 1)
  expect(rb.io.rd_data(0), 59)
  step(1)

  // No write / peek read current
  poke(rb.io.wr_valid, 0)
  poke(rb.io.wr_data(0), 0)
  poke(rb.io.rd_increment, 0)
  expect(rb.io.rd_data(0), 43)
  step(3)

  // Verify no increment on rd
  poke(rb.io.wr_valid, 0)
  poke(rb.io.wr_data(0), 0)
  poke(rb.io.rd_increment, 0)
  expect(rb.io.rd_data(0), 43)
}

class ZigZagDecoderTester(zzd: ZigZagDecoder) extends PeekPokeTester(zzd) {
  poke(zzd.io.input, 0)
  expect(zzd.io.output, 0)
  step(1)

  poke(zzd.io.input, 1)
  expect(zzd.io.output, -1)
  step(1)

  poke(zzd.io.input, 2)
  expect(zzd.io.output, 1)
  step(1)

  poke(zzd.io.input, 3)
  expect(zzd.io.output, -2)
  step(1)
}

class VarintUnitTester(gcd: VarintDecoder) extends PeekPokeTester(gcd) {
  poke(gcd.io.input(0), Integer.parseInt("10101100", 2))
  poke(gcd.io.input(1), Integer.parseInt("00000010", 2))
  poke(gcd.io.input(2), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(3), Integer.parseInt("00000000", 2))
  step(1)
  expect(gcd.io.output, 300)
  expect(gcd.io.bytes_read, 2)

  poke(gcd.io.input(0), Integer.parseInt("00001000", 2))
  poke(gcd.io.input(1), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(2), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(3), Integer.parseInt("00000000", 2))
  step(1)
  expect(gcd.io.output, 8)
  expect(gcd.io.bytes_read, 1)
}

class KeyDecoderUnitTester(kd: KeyDecoder) extends PeekPokeTester(kd) {

  // Varint
  poke(kd.io.input(0), Integer.parseInt("00001000", 2))
  poke(kd.io.input(1), Integer.parseInt("10010110", 2))
  poke(kd.io.input(2), Integer.parseInt("00000001", 2))
  poke(kd.io.input(3), Integer.parseInt("00000000", 2))
  poke(kd.io.input(4), Integer.parseInt("00000000", 2))
  poke(kd.io.input(5), Integer.parseInt("00000000", 2))
  poke(kd.io.input(6), Integer.parseInt("00000000", 2))
  poke(kd.io.input(7), Integer.parseInt("00000000", 2))
  step(1)
  expect(kd.io.wire_type, 0)
  expect(kd.io.field_number, 1)
  expect(kd.io.key_size, 1)
}