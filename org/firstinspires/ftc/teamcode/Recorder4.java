package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 4", group="Iterative Opmode")
public class Recorder4 extends Recorder {
    public Recorder4() {
        super("/storage/emulated/0/recorded4.bin");
    }
}
