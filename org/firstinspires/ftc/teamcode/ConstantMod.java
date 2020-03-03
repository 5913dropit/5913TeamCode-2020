package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;


public class ConstantMod extends DriveMod {
    public double constant;
    
    public ConstantMod(double constant) {
        this.constant = constant;
    }
    
    public void modify(Gamepad gamepad1, Gamepad gamepad2) {
        DriveMain.frontLeftPower *= constant;
        DriveMain.frontRightPower *= constant;
        DriveMain.backLeftPower *= constant;
        DriveMain.backRightPower *= constant;
    }
}
