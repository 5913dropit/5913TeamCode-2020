package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.io.FileInputStream;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="TeleOp Player 12", group="Iterative Opmode")
public class Player12 extends Player {
    public Player12() {
        super("/storage/emulated/0/recorded12.bin", false);
    }
}
