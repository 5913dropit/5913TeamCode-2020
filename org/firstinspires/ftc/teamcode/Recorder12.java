package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 12", group="Iterative Opmode")
public class Recorder12 extends Recorder {
    public Recorder12() {
        super("/storage/emulated/0/recorded12.bin");
    }
}
