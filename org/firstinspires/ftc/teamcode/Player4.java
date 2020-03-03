package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.io.FileInputStream;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="TeleOp Player 4", group="Iterative Opmode")
public class Player4 extends Player {
    public Player4() {
        super("/storage/emulated/0/recorded4.bin", false);
    }
}
