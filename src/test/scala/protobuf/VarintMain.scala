// See README.md for license details.

package protobuf

import chisel3._
import chisel3.iotesters.PeekPokeTester
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

  /*assert(iotesters.Driver.execute(args, () => new RingBuffer(4, 10)) {
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
  })*/

  assert(iotesters.Driver.execute(args, () => new Top(add_bit_width, word_byte_ct, bytes)) {
    c => new TopTester(c)
  })
}

class TopTester(t: Top) extends PeekPokeTester(t) {
  step(10)
}

class RingBufferTester(rb: RingBuffer) extends PeekPokeTester(rb) {

  // Perform one write / don't read
  poke(rb.io.wr_valid, 1)
  poke(rb.io.wr_data(0), 123)
  poke(rb.io.rd_increment, 0)
  expect(rb.io.rd_data(0), 0)
  step(1)

  // Perform one write / read current
  poke(rb.io.wr_valid, 1)
  poke(rb.io.wr_data(0), 59)
  poke(rb.io.rd_increment, 1)
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