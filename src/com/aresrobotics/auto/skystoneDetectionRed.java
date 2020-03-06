package com.aresrobotics.auto;

public class skystoneDetectionRed extends Auto {

    public void run(){

        encoderDrive(0.4, 0.4, 24, 24, 3);
        detectSkystone(false, true);
    }

}
