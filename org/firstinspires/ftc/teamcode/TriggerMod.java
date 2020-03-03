package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class TriggerMod extends DriveMod {
    public void modify(Gamepad gamepad1, Gamepad gamepad2) {
        DriveMain.frontLeftPower *= (2*gamepad1.right_trigger+2)/(4/((4-3*gamepad1.left_trigger)));
        DriveMain.frontRightPower *= (2*gamepad1.right_trigger+2)/(4/((4-3*gamepad1.left_trigger)));
        DriveMain.backLeftPower *= (2*gamepad1.right_trigger+2)/(4/((4-3*gamepad1.left_trigger)));
        DriveMain.backRightPower *= (2*gamepad1.right_trigger+2)/(4/((4-3*gamepad1.left_trigger)));
    }
}
