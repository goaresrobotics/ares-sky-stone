package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "outsidePositionGrabTrayRed")
public class outsidePositionGrabTrayRed extends Auto {

    public void run(){

        sleep(5000);
        encoderDrive(0.3, 77, 77, 15);
        turn(-90);
        encoderDrive(0.3, -10, -10, 5);
        //trayGrab(true);
        encoderDrive(0.3, 10, 10, 5);
        turn(90);
        //trayGrab(false);
        encoderDrive(0.3, 60, 60, 15);

    }

}
