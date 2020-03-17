all: KeyDecoder.v

KeyDecoder.v:
	sbt 'runMain protobuf.KeyDecoderGenerate'

test:
	rm -rf test_run_dir
	sbt 'test:runMain protobuf.VarintMain --backend-name verilator'
	find ./ -name *.vcd -exec cp -prv '{}' '.' ';'

