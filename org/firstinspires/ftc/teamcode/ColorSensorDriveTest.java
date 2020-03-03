package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import android.graphics.Color;

@TeleOp(name="Color Sensor Yoy", group="Iterative Opmode")
public class ColorSensorDriveTest extends DriveMain {
    private ColorSensor sensor;
    
    public void init() {
        sensor = hardwareMap.get(ColorSensor.class, "color");
        sensor.enableLed(true);
        
        super.init();
    }
    
    float[] test = {0,0,0};
    
    public void loop() {
        Color.RGBToHSV(sensor.red() * 8, sensor.green() * 8, sensor.blue() * 8, test);
        telemetry.addData("HSV", test[0] + ", " + test[1] + ", " + test[2]);
        telemetry.addData("LELLOW", test[1] > 0.5);
        super.loop();
    }
}
