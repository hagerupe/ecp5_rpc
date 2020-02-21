GCD.v:
	sbt 'runMain protobuf.ToVerilog'

test:
	sbt 'test:runMain protobuf.VarintMain --generate-vcd-output off'
