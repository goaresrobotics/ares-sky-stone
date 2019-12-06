package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionGrabTrayRed")
public class insidePositionGrabTrayRed extends Auto {

    public void run(){

        encoderDrive(0.3, 1, 1, 0.5);
        turn(-90, true);
        encoderDrive(0.3, 1, 1, 0.5);
       // trayGrab(true);
        encoderDrive(0.3, 1, 1, 0.5);
        turn(-90, true);
     //   trayGrab(false);
        encoderDrive(0.3, 1, 1, 0.5);

    }

}
