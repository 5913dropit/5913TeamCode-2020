package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "RedBlockStack (NEW)", group = "")
public class RedBlockStack extends LinearOpMode {

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

  /**
   * Describe this function...
   */
  private void Turn_Right() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(-0.3);
        back_left.setPower(0.3);
        front_right.setPower(-0.3);
        front_left.setPower(0.3);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Turn_Left() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(0.3);
        back_left.setPower(-0.3);
        front_right.setPower(0.3);
        front_left.setPower(-0.3);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
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
    sky_claw.setPosition(1);
    markerer.setPosition(0);
    hook.setPosition(0);
    hook2.setPosition(1);
    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        REV.setPosition(1);
        Rotations = 1100;
        Linear_Slide();
        Rotations = 2300;
        Forward();
        Rotations = 160;
        Forward_Slow();
        Rotations = 1000;
        Linear_Slide_DOWN();
        sleep(250);
        claw.setPosition(1);
        sleep(500);
        Rotations = 1500;
        Linear_Slide();
        Rotations = 1800;
        Turn_Right();
        Rotations = 150;
        Strafe_Right();
        Rotations = 1400;
        Linear_Slide_DOWN();
        Rotations = 4450;
        Forward_Fast();
        Rotations = 1200;
        Linear_Slide();
        Rotations = 400;
        Strafe_Right();
        Rotations = 800;
        Forward();
        Rotations = 750;
        Linear_Slide_DOWN();
        claw.setPosition(0);
        sleep(500);
        Rotations = 100;
        Backward();
        Rotations = 900;
        Linear_Slide();
        Rotations = 1000;
        Strafe_Left();
        Rotations = 1850;
        Backward_Fast();
        sleep(30000);
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */
  private void Forward() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(0.3);
        back_left.setPower(0.3);
        front_right.setPower(0.3);
        front_left.setPower(0.3);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Strafe_Left() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(-0.3);
        back_left.setPower(0.3);
        front_right.setPower(0.3);
        front_left.setPower(-0.3);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Strafe_Left_FAST() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(-0.6);
        back_left.setPower(0.6);
        front_right.setPower(0.6);
        front_left.setPower(-0.6);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Forward_Fast() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(0.6);
        back_left.setPower(0.6);
        front_right.setPower(0.6);
        front_left.setPower(0.6);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Forward_Slow() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition())) {
      back_right.setPower(0.1);
      back_left.setPower(0.1);
      front_right.setPower(0.1);
      front_left.setPower(0.1);
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Strafe_Right() {
    back_right.setTargetPosition(back_right.getCurrentPosition() + Rotations);
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(0.3);
        back_left.setPower(-0.3);
        front_right.setPower(-0.3);
        front_left.setPower(0.3);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Backward() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(-0.3);
        back_left.setPower(-0.3);
        front_right.setPower(-0.3);
        front_left.setPower(-0.3);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Backward_Fast() {
    back_right.setTargetPosition(back_right.getCurrentPosition() - Rotations);
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition())) {
      if (opModeIsActive() == false) {
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
      } else {
        back_right.setPower(-0.6);
        back_left.setPower(-0.6);
        front_right.setPower(-0.6);
        front_left.setPower(-0.6);
      }
    }
    back_right.setPower(0);
    back_left.setPower(0);
    front_right.setPower(0);
    front_left.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Linear_Slide() {
    linear_slide.setTargetPosition(linear_slide.getCurrentPosition() - Rotations);
    while (!(linear_slide.getCurrentPosition() <= linear_slide.getTargetPosition())) {
      linear_slide.setPower(-0.25);
      telemetry.addData("Rotations: ", linear_slide.getCurrentPosition());
    }
    linear_slide.setPower(0);
    sleep(500);
  }

  /**
   * Describe this function...
   */
  private void Linear_Slide_DOWN() {
    linear_slide.setTargetPosition(linear_slide.getCurrentPosition() + Rotations);
    while (!(linear_slide.getCurrentPosition() >= linear_slide.getTargetPosition())) {
      linear_slide.setPower(0.25);
      telemetry.addData("Rotations: ", linear_slide.getCurrentPosition());
    }
    linear_slide.setPower(0);
    sleep(500);
  }
}
