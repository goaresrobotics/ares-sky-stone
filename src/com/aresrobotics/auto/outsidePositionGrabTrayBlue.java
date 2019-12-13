package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "outsidePositionGrabTrayBlue")
public class outsidePositionGrabTrayBlue extends Auto {

    public void run(){

        sleep(5000);
        encoderDrive(0.3, 8, 8, 2);
        turn(90,3);
        encoderDrive(0.3, 79, 79, 7);
        turn(179.69,3);
        encoderDrive(0.3, -20, -20, 3);
        trayGrab();
        encoderDrive(0.3, 20, 20,  3);
        turn(-90,3);
        trayRelease();
        encoderDrive(0.3, 50, 50, 7);


    }
}