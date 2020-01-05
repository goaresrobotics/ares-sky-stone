package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "goodautoblueoutside")
public class Therealauto extends Auto {

    //Tested

    public void run(){


        encoderDrive(0.4, -35, -35, 7);
        trayGrab();
        sleep(1000);
        encoderDrive(0.4, 20, 20, 7);
        encoderDrive(0.3, 30, -30,  2);
        trayRelease();
        sleep(1000);
        encoderDrive(0.3, -25, -25, 7);


    }
}