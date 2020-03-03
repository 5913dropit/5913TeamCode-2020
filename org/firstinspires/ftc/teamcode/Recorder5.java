package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Recorder 5", group="Iterative Opmode")
public class Recorder5 extends Recorder {
    public Recorder5() {
        super("/storage/emulated/0/recorded5.bin");
    }
}
