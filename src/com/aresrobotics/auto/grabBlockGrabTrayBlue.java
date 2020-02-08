package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "grabBlockGrabTrayBlue")
public class grabBlockGrabTrayBlue extends Auto{

    @Override
    public void run() {

        timer();
        encoderDrive(0.4, 0.4, 46.5, 46.5, 3);
        encoderDrive(0.4, 0.4, -4.6, -4.6, 2);
        turn(-90, 2, false);
        intake(true, true);
        encoderDrive(0.4, 0.4, 8, 8, 2);
        encoderDrive(0.4, 0.4, -8, -8, 2);
        intake(false, false);
        strafe(true, 950);
        turn(88, 2, false);
        encoderDrive(0.8, 0.8, 72, 72, 5);
        turn(175, 2, false);
        encoderDrive(0.6, 0.6, -10, -10, 2);
        trayGrab();
        sleep(200);
        encoderDrive(0.15, 0.525, 57.5,57.5, 8);
        turn(-90, 1, false);
        encoderDrive(0.4, 0.4, -15, -15, 3);
        turn(-90,1, false);
        trayRelease();
        encoderDrive(0.8, 0.8,35, 35, 6);

    }
}
