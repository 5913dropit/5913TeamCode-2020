package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.io.File;

public class Audio {
    // Point to sound files on the phone's drive
    public static File goldFile   = new File("/sdcard/FIRST/blocks/sounds/gold.wav");
    public static File silverFile = new File("/sdcard/FIRST/blocks/sounds/silver.wav");

    public static void play(File file, HardwareMap hardwareMap) {
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, file);
    }
}
