package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import android.graphics.Color;

@Autonomous(name = "Blue Skies", group = "")
public class BlueSkies extends LinearOpMode {

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
    
    
    
    Rotations = 2550;
    Backward();
    Rotations = 200;
    Backward();
    int backtrack = 0;
    float[] lellow = {0,0,0};
    while(backtrack != 2) {
      Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, lellow);
      if(lellow[1] <= 0.3) break;
      Rotations = 890;
      Strafe_Left_FAST();
      backtrack = backtrack+1;
    }
    
    Rotations = 100;
    Backward();
    
    sky_claw.setPosition(0);
    sleep(500);
    
    Rotations = 600;
    Forward();
    
    Rotations = backtrack * 890;
    Strafe_Right_FAST();
    if(backtrack ==2){
      Rotations = 50;
      Forward();
      Rotations = 50;
      Turn_Left();
      
    }
    
    Rotations = 1700;
    Turn_Left();
    
    Rotations = 3550;
    Backward_Fast();
    
    
    sky_claw.setPosition(.75);
    sleep(500);
    if(backtrack < 1){
      Rotations = 1200;
      Forward_Fast();
      Rotations = 750;
      Strafe_Left_FAST();
      Rotations = 250;
      Strafe_Right_FAST();
      
      Rotations = 4650;
      Forward_Fast();
      Rotations = 1700;
      Turn_Right();
      //Rotations = 1750;
      //Strafe_Left_FAST();
      //Rotations = 1450;
      //Strafe_Right_FAST();
      Rotations = 400;
      Backward();
      sky_claw.setPosition(0);
      sleep(500);
      Rotations = 700;
      Forward();
      Rotations = 1650;
      Turn_Left();
      Rotations = 5800;
      Backward_Fast();
      sky_claw.setPosition(.75);
      sleep(500);
      Rotations = 800;
      Forward();
      //Rotations = 3500;
      //Turn_Left();
      
    } else if (backtrack==1){
      Rotations = 250;
      Backward();
      Rotations = 1200;
      Forward_Fast();
      Rotations = 750;
      Strafe_Left_FAST();
      Rotations = 250;
      Strafe_Right_FAST();
      
     
      Rotations = 4750;
      Forward_Fast();
      Rotations = 1700;
      Turn_Right();
      //Rotations = 1750;
      //Strafe_Left_FAST();
      //Rotations = 500;
      //Strafe_Right_FAST();
      Rotations = 300;
      Backward();
      Rotations = 850;
      Strafe_Left_FAST();
      Rotations = 200;
      Backward();
      
      sky_claw.setPosition(0);
      sleep(500);
      Rotations = 450;
      Forward();
      Rotations = 1700;
      Turn_Left();
      Rotations = 6700;
      Backward_Fast();
      sky_claw.setPosition(.75);
      sleep(500);
      Rotations = 670;
      Forward();
      //Rotations = 3500;
      //Turn_Left();
      
    } else if(backtrack==2) {
      Rotations = 500;
      Backward();
      Rotations = 1500;
      Forward_Fast();
      Rotations = 750;
      Strafe_Left_FAST();
      Rotations = 250;
      Strafe_Right_FAST();
      
      Rotations = 2350;
      Forward_Fast();
      Rotations = 1700;
      Turn_Right();
      Rotations = 400;
      Backward();
      sky_claw.setPosition(0);
      sleep(500);
      Rotations = 400;
      Forward();
      Rotations = 1700;
      Turn_Left();
      Rotations = 3950;
      Backward_Fast();
      sky_claw.setPosition(.75);
      sleep(500);
      
      Rotations = 900;
      Forward();
      
      
    }
    
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
    double power = Rotations > 1000 ? 0.25 : 0.8;
    while (!(back_right.getCurrentPosition() >= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(power);
      back_left.setPower(power);
      front_right.setPower(power);
      front_left.setPower(power);
      power += 0.01;
      if(power > 0.85) power = 0.85;
      if(back_right.getCurrentPosition() >= (back_right.getTargetPosition() - 1000)) power -= 0.05;
      if(power < 0.25) power = 0.25;
      sleep(1);
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
    double power = Rotations > 1000 ? -0.4 : -0.8;
    while (!(back_right.getCurrentPosition() <= back_right.getTargetPosition()) && opModeIsActive()) {
      back_right.setPower(power);
      back_left.setPower(power);
      front_right.setPower(power);
      front_left.setPower(power);
      power -= 0.01;
      if(power < -0.85) power = -0.85;
      if(back_right.getCurrentPosition() <= (back_right.getTargetPosition() + 1000)) power += 0.05;
      if(power > -0.25) power = -0.25;
      sleep(1);
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
      back_right.setPower(-0.1);
      back_left.setPower(-0.1);
      front_right.setPower(-0.1);
      front_left.setPower(-0.1);
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
