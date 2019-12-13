package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionGrabTrayBlue")
public class insidePositionGrabTrayBlue extends Auto {

    public void run(){

        encoderDrive(0.4, 8, 8, 2);
        turn(90, 3);
        encoderDrive(0.4, 55, 55, 7);
        turn(178,3);
        encoderDrive(0.2, -23, -23, 3);
        trayGrab();
        encoderDrive(0.3, 20, 20,  7);
        turn(-95,3);
        encoderDrive(0.1,-2,-2,1);
        trayRelease();
        encoderDrive(0.4, 38, 38, 7);

    }
}