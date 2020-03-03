package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@TeleOp(name = "CameraTest (Java Version)", group = "")
public class CameraTest extends LinearOpMode {
  private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
  private static final String LABEL_FIRST_ELEMENT = "Stone";
  private static final String LABEL_SECOND_ELEMENT = "Skystone";

  private static final String VUFORIA_KEY =
            "Acz/0w//////AAABmeOk+EtyC0Uvp/MWTB7/CzwpPu4r8AkwmB5sxumDOB4QRfB+6AQE8ZSIzh7coqzc94S6OvJZeEh3fsnpX6lqv4CdpzbWfXKqu3WndQqFEwhWcowjZKEn37LbWTjwrGia7A+hknUNgSSO7pj9Zc721A1Gi868eNrepT5oMBF8hJ4JWqLy5xXZIRvTCXJXcLrdnyDRGkGCf7bq7d1O/kbVFhbHRKVgeGpTrdkNpYhSMXN7nBzK0Zvc8wPN07MM4Oj3NtecdlmVC8WsusOnxz0GSmFjsKGvCQ/IPdE/rS9yectLtoLi+Tr28kVv4T3B5EdB2H6sPZFgEGNQVBLjsiIxnUHLci/Dean4WnJAfXUeIFJs";

  private VuforiaLocalizer vuforia;
  private TFObjectDetector tfod;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    List<Recognition> recognitions;
    
    initVuforia();
    
    if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
      initTfod();
    } else {
      telemetry.addData("Sorry!", "This device is not compatible with TFOD");
    }

    if (tfod != null) {
      tfod.activate();
    }
    
    telemetry.addData(">", "Press Play to start op mode");
    telemetry.update();
    
    waitForStart();
    
    while (opModeIsActive()) {
      recognitions = tfod.getRecognitions();
      if (recognitions.size() == 1) {
        telemetry.addData("Skystone status", "FOUND!");

      } else if (recognitions.size() > 1) {
        telemetry.addData("Skystone status", "Found too many");
      } else {
        telemetry.addData("Skystone status", "Found too few");
      }
      telemetry.update();
    }
    
    tfod.shutdown();
    /*
    comment
    */
  }
  
  private void initVuforia() {
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

    parameters.vuforiaLicenseKey = VUFORIA_KEY;
    parameters.cameraDirection = CameraDirection.BACK;

    vuforia = ClassFactory.getInstance().createVuforia(parameters);
  }

  private void initTfod() {
    int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
      "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfodParameters.minimumConfidence = 0.8;
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
  }
}
