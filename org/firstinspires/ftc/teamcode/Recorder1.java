package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 1", group="Iterative Opmode")
public class Recorder1 extends Recorder {
    public Recorder1() {
        super("/storage/emulated/0/recorded1.bin");
    }
}
