package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class AutoModeDrive extends DriveControl {
    public void processInput(Gamepad gamepad) {
        DriveMain.frontLeftPower = 1;
        DriveMain.frontRightPower = 1;
        DriveMain.backLeftPower = 1;
        DriveMain.backRightPower = 1;
    }
}
