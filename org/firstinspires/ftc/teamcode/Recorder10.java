package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 10", group="Iterative Opmode")
public class Recorder10 extends Recorder {
    public Recorder10() {
        super("/storage/emulated/0/recorded10.bin");
    }
}
