package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "skystoneDetectionBlue")
public class skystoneDetectionBlue extends Auto {

    public void run(){

        encoderDrive(0.2, 0.2, 14, 14, 3);
        turn(0, 1, false);
        detectSkystone(true, false);
    }

}
