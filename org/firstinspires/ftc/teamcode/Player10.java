package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.io.FileInputStream;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="TeleOp Player 10", group="Iterative Opmode")
public class Player10 extends Player {
    public Player10() {
        super("/storage/emulated/0/recorded10.bin", false);
    }
}
