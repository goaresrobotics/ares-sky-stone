package com.aresrobotics.auto;

public class skystoneDetectionBlue extends Auto {

    public void run(){

        encoderDrive(0.4, 0.4, 24, 24, 3);
        detectSkystone(true, false);
    }

}
