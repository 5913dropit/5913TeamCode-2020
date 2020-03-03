package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 3", group="Iterative Opmode")
public class Recorder3 extends Recorder {
    public Recorder3() {
        super("/storage/emulated/0/recorded3.bin");
    }
}
