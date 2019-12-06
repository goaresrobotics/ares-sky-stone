package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "outsidePositionGrabTrayBlue")
public class outsidePositionGrabTrayBlue extends Auto {

    public void run(){

        sleep(1000);
        encoderDrive(0.3, 77, 77, 0.5);
        turn(90, false);
        encoderDrive(0.3, -10, -10, 0.5);
       // trayGrab(true);
        turn(-90, false);
      //  trayGrab(false);
        encoderDrive(0.3, 1, 1, 0.5);


    }
}