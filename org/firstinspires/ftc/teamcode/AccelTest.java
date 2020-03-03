package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp

public class AccelTest extends LinearOpMode {
    public int dist;
    public int Rotations;
    
  private DcMotor back_right;
  private DcMotor back_left;
  private DcMotor front_right;
  private DcMotor front_left;
    
    public void runOpMode() {
        back_right = hardwareMap.dcMotor.get("back_right");
        back_left = hardwareMap.dcMotor.get("back_left");
        front_right = hardwareMap.dcMotor.get("front_right");
        front_left = hardwareMap.dcMotor.get("front_left");
        back_left.setDirection(DcMotorSimple.Direction.REVERSE);
        front_left.setDirection(DcMotorSimple.Direction.REVERSE);
        
        waitForStart();
        
        while(opModeIsActive()) {
            dist += (int)(gamepad1.left_stick_y*20);
            if(gamepad1.a) {
                Rotations = dist;
                Forward_Fast();
                dist = 0;
            }
            telemetry.addData("Dist", dist);
            telemetry.update();
            sleep(10);
        }
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
          if(power > 0.8) power = 0.8;
          if(back_right.getCurrentPosition() >= (back_right.getTargetPosition() - 1000)) power -= 0.05;
          if(power < 0.1) power = 0.1;
          sleep(1);
        }
       
        back_right.setPower(0);
        back_left.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);
         
        sleep(1);
    }
}
