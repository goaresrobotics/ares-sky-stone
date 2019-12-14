package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionGrabTrayBlue")
public class insidePositionGrabTrayBlue extends Auto {

    //Tested

    public void run(){

        encoderDrive(0.4, 8, 8, 2);
        turn(88, 3);
        encoderDrive(0.4, 50, 50, 7);
        turn(179.5,3);
        encoderDrive(0.2, -23, -23, 3);
        trayGrab();
        sleep(1000);
        encoderDrive(0.3, 22.5, 22.5,  7);
        turn(-110,3);
        encoderDrive(0.1,-8,-8,1.5);
        trayRelease();
        sleep(1000);
        encoderDrive(0.4, 33, 33, 7);
        turn(-90,3);

    }
}