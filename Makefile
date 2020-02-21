all: KeyDecoder.v

KeyDecoder.v:
	sbt 'runMain protobuf.KeyDecoderGenerate'

test:
	sbt 'test:runMain protobuf.VarintMain --backend-name verilator'
