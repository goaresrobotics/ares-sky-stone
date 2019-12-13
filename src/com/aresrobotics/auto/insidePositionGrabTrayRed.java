package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionGrabTrayRed")
public class insidePositionGrabTrayRed extends Auto {

    public void run(){

        encoderDrive(0.3, 8, 8, 2);
        turn(-90,3);
        encoderDrive(0.3, 55, 55, 7);
        turn(-179.69,3);
        encoderDrive(0.3, -20, -20, 3);
        trayGrab();
        encoderDrive(0.3, 20, 20,  3);
        turn(90,3);
        trayRelease();
        encoderDrive(0.3, 50, 50, 7);

    }

}
