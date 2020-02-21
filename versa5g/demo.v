`include "KeyDecoder.v"


module top(input clk, output [7:0] led, output [13:0] disp);

    KeyDecoder decoder_1(
	.reset(1),
	.clock(clk),
	.io_input_0(0),
        .io_input_1(0),
        .io_input_2(0),
        .io_input_3(0),
        .io_input_4(0),
        .io_input_5(0),
        .io_input_6(0),
        .io_input_7(0),
    );

endmodule

