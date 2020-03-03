package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftccommon.SoundPlayer;
import java.io.File;


@TeleOp(name="Drive Main", group="Iterative Opmode")
public class DriveMain extends OpMode
{
    public static double frontLeftPower;
    public static double frontRightPower;
    public static double backLeftPower;
    public static double backRightPower;

    protected ElapsedTime runtime = new ElapsedTime();
    protected DcMotor frontLeft = null;
    protected DcMotor frontRight = null;
    protected DcMotor backLeft = null;
    protected DcMotor backRight = null;
    private Servo claw;
    private Servo hook;
    private Servo hook2;
    private Servo markerer;
    private DcMotor linearSlide;
    private Servo armRotator;
    private Servo skyClaw;

    private int curDrive;
    protected DriveControl[] driveModes = {new MCModeDrive(), new TankModeDrive()};
    private ArrayList<DriveMod> curMods = new ArrayList<>();
    
    private boolean bDown;
    private boolean aDown;
    private boolean xDown;
    private boolean yDown;

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");
       
        bDown = false;
       
        frontLeft  = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft  = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        armRotator = hardwareMap.get(Servo.class, "REV");
        skyClaw = hardwareMap.get(Servo.class, "sky_claw");
        
        claw = hardwareMap.get(Servo.class, "claw");
        hook = hardwareMap.get(Servo.class, "hook");
        hook2 = hardwareMap.get(Servo.class, "hook2");
        markerer = hardwareMap.get(Servo.class, "markerer");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        curMods.add(new TriggerMod());
        curMods.add(new ConstantMod(0.25));
        
        // Audio.play(new File("/sdcard/FIRST/blocks/sounds/R2-D2-beep-1.wav"), hardwareMap); // Happy beep

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }
    
    public float clawPos;
    public boolean hookPos;
    public boolean markererPos;
    public float armPos;
    public boolean skyClawPos;

    @Override
    public void loop() {
        if(gamepad1.b && !bDown) {
            curDrive++;
            if(curDrive == driveModes.length) curDrive = 0;
        }
       
        driveModes[curDrive].processInput(gamepad1);
       
        for(int i = 0; i < curMods.size(); i++) {
            curMods.get(i).modify(gamepad1, gamepad2);
        }
        
        clawPos -= (gamepad2.left_trigger - gamepad2.right_trigger)/15;
        if(clawPos < 0 || Float.isNaN(clawPos)) clawPos = 0;
        else if(clawPos > 1) clawPos = 1;
        
        hookPos = hookPos ^ (gamepad2.a && !aDown);
        aDown = gamepad2.a;
        
        markererPos = markererPos ^ (gamepad2.y && !yDown);
        yDown = gamepad2.y;
        
        skyClawPos = skyClawPos ^ (gamepad2.x && !xDown);
        xDown = gamepad2.x;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
        linearSlide.setPower(gamepad2.left_stick_y*0.9);
        armPos -= gamepad2.right_stick_y/16;
        if(armPos < 0) armPos = 0;
        if(armPos > 1) armPos = 1;
        armRotator.setPosition(armPos);
        // armRotator.setPower(gamepad2.right_stick_y/8);
        
        claw.setPosition(clawPos);
        hook.setPosition(hookPos ? 0.6 : 0);
        hook2.setPosition(hookPos ? 0.4 : 1);
        markerer.setPosition(markererPos ? 0.35 : .65)
    ;
        skyClaw.setPosition(skyClawPos ? 0 : 0.75);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "fl (%.2f), fr (%.2f), bl (%.2f), br (%.2f)", frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.addData("Drive mode", driveModes[curDrive].getClass().getSimpleName());    
        bDown = gamepad1.b;
    }

    @Override
    public void stop() {
    }
}
