`include "KeyDecoder.v"

module top(input clk,
           input  [7:0]  io_input_0,
           input  [7:0]  io_input_1,
           input  [7:0]  io_input_2,
           input  [7:0]  io_input_3,
           output [2:0]  io_wire_type,
           output [7:0]  io_field_number,
           output [7:0]  io_bytes_read,
           output [15:0] io_value_size);

    KeyDecoder decoder_1(
        .reset(1),
        .clock(clk),
        .io_input_0(io_input_0),
        .io_input_1(io_input_1),
        .io_input_2(io_input_2),
        .io_input_3(io_input_3),

        .io_wire_type(io_wire_type),
        .io_field_number(io_field_number),
        .io_bytes_read(io_bytes_read),
        .io_value_size(io_value_size)
    );

endmodule
