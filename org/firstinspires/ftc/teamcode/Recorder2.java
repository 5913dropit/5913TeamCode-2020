package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 2", group="Iterative Opmode")
public class Recorder2 extends Recorder {
    public Recorder2() {
        super("/storage/emulated/0/recorded2.bin");
    }
}
