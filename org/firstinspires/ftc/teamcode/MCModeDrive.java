package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class MCModeDrive extends DriveControl {
    public void processInput(Gamepad gamepad) {
        DriveMain.frontLeftPower = (gamepad.left_stick_y-gamepad.left_stick_x-gamepad.right_stick_x);
        DriveMain.backLeftPower = (gamepad.left_stick_y-gamepad.left_stick_x+gamepad.right_stick_x);
        DriveMain.frontRightPower = (gamepad.left_stick_y+gamepad.left_stick_x+gamepad.right_stick_x);
        DriveMain.backRightPower = (gamepad.left_stick_y+gamepad.left_stick_x-gamepad.right_stick_x);
    }
}
