package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import android.graphics.Color;

@Autonomous(name = "Red Platform Turn", group = "")
public class RedPlatfromTurn extends LinearOpMode {

  private int Rotations;

  private DcMotor back_right;
  private DcMotor back_left;
  private DcMotor front_right;
  private DcMotor front_left;
  private DcMotor linear_slide;
  private Servo REV;
  private Servo claw;
  private Servo sky_claw;
  private Servo markerer;
  private Servo hook;
  private Servo hook2;
  private ColorSensor color;
  
  @Override
  public void runOpMode() {
    back_right = hardwareMap.dcMotor.get("back_right");
    back_left = hardwareMap.dcMotor.get("back_left");
    front_right = hardwareMap.dcMotor.get("front_right");
    front_left = hardwareMap.dcMotor.get("front_left");
    linear_slide = hardwareMap.dcMotor.get("linear_slide");
    REV = hardwareMap.servo.get("REV");
    sky_claw = hardwareMap.servo.get("sky_claw");
    claw = hardwareMap.servo.get("claw");
    markerer = hardwareMap.servo.get("markerer");
    hook = hardwareMap.servo.get("hook");
    hook2 = hardwareMap.servo.get("hook2");
    color = hardwareMap.colorSensor.get("color");

    // Put initialization blocks here.
    Rotations = 0;
    back_left.setDirection(DcMotorSimple.Direction.REVERSE);
    front_left.setDirection(DcMotorSimple.Direction.REVERSE);
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    linear_slide.setPower(0);
    REV.setPosition(0);
    claw.setPosition(0);
    sky_claw.setPosition(.75);
    markerer.setPosition(0.65);
    hook.setPosition(0);
    hook2.setPosition(1);
    REV.setPosition(0);
    waitForStart();
    
    Rotations = 1300;
    Strafe_Left_FAST();
    Rotations = 2950;
    Backward();
    Rotations = 100;
    Backward_Slow();
    sleep(500);
    
    hook.setPosition(.5);
    hook2.setPosition(.5);
    
    sleep(500);
    Rotations = 1350;
    Forward_Fast();
    Rotations = 1300;
    Strafe_Right_FAST();
    
    Rotations = 1500;
    Turn_Right_Fast();
    sleep(500);
    
    hook.setPosition(0);
    hook2.setPosition(1);
    sleep(500);
    
    Rotations = 1000;
    Backward_Fast();
    
    Rotations = 2500;
    Strafe_Left_FAST();
    sleep(10000);
    
    Rotations = 4000;
    Forward();
    
    
    
    
    
    
    
    
    
    
    // telemetry.update();
  }
  
  public static final int SLEEP_TIME = 300;
  
  private void Turn_Right() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.3);
      back_left.setPower(0.3);
      front_right.setPower(-0.3);
      front_left.setPower(0.3);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }
  private void Turn_Right_Fast() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.6);
      back_left.setPower(0.6);
      front_right.setPower(-0.6);
      front_left.setPower(0.6);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Turn_Left() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.3);
      back_left.setPower(-0.3);
      front_right.setPower(0.3);
      front_left.setPower(-0.3);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }
  private void Turn_Left_Fast() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.6);
      back_left.setPower(-0.6);
      front_right.setPower(0.6);
      front_left.setPower(-0.6);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Strafe_Left_FAST() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.6);
      back_left.setPower(0.6);
      front_right.setPower(0.6);
      front_left.setPower(-0.6);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }
  
  private void Forward_Fast() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.6);
      back_left.setPower(0.6);
      front_right.setPower(0.6);
      front_left.setPower(0.6);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Forward() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.3);
      back_left.setPower(0.3);
      front_right.setPower(0.3);
      front_left.setPower(0.3);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Strafe_Left() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.3);
      back_left.setPower(0.3);
      front_right.setPower(0.3);
      front_left.setPower(-0.3);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Strafe_Left_Slowish() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.2);
      back_left.setPower(0.2);
      front_right.setPower(0.2);
      front_left.setPower(-0.2);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }
  
  private void Forward_Slow() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.1);
      back_left.setPower(0.1);
      front_right.setPower(0.1);
      front_left.setPower(0.1);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Strafe_Right() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.3);
      back_left.setPower(-0.3);
      front_right.setPower(-0.3);
      front_left.setPower(0.3);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }
  
  private void Strafe_Right_FAST() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(0.6);
      back_left.setPower(-0.6);
      front_right.setPower(-0.6);
      front_left.setPower(0.6);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

   private void Backward() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.3);
      back_left.setPower(-0.3);
      front_right.setPower(-0.3);
      front_left.setPower(-0.3);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Backward_Fast() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.6);
      back_left.setPower(-0.6);
      front_right.setPower(-0.6);
      front_left.setPower(-0.6);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

   private void Backward_Slow() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(-0.2);
      back_left.setPower(-0.2);
      front_right.setPower(-0.2);
      front_left.setPower(-0.2);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Linear_Slide() {
    linear_slide.setTargetPosition(linear_slide.getCurrentPosition() - Rotations);
    while (!(linear_slide.getCurrentPosition() <= linear_slide.getTargetPosition()) && opModeIsActive()) {
      linear_slide.setPower(-0.25);
    }
    linear_slide.setPower(0);
    sleep(SLEEP_TIME);
  }

  private void Linear_Slide_DOWN() {
    linear_slide.setTargetPosition(linear_slide.getCurrentPosition() + Rotations);
    while (!(linear_slide.getCurrentPosition() >= linear_slide.getTargetPosition()) && opModeIsActive()) {
      linear_slide.setPower(0.25);
    }
    linear_slide.setPower(0);
    sleep(SLEEP_TIME);
  }
}
