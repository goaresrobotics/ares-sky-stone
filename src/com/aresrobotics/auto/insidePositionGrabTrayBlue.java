package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionGrabTrayBlue")
public class insidePositionGrabTrayBlue extends Auto {

    public void run(){

        encoderDrive(0.3, 77, 77, 0.5);
        turn(90, false);
        encoderDrive(0.3, -10, -10, 0.5);
       // trayGrab(true);
        encoderDrive(0.3, 4, 4, 0.5);
        turn(-90, true);
        //trayGrab((false));
        encoderDrive(1, 1, 1, 0.5);

    }
}