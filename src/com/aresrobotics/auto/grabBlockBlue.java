package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "grabBlockBlue")
public class grabBlockBlue extends Auto{

    public void run(){

        encoderDrive(0.3, 0.3, 12, 12, 3);
        turn(-90, 3);
        encoderDrive(0.3, 0.3, 10, 10, 3);
        turn(0, 3);
        encoderDrive(0.3, 0.3, 28, 28, 5);
        encoderDrive(0.3, 0.3, -8, -8, 5);
        turn(-90, 3);
        intake(true, true);
        encoderDrive(0.3, 0.3, 8, 8, 3);
        sleep(500);
        encoderDrive(0.3, 0.3, -8, -8, 3);
        intake(false, false);

    }

}
