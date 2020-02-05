package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "grabBlockBlue")
public class grabBlockBlue extends Auto{

    public void run(){

        encoderDrive(0.3, 0.3, 47, 47, 3);
        encoderDrive(0.3, 0.3, -4.6, -4.6, 5);
        turn(-90, 3);
        intake(true, true);
        encoderDrive(0.3, 0.3, 8, 8, 3);
        sleep(500);
        encoderDrive(0.3, 0.3, -8, -8, 3);
        intake(false, false);
        strafe(true, 925);
        turn(90, 2);
        encoderDrive(0.3, 0.3, 45, 45, 5);
        turn(90, 2);
        intake(true, false);
        encoderDrive(0.3, 0.3, -25, -25, 4);
        intake(false, false);
        /*
        turn(-90, 5);
        encoderDrive(0.3, 0.3, 36, 36, 5);
        intake(true, false);
        encoderDrive(0.3, 0.3, -12, -12, 4);
        intake(false, false);*/

    }

}
