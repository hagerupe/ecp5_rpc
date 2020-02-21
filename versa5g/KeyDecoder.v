module UnsetBitCounter(
  input  [7:0] io_input,
  output [2:0] io_output
);
  wire  _T; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] bits_unset_1; // @[UnsetBitCounter.scala 35:22]
  wire  _T_3; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_5; // @[UnsetBitCounter.scala 35:65]
  wire [2:0] bits_unset_2; // @[UnsetBitCounter.scala 35:22]
  wire  _T_6; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_8; // @[UnsetBitCounter.scala 35:65]
  wire [2:0] bits_unset_3; // @[UnsetBitCounter.scala 35:22]
  wire  _T_9; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_11; // @[UnsetBitCounter.scala 35:65]
  wire [2:0] bits_unset_4; // @[UnsetBitCounter.scala 35:22]
  wire  _T_12; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_14; // @[UnsetBitCounter.scala 35:65]
  wire [2:0] bits_unset_5; // @[UnsetBitCounter.scala 35:22]
  wire  _T_15; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_17; // @[UnsetBitCounter.scala 35:65]
  wire [2:0] bits_unset_6; // @[UnsetBitCounter.scala 35:22]
  wire  _T_18; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_20; // @[UnsetBitCounter.scala 35:65]
  wire [2:0] bits_unset_7; // @[UnsetBitCounter.scala 35:22]
  wire  _T_21; // @[UnsetBitCounter.scala 35:31]
  wire [2:0] _T_23; // @[UnsetBitCounter.scala 35:65]
  assign _T = io_input[0]; // @[UnsetBitCounter.scala 35:31]
  assign bits_unset_1 = _T ? 3'h0 : 3'h1; // @[UnsetBitCounter.scala 35:22]
  assign _T_3 = io_input[1]; // @[UnsetBitCounter.scala 35:31]
  assign _T_5 = bits_unset_1 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign bits_unset_2 = _T_3 ? bits_unset_1 : _T_5; // @[UnsetBitCounter.scala 35:22]
  assign _T_6 = io_input[2]; // @[UnsetBitCounter.scala 35:31]
  assign _T_8 = bits_unset_2 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign bits_unset_3 = _T_6 ? bits_unset_2 : _T_8; // @[UnsetBitCounter.scala 35:22]
  assign _T_9 = io_input[3]; // @[UnsetBitCounter.scala 35:31]
  assign _T_11 = bits_unset_3 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign bits_unset_4 = _T_9 ? bits_unset_3 : _T_11; // @[UnsetBitCounter.scala 35:22]
  assign _T_12 = io_input[4]; // @[UnsetBitCounter.scala 35:31]
  assign _T_14 = bits_unset_4 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign bits_unset_5 = _T_12 ? bits_unset_4 : _T_14; // @[UnsetBitCounter.scala 35:22]
  assign _T_15 = io_input[5]; // @[UnsetBitCounter.scala 35:31]
  assign _T_17 = bits_unset_5 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign bits_unset_6 = _T_15 ? bits_unset_5 : _T_17; // @[UnsetBitCounter.scala 35:22]
  assign _T_18 = io_input[6]; // @[UnsetBitCounter.scala 35:31]
  assign _T_20 = bits_unset_6 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign bits_unset_7 = _T_18 ? bits_unset_6 : _T_20; // @[UnsetBitCounter.scala 35:22]
  assign _T_21 = io_input[7]; // @[UnsetBitCounter.scala 35:31]
  assign _T_23 = bits_unset_7 + 3'h1; // @[UnsetBitCounter.scala 35:65]
  assign io_output = _T_21 ? bits_unset_7 : _T_23; // @[UnsetBitCounter.scala 38:13]
endmodule
module VarintDecoder(
  input         clock,
  input         reset,
  input  [7:0]  io_input_0,
  input  [7:0]  io_input_1,
  input  [7:0]  io_input_2,
  input  [7:0]  io_input_3,
  input  [7:0]  io_input_4,
  input  [7:0]  io_input_5,
  input  [7:0]  io_input_6,
  input  [7:0]  io_input_7,
  output [63:0] io_output,
  output [7:0]  io_bytes_read
);
  wire [7:0] unset_bit_counter_io_input; // @[VarintDecoder.scala 56:33]
  wire [2:0] unset_bit_counter_io_output; // @[VarintDecoder.scala 56:33]
  wire  _T; // @[VarintDecoder.scala 35:51]
  wire  bitmask_1; // @[VarintDecoder.scala 35:16]
  wire  _T_1; // @[VarintDecoder.scala 35:51]
  wire  bitmask_2; // @[VarintDecoder.scala 35:16]
  wire  _T_2; // @[VarintDecoder.scala 35:51]
  wire  bitmask_3; // @[VarintDecoder.scala 35:16]
  wire  _T_3; // @[VarintDecoder.scala 35:51]
  wire  bitmask_4; // @[VarintDecoder.scala 35:16]
  wire  _T_4; // @[VarintDecoder.scala 35:51]
  wire  bitmask_5; // @[VarintDecoder.scala 35:16]
  wire  _T_5; // @[VarintDecoder.scala 35:51]
  wire  bitmask_6; // @[VarintDecoder.scala 35:16]
  wire  _T_6; // @[VarintDecoder.scala 35:51]
  wire  bitmask_7; // @[VarintDecoder.scala 35:16]
  wire [7:0] bitmask_int; // @[Cat.scala 29:58]
  wire  bitmask_cmp_0; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_1; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_2; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_3; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_4; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_5; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_6; // @[VarintDecoder.scala 42:33]
  wire  bitmask_cmp_7; // @[VarintDecoder.scala 42:33]
  wire [3:0] _T_15; // @[Cat.scala 29:58]
  wire [3:0] _T_18; // @[Cat.scala 29:58]
  wire [7:0] bitmask_cmp_int; // @[Cat.scala 29:58]
  wire [6:0] _T_19; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_0; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_21; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_1; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_23; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_2; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_25; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_3; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_27; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_4; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_29; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_5; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_31; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_6; // @[VarintDecoder.scala 52:22]
  wire [6:0] _T_33; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  wire [6:0] results_7; // @[VarintDecoder.scala 52:22]
  wire [3:0] _GEN_0; // @[VarintDecoder.scala 58:33]
  wire [3:0] _T_36; // @[VarintDecoder.scala 58:33]
  wire [55:0] _T_43; // @[Cat.scala 29:58]
  wire [63:0] _T_50; // @[Cat.scala 29:58]
  wire  _T_51; // @[VarintDecoder.scala 61:9]
  wire  _T_52; // @[VarintDecoder.scala 61:9]
  UnsetBitCounter unset_bit_counter ( // @[VarintDecoder.scala 56:33]
    .io_input(unset_bit_counter_io_input),
    .io_output(unset_bit_counter_io_output)
  );
  assign _T = io_input_0[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_1 = _T == 1'h0; // @[VarintDecoder.scala 35:16]
  assign _T_1 = io_input_1[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_2 = _T_1 == 1'h0; // @[VarintDecoder.scala 35:16]
  assign _T_2 = io_input_2[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_3 = _T_2 == 1'h0; // @[VarintDecoder.scala 35:16]
  assign _T_3 = io_input_3[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_4 = _T_3 == 1'h0; // @[VarintDecoder.scala 35:16]
  assign _T_4 = io_input_4[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_5 = _T_4 == 1'h0; // @[VarintDecoder.scala 35:16]
  assign _T_5 = io_input_5[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_6 = _T_5 == 1'h0; // @[VarintDecoder.scala 35:16]
  assign _T_6 = io_input_6[7]; // @[VarintDecoder.scala 35:51]
  assign bitmask_7 = _T_6 == 1'h0; // @[VarintDecoder.scala 35:16]
  assign bitmask_int = {1'h0,bitmask_1,bitmask_2,bitmask_3,bitmask_4,bitmask_5,bitmask_6,bitmask_7}; // @[Cat.scala 29:58]
  assign bitmask_cmp_0 = bitmask_int < 8'h1; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_1 = bitmask_int < 8'h2; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_2 = bitmask_int < 8'h4; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_3 = bitmask_int < 8'h8; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_4 = bitmask_int < 8'h10; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_5 = bitmask_int < 8'h20; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_6 = bitmask_int < 8'h40; // @[VarintDecoder.scala 42:33]
  assign bitmask_cmp_7 = bitmask_int < 8'h80; // @[VarintDecoder.scala 42:33]
  assign _T_15 = {bitmask_cmp_4,bitmask_cmp_5,bitmask_cmp_6,bitmask_cmp_7}; // @[Cat.scala 29:58]
  assign _T_18 = {bitmask_cmp_0,bitmask_cmp_1,bitmask_cmp_2,bitmask_cmp_3}; // @[Cat.scala 29:58]
  assign bitmask_cmp_int = {bitmask_cmp_0,bitmask_cmp_1,bitmask_cmp_2,bitmask_cmp_3,bitmask_cmp_4,bitmask_cmp_5,bitmask_cmp_6,bitmask_cmp_7}; // @[Cat.scala 29:58]
  assign _T_19 = io_input_7[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_0 = bitmask_cmp_0 ? _T_19 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_21 = io_input_6[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_1 = bitmask_cmp_1 ? _T_21 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_23 = io_input_5[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_2 = bitmask_cmp_2 ? _T_23 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_25 = io_input_4[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_3 = bitmask_cmp_3 ? _T_25 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_27 = io_input_3[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_4 = bitmask_cmp_4 ? _T_27 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_29 = io_input_2[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_5 = bitmask_cmp_5 ? _T_29 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_31 = io_input_1[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_6 = bitmask_cmp_6 ? _T_31 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _T_33 = io_input_0[6:0]; // @[VarintDecoder.scala 49:23 VarintDecoder.scala 50:13]
  assign results_7 = bitmask_cmp_7 ? _T_33 : 7'h0; // @[VarintDecoder.scala 52:22]
  assign _GEN_0 = {{1'd0}, unset_bit_counter_io_output}; // @[VarintDecoder.scala 58:33]
  assign _T_36 = 4'h8 - _GEN_0; // @[VarintDecoder.scala 58:33]
  assign _T_43 = {results_0,results_1,results_2,results_3,results_4,results_5,results_6,results_7}; // @[Cat.scala 29:58]
  assign _T_50 = {io_input_0,io_input_1,io_input_2,io_input_3,io_input_4,io_input_5,io_input_6,io_input_7}; // @[Cat.scala 29:58]
  assign _T_51 = $unsigned(reset); // @[VarintDecoder.scala 61:9]
  assign _T_52 = _T_51 == 1'h0; // @[VarintDecoder.scala 61:9]
  assign io_output = {{8'd0}, _T_43}; // @[VarintDecoder.scala 59:13]
  assign io_bytes_read = {{4'd0}, _T_36}; // @[VarintDecoder.scala 58:17]
  assign unset_bit_counter_io_input = {_T_18,_T_15}; // @[VarintDecoder.scala 57:30]
  always @(posedge clock) begin
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_52) begin
          $fwrite(32'h80000002,"VarintDecoder:io.input:                    %d\n",_T_50); // @[VarintDecoder.scala 61:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_52) begin
          $fwrite(32'h80000002,"VarintDecoder:bitmask_cmp_int:             %d\n",bitmask_cmp_int); // @[VarintDecoder.scala 62:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_52) begin
          $fwrite(32'h80000002,"VarintDecoder:io.bytes_read:               %d\n",io_bytes_read); // @[VarintDecoder.scala 63:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_52) begin
          $fwrite(32'h80000002,"VarintDecoder:io.output:                   %d\n",io_output); // @[VarintDecoder.scala 64:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
  end
endmodule
module OffsetMap(
  input        clock,
  input        reset,
  input  [7:0] io_input_0,
  input  [7:0] io_input_1,
  input  [7:0] io_input_2,
  input  [7:0] io_input_3,
  input  [7:0] io_input_4,
  input  [7:0] io_input_5,
  input  [7:0] io_input_6,
  input  [7:0] io_input_7,
  input  [7:0] io_offset,
  output [7:0] io_output_0,
  output [7:0] io_output_1,
  output [7:0] io_output_2,
  output [7:0] io_output_3,
  output [7:0] io_output_4,
  output [7:0] io_output_5,
  output [7:0] io_output_6,
  output [7:0] io_output_7
);
  wire [63:0] _T_6; // @[Cat.scala 29:58]
  wire [3:0] _T_8; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_10; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_0; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_12; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_13; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_14; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_23; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_25; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_1; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_27; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_28; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_29; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_38; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_40; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_2; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_42; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_43; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_44; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_53; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_55; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_3; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_57; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_58; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_59; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_68; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_70; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_4; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_72; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_73; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_74; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_83; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_85; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_5; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_87; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_88; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_89; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_98; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_100; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_6; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_102; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_103; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_104; // @[OffsetMap.scala 30:35]
  wire [3:0] _T_113; // @[OffsetMap.scala 30:48]
  wire [3:0] _T_115; // @[OffsetMap.scala 30:54]
  wire [7:0] _GEN_7; // @[OffsetMap.scala 30:60]
  wire [7:0] _T_117; // @[OffsetMap.scala 30:60]
  wire [11:0] _T_118; // @[OffsetMap.scala 30:73]
  wire [63:0] _T_119; // @[OffsetMap.scala 30:35]
  wire  _T_127; // @[OffsetMap.scala 33:9]
  wire  _T_128; // @[OffsetMap.scala 33:9]
  wire [63:0] _T_135; // @[Cat.scala 29:58]
  assign _T_6 = {io_input_0,io_input_1,io_input_2,io_input_3,io_input_4,io_input_5,io_input_6,io_input_7}; // @[Cat.scala 29:58]
  assign _T_8 = 4'h8 - 4'h0; // @[OffsetMap.scala 30:48]
  assign _T_10 = _T_8 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_0 = {{4'd0}, _T_10}; // @[OffsetMap.scala 30:60]
  assign _T_12 = _GEN_0 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_13 = _T_12 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_14 = _T_6 >> _T_13; // @[OffsetMap.scala 30:35]
  assign _T_23 = 4'h8 - 4'h1; // @[OffsetMap.scala 30:48]
  assign _T_25 = _T_23 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_1 = {{4'd0}, _T_25}; // @[OffsetMap.scala 30:60]
  assign _T_27 = _GEN_1 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_28 = _T_27 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_29 = _T_6 >> _T_28; // @[OffsetMap.scala 30:35]
  assign _T_38 = 4'h8 - 4'h2; // @[OffsetMap.scala 30:48]
  assign _T_40 = _T_38 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_2 = {{4'd0}, _T_40}; // @[OffsetMap.scala 30:60]
  assign _T_42 = _GEN_2 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_43 = _T_42 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_44 = _T_6 >> _T_43; // @[OffsetMap.scala 30:35]
  assign _T_53 = 4'h8 - 4'h3; // @[OffsetMap.scala 30:48]
  assign _T_55 = _T_53 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_3 = {{4'd0}, _T_55}; // @[OffsetMap.scala 30:60]
  assign _T_57 = _GEN_3 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_58 = _T_57 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_59 = _T_6 >> _T_58; // @[OffsetMap.scala 30:35]
  assign _T_68 = 4'h8 - 4'h4; // @[OffsetMap.scala 30:48]
  assign _T_70 = _T_68 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_4 = {{4'd0}, _T_70}; // @[OffsetMap.scala 30:60]
  assign _T_72 = _GEN_4 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_73 = _T_72 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_74 = _T_6 >> _T_73; // @[OffsetMap.scala 30:35]
  assign _T_83 = 4'h8 - 4'h5; // @[OffsetMap.scala 30:48]
  assign _T_85 = _T_83 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_5 = {{4'd0}, _T_85}; // @[OffsetMap.scala 30:60]
  assign _T_87 = _GEN_5 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_88 = _T_87 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_89 = _T_6 >> _T_88; // @[OffsetMap.scala 30:35]
  assign _T_98 = 4'h8 - 4'h6; // @[OffsetMap.scala 30:48]
  assign _T_100 = _T_98 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_6 = {{4'd0}, _T_100}; // @[OffsetMap.scala 30:60]
  assign _T_102 = _GEN_6 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_103 = _T_102 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_104 = _T_6 >> _T_103; // @[OffsetMap.scala 30:35]
  assign _T_113 = 4'h8 - 4'h7; // @[OffsetMap.scala 30:48]
  assign _T_115 = _T_113 - 4'h1; // @[OffsetMap.scala 30:54]
  assign _GEN_7 = {{4'd0}, _T_115}; // @[OffsetMap.scala 30:60]
  assign _T_117 = _GEN_7 - io_offset; // @[OffsetMap.scala 30:60]
  assign _T_118 = _T_117 * 8'h8; // @[OffsetMap.scala 30:73]
  assign _T_119 = _T_6 >> _T_118; // @[OffsetMap.scala 30:35]
  assign _T_127 = $unsigned(reset); // @[OffsetMap.scala 33:9]
  assign _T_128 = _T_127 == 1'h0; // @[OffsetMap.scala 33:9]
  assign _T_135 = {io_output_0,io_output_1,io_output_2,io_output_3,io_output_4,io_output_5,io_output_6,io_output_7}; // @[Cat.scala 29:58]
  assign io_output_0 = _T_14[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_1 = _T_29[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_2 = _T_44[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_3 = _T_59[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_4 = _T_74[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_5 = _T_89[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_6 = _T_104[7:0]; // @[OffsetMap.scala 30:18]
  assign io_output_7 = _T_119[7:0]; // @[OffsetMap.scala 30:18]
  always @(posedge clock) begin
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_128) begin
          $fwrite(32'h80000002,"OffsetMap:io.input:       %d\n",_T_6); // @[OffsetMap.scala 33:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_128) begin
          $fwrite(32'h80000002,"OffsetMap:io.output:      %d\n",_T_135); // @[OffsetMap.scala 34:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
  end
endmodule
module KeyDecoder(
  input         clock,
  input         reset,
  input  [7:0]  io_input_0,
  input  [7:0]  io_input_1,
  input  [7:0]  io_input_2,
  input  [7:0]  io_input_3,
  input  [7:0]  io_input_4,
  input  [7:0]  io_input_5,
  input  [7:0]  io_input_6,
  input  [7:0]  io_input_7,
  output [2:0]  io_wire_type,
  output [63:0] io_field_number,
  output [7:0]  io_bytes_read,
  output [31:0] io_value_size
);
  wire  varint_decode_clock; // @[KeyDecoder.scala 31:29]
  wire  varint_decode_reset; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_0; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_1; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_2; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_3; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_4; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_5; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_6; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_input_7; // @[KeyDecoder.scala 31:29]
  wire [63:0] varint_decode_io_output; // @[KeyDecoder.scala 31:29]
  wire [7:0] varint_decode_io_bytes_read; // @[KeyDecoder.scala 31:29]
  wire  length_varint_decoder_clock; // @[KeyDecoder.scala 39:37]
  wire  length_varint_decoder_reset; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_0; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_1; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_2; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_3; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_4; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_5; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_6; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_input_7; // @[KeyDecoder.scala 39:37]
  wire [63:0] length_varint_decoder_io_output; // @[KeyDecoder.scala 39:37]
  wire [7:0] length_varint_decoder_io_bytes_read; // @[KeyDecoder.scala 39:37]
  wire  offset_map_clock; // @[KeyDecoder.scala 40:26]
  wire  offset_map_reset; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_0; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_1; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_2; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_3; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_4; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_5; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_6; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_input_7; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_offset; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_0; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_1; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_2; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_3; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_4; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_5; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_6; // @[KeyDecoder.scala 40:26]
  wire [7:0] offset_map_io_output_7; // @[KeyDecoder.scala 40:26]
  wire [63:0] raw_key; // @[KeyDecoder.scala 32:21 KeyDecoder.scala 34:11]
  wire [60:0] _T; // @[KeyDecoder.scala 37:30]
  wire  _T_1; // @[KeyDecoder.scala 48:15]
  wire [7:0] _GEN_0; // @[KeyDecoder.scala 48:32]
  wire  _T_2; // @[KeyDecoder.scala 51:15]
  wire [7:0] _GEN_1; // @[KeyDecoder.scala 51:33]
  wire  _T_3; // @[KeyDecoder.scala 54:15]
  wire [63:0] _GEN_2; // @[KeyDecoder.scala 54:33]
  wire  _T_4; // @[KeyDecoder.scala 57:15]
  wire [7:0] _GEN_3; // @[KeyDecoder.scala 57:33]
  wire  _T_6; // @[KeyDecoder.scala 62:9]
  VarintDecoder varint_decode ( // @[KeyDecoder.scala 31:29]
    .clock(varint_decode_clock),
    .reset(varint_decode_reset),
    .io_input_0(varint_decode_io_input_0),
    .io_input_1(varint_decode_io_input_1),
    .io_input_2(varint_decode_io_input_2),
    .io_input_3(varint_decode_io_input_3),
    .io_input_4(varint_decode_io_input_4),
    .io_input_5(varint_decode_io_input_5),
    .io_input_6(varint_decode_io_input_6),
    .io_input_7(varint_decode_io_input_7),
    .io_output(varint_decode_io_output),
    .io_bytes_read(varint_decode_io_bytes_read)
  );
  VarintDecoder length_varint_decoder ( // @[KeyDecoder.scala 39:37]
    .clock(length_varint_decoder_clock),
    .reset(length_varint_decoder_reset),
    .io_input_0(length_varint_decoder_io_input_0),
    .io_input_1(length_varint_decoder_io_input_1),
    .io_input_2(length_varint_decoder_io_input_2),
    .io_input_3(length_varint_decoder_io_input_3),
    .io_input_4(length_varint_decoder_io_input_4),
    .io_input_5(length_varint_decoder_io_input_5),
    .io_input_6(length_varint_decoder_io_input_6),
    .io_input_7(length_varint_decoder_io_input_7),
    .io_output(length_varint_decoder_io_output),
    .io_bytes_read(length_varint_decoder_io_bytes_read)
  );
  OffsetMap offset_map ( // @[KeyDecoder.scala 40:26]
    .clock(offset_map_clock),
    .reset(offset_map_reset),
    .io_input_0(offset_map_io_input_0),
    .io_input_1(offset_map_io_input_1),
    .io_input_2(offset_map_io_input_2),
    .io_input_3(offset_map_io_input_3),
    .io_input_4(offset_map_io_input_4),
    .io_input_5(offset_map_io_input_5),
    .io_input_6(offset_map_io_input_6),
    .io_input_7(offset_map_io_input_7),
    .io_offset(offset_map_io_offset),
    .io_output_0(offset_map_io_output_0),
    .io_output_1(offset_map_io_output_1),
    .io_output_2(offset_map_io_output_2),
    .io_output_3(offset_map_io_output_3),
    .io_output_4(offset_map_io_output_4),
    .io_output_5(offset_map_io_output_5),
    .io_output_6(offset_map_io_output_6),
    .io_output_7(offset_map_io_output_7)
  );
  assign raw_key = varint_decode_io_output; // @[KeyDecoder.scala 32:21 KeyDecoder.scala 34:11]
  assign _T = raw_key[63:3]; // @[KeyDecoder.scala 37:30]
  assign _T_1 = 3'h0 == io_wire_type; // @[KeyDecoder.scala 48:15]
  assign _GEN_0 = _T_1 ? length_varint_decoder_io_bytes_read : 8'h0; // @[KeyDecoder.scala 48:32]
  assign _T_2 = 3'h1 == io_wire_type; // @[KeyDecoder.scala 51:15]
  assign _GEN_1 = _T_2 ? 8'h8 : _GEN_0; // @[KeyDecoder.scala 51:33]
  assign _T_3 = 3'h2 == io_wire_type; // @[KeyDecoder.scala 54:15]
  assign _GEN_2 = _T_3 ? varint_decode_io_output : {{56'd0}, varint_decode_io_bytes_read}; // @[KeyDecoder.scala 54:33]
  assign _T_4 = 3'h5 == io_wire_type; // @[KeyDecoder.scala 57:15]
  assign _GEN_3 = _T_4 ? 8'h4 : _GEN_1; // @[KeyDecoder.scala 57:33]
  assign _T_6 = reset == 1'h0; // @[KeyDecoder.scala 62:9]
  assign io_wire_type = raw_key[2:0]; // @[KeyDecoder.scala 36:16]
  assign io_field_number = {{3'd0}, _T}; // @[KeyDecoder.scala 37:19]
  assign io_bytes_read = _GEN_2[7:0]; // @[KeyDecoder.scala 46:19 KeyDecoder.scala 55:21]
  assign io_value_size = {{24'd0}, _GEN_3}; // @[KeyDecoder.scala 47:19 KeyDecoder.scala 49:21 KeyDecoder.scala 52:21 KeyDecoder.scala 58:21]
  assign varint_decode_clock = clock;
  assign varint_decode_reset = reset;
  assign varint_decode_io_input_0 = io_input_0; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_1 = io_input_1; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_2 = io_input_2; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_3 = io_input_3; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_4 = io_input_4; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_5 = io_input_5; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_6 = io_input_6; // @[KeyDecoder.scala 33:26]
  assign varint_decode_io_input_7 = io_input_7; // @[KeyDecoder.scala 33:26]
  assign length_varint_decoder_clock = clock;
  assign length_varint_decoder_reset = reset;
  assign length_varint_decoder_io_input_0 = offset_map_io_output_0; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_1 = offset_map_io_output_1; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_2 = offset_map_io_output_2; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_3 = offset_map_io_output_3; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_4 = offset_map_io_output_4; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_5 = offset_map_io_output_5; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_6 = offset_map_io_output_6; // @[KeyDecoder.scala 43:34]
  assign length_varint_decoder_io_input_7 = offset_map_io_output_7; // @[KeyDecoder.scala 43:34]
  assign offset_map_clock = clock;
  assign offset_map_reset = reset;
  assign offset_map_io_input_0 = io_input_0; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_1 = io_input_1; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_2 = io_input_2; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_3 = io_input_3; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_4 = io_input_4; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_5 = io_input_5; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_6 = io_input_6; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_input_7 = io_input_7; // @[KeyDecoder.scala 42:23]
  assign offset_map_io_offset = varint_decode_io_bytes_read; // @[KeyDecoder.scala 41:24]
  always @(posedge clock) begin
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_6) begin
          $fwrite(32'h80000002,"KeyDecoder:io.wire_type: %d\n",io_wire_type); // @[KeyDecoder.scala 62:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_6) begin
          $fwrite(32'h80000002,"KeyDecoder:io.field_number: %d\n",io_field_number); // @[KeyDecoder.scala 63:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_6) begin
          $fwrite(32'h80000002,"KeyDecoder:io.bytes_read: %d\n",io_bytes_read); // @[KeyDecoder.scala 64:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T_6) begin
          $fwrite(32'h80000002,"KeyDecoder:io.value_size: %d\n",io_value_size); // @[KeyDecoder.scala 65:9]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
  end
endmodule
