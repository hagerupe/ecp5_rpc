// See README.md for license details.

package protobuf

import chisel3._
import chisel3.iotesters.PeekPokeTester

object VarintMain extends App {
  iotesters.Driver.execute(args, () => new Varint(5)) {
    c => new VarintUnitTester(c)
  }

  iotesters.Driver.execute(args, () => new KeyDecoder(1)) {
    c => new KeyDecoderUnitTester(c)
  }
}

class VarintUnitTester(gcd: Varint) extends PeekPokeTester(gcd) {
  poke(gcd.io.input(0), Integer.parseInt("00000010", 2))
  poke(gcd.io.input(1), Integer.parseInt("10101100", 2))
  poke(gcd.io.input(2), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(3), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(4), Integer.parseInt("00000000", 2))
  expect(gcd.io.output, 300)

  poke(gcd.io.input(0), Integer.parseInt("00001000", 2))
  poke(gcd.io.input(1), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(2), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(3), Integer.parseInt("00000000", 2))
  poke(gcd.io.input(4), Integer.parseInt("00000000", 2))
  expect(gcd.io.output, 8)
}

class KeyDecoderUnitTester(kd: KeyDecoder) extends PeekPokeTester(kd) {
  poke(kd.io.input(0), Integer.parseInt("00001000"))
  expect(kd.io.wire_type, 3)
  expect(kd.io.field_number, 3)
}