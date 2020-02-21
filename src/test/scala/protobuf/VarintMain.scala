// See README.md for license details.

package protobuf

import chisel3._
import chisel3.iotesters.PeekPokeTester

object VarintMain extends App {
  assert(iotesters.Driver.execute(args, () => new VarintDecoder(4)) {
    c => new VarintUnitTester(c)
  })

  assert(iotesters.Driver.execute(args, () => new KeyDecoder(8)) {
    c => new KeyDecoderUnitTester(c)
  })
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
  expect(kd.io.bytes_read, 1)
}