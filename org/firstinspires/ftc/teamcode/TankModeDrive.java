package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class TankModeDrive extends DriveControl {
    public void processInput(Gamepad gamepad) {
        DriveMain.frontLeftPower = (-gamepad.left_stick_x+gamepad.left_stick_y);
        DriveMain.backLeftPower = (gamepad.left_stick_x+gamepad.left_stick_y);
        DriveMain.frontRightPower = (gamepad.right_stick_x+gamepad.right_stick_y);
        DriveMain.backRightPower = (-gamepad.right_stick_x+gamepad.right_stick_y);
    }
}
