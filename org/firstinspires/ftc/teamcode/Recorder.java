package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import java.io.FileOutputStream;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="Recorder", group="Iterative Opmode")
public class Recorder extends DriveMain {
    private FileOutputStream fileOut;
    public String fileName;
    public Recorder(String fileName) {
        this.fileName = fileName;
    }
    
    private static final byte VERSION = 1;
    private int flStart;
    private int frStart;
    private int blStart;
    private int brStart;
    
    @Override
    public void init() {
        super.init();
        telemetry.addData("RecorderStatus", "Starting...");
        try {
            fileOut = new FileOutputStream(new File(fileName));
            fileOut.write(new byte[] { VERSION });
        } catch(Exception e) {
            telemetry.addData("RecorderStatus", "Can't make file");
        }
        if(VERSION > 0) {
            flStart = frontLeft.getCurrentPosition();
            frStart = frontRight.getCurrentPosition();
            blStart = backLeft.getCurrentPosition();
            brStart = backRight.getCurrentPosition();
        }
    }
    
    public void writeInt(int x) {
        try {
            fileOut.write(new byte[] {
                (byte)((x >>  0) & 0xFF),
                (byte)((x >>  8) & 0xFF),
                (byte)((x >> 16) & 0xFF),
                (byte)((x >> 24) & 0xFF)
            });
        } catch(Exception e) {}
    }
    
    public void writeFloat(float x) {
        writeInt(Float.floatToIntBits(x));
    }
    
    long lastTime = -1;
    
    public void init_loop() {
        lastTime = System.currentTimeMillis();
    }
    
    
    public void writeByte(byte x) {
        try {
            fileOut.write(new byte[] {x});
        } catch(Exception e) {}
    }
    
    int index = 0;
    
    @Override
    public void loop() {
        super.loop();
        writeFloat(gamepad1.left_stick_x);
        writeFloat(gamepad1.left_stick_y);
        writeFloat(gamepad1.right_stick_x);
        writeFloat(gamepad1.right_stick_y);
        writeFloat(gamepad1.left_trigger);
        writeFloat(gamepad1.right_trigger);
        byte g1_abxyudlr = (byte) (
            (gamepad1.a          ? 0b10000000 : 0) |
            (gamepad1.b          ? 0b01000000 : 0) |
            (gamepad1.x          ? 0b00100000 : 0) |
            (gamepad1.y          ? 0b00010000 : 0) |
            (gamepad1.dpad_up    ? 0b00001000 : 0) |
            (gamepad1.dpad_down  ? 0b00000100 : 0) |
            (gamepad1.dpad_left  ? 0b00000010 : 0) |
            (gamepad1.dpad_right ? 0b00000001 : 0));
        writeByte(g1_abxyudlr);
        writeFloat(gamepad2.left_stick_x);
        writeFloat(gamepad2.left_stick_y);
        writeFloat(gamepad2.right_stick_x);
        writeFloat(gamepad2.right_stick_y);
        writeFloat(gamepad2.left_trigger);
        writeFloat(gamepad2.right_trigger);
        byte g2_abxyudlr = (byte) (
            (gamepad2.a          ? 0b10000000 : 0) |
            (gamepad2.b          ? 0b01000000 : 0) |
            (gamepad2.x          ? 0b00100000 : 0) |
            (gamepad2.y          ? 0b00010000 : 0) |
            (gamepad2.dpad_up    ? 0b00001000 : 0) |
            (gamepad2.dpad_down  ? 0b00000100 : 0) |
            (gamepad2.dpad_left  ? 0b00000010 : 0) |
            (gamepad2.dpad_right ? 0b00000001 : 0));
        writeByte(g2_abxyudlr);
        long curTime = System.currentTimeMillis();
        try { Thread.sleep(lastTime-curTime+50); } catch(Exception e) {}
        lastTime = System.currentTimeMillis();
        if(VERSION > 0) {
            writeInt(super.frontLeft.getCurrentPosition()-flStart);
            writeInt(super.frontRight.getCurrentPosition()-frStart);
            writeInt(super.backLeft.getCurrentPosition()-blStart);
            writeInt(super.backRight.getCurrentPosition()-brStart);
        }
        index++;
    }
    
    @Override
    public void stop() {
        try {
            fileOut.close();
        } catch(Exception e) {}
    }
}
