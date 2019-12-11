package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionGrabTrayBlue")
public class insidePositionGrabTrayBlue extends Auto {

    public void run(){

        encoderDrive(0.3, 2, 2, 1);
        turn(90);
        encoderDrive(0.3, 70, 70, 15);
        turn(179.9);
        encoderDrive(0.3, -10, -10, 5);
       // trayGrab(true);
        encoderDrive(0.3, 10, 10,  5);
        turn(90);
        //trayGrab((false));
        encoderDrive(0.3, 50, 50, 15);

    }
}