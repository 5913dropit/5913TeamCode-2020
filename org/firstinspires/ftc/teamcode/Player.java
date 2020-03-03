package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.io.FileInputStream;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous(name="TeleOp Player", group="Iterative Opmode")
public class Player extends DriveMain {
    public ArrayList<Float> g1_left_stick_x = new ArrayList<>();
    public ArrayList<Float> g1_left_stick_y = new ArrayList<>();
    public ArrayList<Float> g1_right_stick_x = new ArrayList<>();
    public ArrayList<Float> g1_right_stick_y = new ArrayList<>();
    public ArrayList<Float> g1_left_trigger = new ArrayList<>();
    public ArrayList<Float> g1_right_trigger = new ArrayList<>();
    public ArrayList<Integer> g1_buttons = new ArrayList<>();
    public ArrayList<Float> g2_left_stick_x = new ArrayList<>();
    public ArrayList<Float> g2_left_stick_y = new ArrayList<>();
    public ArrayList<Float> g2_right_stick_x = new ArrayList<>();
    public ArrayList<Float> g2_right_stick_y = new ArrayList<>();
    public ArrayList<Float> g2_left_trigger = new ArrayList<>();
    public ArrayList<Float> g2_right_trigger = new ArrayList<>();
    public ArrayList<Integer> g2_buttons = new ArrayList<>();
    public ArrayList<Integer> frontLeftPos = new ArrayList<>();
    public ArrayList<Integer> frontRightPos = new ArrayList<>();
    public ArrayList<Integer> backLeftPos = new ArrayList<>();
    public ArrayList<Integer> backRightPos = new ArrayList<>();
    public FileInputStream fileInput;
    public int iterator = 0;
    public int i = 0;
    public String fileName;
    public boolean flipX;
    public int version;
    
    public Player(String fileName, boolean flipX) {
        this.fileName = fileName;
        this.flipX = flipX;
    }
    
    @Override
    public void init() {
        // Format: 6 little-endian floats, one byte for buttons, repeat twice for controllers.
        // Version 1 additions: 4 little-endian floats for encoder positions every 100 frames
        // Load file
        try {
            fileInput = new FileInputStream(new File(fileName));
            i = 0;
            version = fileInput.read();
            if(version > 1) {
                for(;;); // FIXME: Make the program end
            }
        } catch(Exception e) {
            
        }
        // Replace gamepads with blanks
        super.gamepad1 = new Gamepad();
        super.gamepad2 = new Gamepad();
        super.init();
    }
    
    long lastTime = -1;
    
    @Override
    public void init_loop() {
        int prevI = i;
        try {
            int hackByte;
            int j = 0;
            while(j++ < 120 && (hackByte = fileInput.read()) >= 0) {
                g1_left_stick_x.add(Float.intBitsToFloat(hackByte | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g1_left_stick_y.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g1_right_stick_x.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g1_right_stick_y.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g1_left_trigger.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g1_right_trigger.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g1_buttons.add(fileInput.read());
                g2_left_stick_x.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g2_left_stick_y.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g2_right_stick_x.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g2_right_stick_y.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g2_left_trigger.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g2_right_trigger.add(Float.intBitsToFloat(fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)));
                g2_buttons.add(fileInput.read());
                i++;
                telemetry.addData("Status" + j, i);
                telemetry.update();
                if(version > 0) {
                    frontLeftPos.add((fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)) + frontLeft.getCurrentPosition());
                    frontRightPos.add((fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)) + frontRight.getCurrentPosition());
                    backLeftPos.add((fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)) + backLeft.getCurrentPosition());
                    backRightPos.add((fileInput.read() | (fileInput.read() << 8) | (fileInput.read() << 16) | (fileInput.read() << 24)) + backRight.getCurrentPosition());
                }
            }
        } catch(Exception e) {
            telemetry.addData("Status", "Could not read file!");
        }
        if(prevI == i)
            telemetry.addData("Status", "Done!");
        telemetry.update();
        iterator = 0;
        lastTime = System.currentTimeMillis();
    }
    
    @Override
    public void loop() {
        try {
        if(iterator < g1_left_stick_x.size()) {
            // Set gamepad state
            /*if(version == 0) */super.gamepad1.left_stick_x  = (flipX ? -1 : 1) * g1_left_stick_x.get(iterator);
            /*if(version == 0) */super.gamepad1.left_stick_y  = g1_left_stick_y.get(iterator);
            /*if(version == 0) */super.gamepad1.right_stick_x = (flipX ? -1 : 1) * g1_right_stick_x.get(iterator);
            /*if(version == 0) */super.gamepad1.right_stick_y = g1_right_stick_y.get(iterator);
            super.gamepad1.left_trigger  = g1_left_trigger.get(iterator);
            super.gamepad1.right_trigger = g1_right_trigger.get(iterator);
            super.gamepad1.a           = ((g1_buttons.get(iterator)>>0) & 1) != 0;
            super.gamepad1.b           = ((g1_buttons.get(iterator)>>1) & 1) != 0;
            super.gamepad1.x           = ((g1_buttons.get(iterator)>>2) & 1) != 0;
            super.gamepad1.y           = ((g1_buttons.get(iterator)>>3) & 1) != 0;
            super.gamepad1.dpad_up     = ((g1_buttons.get(iterator)>>4) & 1) != 0;
            super.gamepad1.dpad_down   = ((g1_buttons.get(iterator)>>5) & 1) != 0;
            super.gamepad1.dpad_left   = ((g1_buttons.get(iterator)>>6) & 1) != 0;
            super.gamepad1.dpad_right  = ((g1_buttons.get(iterator)>>7) & 1) != 0;
    
            super.gamepad2.left_stick_x  = g2_left_stick_x.get(iterator);
            super.gamepad2.left_stick_y  = g2_left_stick_y.get(iterator);
            super.gamepad2.right_stick_x = g2_right_stick_x.get(iterator);
            super.gamepad2.right_stick_y = g2_right_stick_y.get(iterator);
            super.gamepad2.left_trigger  = g2_left_trigger.get(iterator);
            super.gamepad2.right_trigger = g2_right_trigger.get(iterator);
            super.gamepad2.a           = ((g2_buttons.get(iterator)>>7) & 1) != 0;
            super.gamepad2.b           = ((g2_buttons.get(iterator)>>6) & 1) != 0;
            super.gamepad2.x           = ((g2_buttons.get(iterator)>>5) & 1) != 0;
            super.gamepad2.y           = ((g2_buttons.get(iterator)>>4) & 1) != 0;
            super.gamepad2.dpad_up     = ((g2_buttons.get(iterator)>>3) & 1) != 0;
            super.gamepad2.dpad_down   = ((g2_buttons.get(iterator)>>2) & 1) != 0;
            super.gamepad2.dpad_left   = ((g2_buttons.get(iterator)>>1) & 1) != 0;
            super.gamepad2.dpad_right  = ((g2_buttons.get(iterator)>>0) & 1) != 0;
    
            iterator++;
            super.loop();
            
            telemetry.addData("5913 is the best team", iterator);
            
            if(version > 0) {
                int flCurrent = super.frontLeft.getCurrentPosition();
                int frCurrent = super.frontRight.getCurrentPosition();
                int blCurrent = super.backLeft.getCurrentPosition();
                int brCurrent = super.backRight.getCurrentPosition();
                int flTarget = frontLeftPos.get(iterator);
                int frTarget = frontRightPos.get(iterator);
                int blTarget = backLeftPos.get(iterator);
                int brTarget = backRightPos.get(iterator);
                double flPower = super.frontLeft.getPower();
                double frPower = super.frontRight.getPower();
                double blPower = super.backLeft.getPower();
                double brPower = super.backRight.getPower();
                telemetry.addData("FrontLeft", flCurrent-flTarget + ", " + flPower);
                telemetry.addData("FrontRight", frCurrent-frTarget + ", " + frPower);
                telemetry.addData("BackLeft", blCurrent-blTarget + ", " + blPower);
                telemetry.addData("BackRight", brCurrent-brTarget + ", " + brPower);
                super.frontLeft.setPower(flPower-(flCurrent-flTarget)/50f);
                super.frontRight.setPower(frPower-(frCurrent-frTarget)/50f);
                super.backLeft.setPower(blPower-(blCurrent-blTarget)/50f);
                super.backRight.setPower(brPower-(brCurrent-brTarget)/50f);
            }
            
            long curTime = System.currentTimeMillis();
            try { Thread.sleep(lastTime-curTime+50); } catch(Exception e) {}
            lastTime = System.currentTimeMillis();
        }
        } catch(Exception e) {}
    }
}
