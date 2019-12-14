package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "outsidePositionGrabTrayBlue")
public class outsidePositionGrabTrayBlue extends Auto {

    //Tested

    public void run(){

        sleep(2500);
        encoderDrive(0.4, 8, 8, 2);
        turn(88, 3);
        encoderDrive(0.4, 70, 70, 7);
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