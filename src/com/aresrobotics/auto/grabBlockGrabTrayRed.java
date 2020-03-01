package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "grabBlockGrabTrayRed")
public class grabBlockGrabTrayRed extends Auto{

    @Override
    public void run() {

        encoderDrive(0.4, 0.4, 46.5, 46.5, 3);
        encoderDrive(0.4, 0.4, -6.5, -6.5, 2);
        turn(90, 2, false);
        intake(true, true);
        encoderDrive(0.4, 0.4, 8, 8, 2);
        encoderDrive(0.4, 0.4, -8, -8, 2);
        intake(false, false);
        turn(-90, 3, true);
        strafe(true, 1000);
        turn(-90, 2, true);
        encoderDrive(0.8, 0.8, 72, 72, 5);
        turn(-177.5, 2, false);
        encoderDrive(0.6, 0.6, -10, -10, 2);
        trayGrab();
        sleep(200);
        encoderDrive(0.15, 0.575, 57.5,57.5, 8);
        turn(90, 1, true);
        encoderDrive(0.4, 0.4, -15, -15, 3);
        turn(90,1, true);
        trayRelease();
        encoderDrive(0.4, 0.4, 10, 10, 5);
        turn(-90, 3, true);
        intake(true, false);
        encoderDrive(0.4, 0.4,-25, -25, 6);
        intake(false,false);
        trayGrab();

    }
}
