package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionSitUnderBridge")
public class insidePositionSitUnderBridge extends Auto {

    public void run(){

        sleep(5000);
        encoderDrive(0.3,0.3,14,14,5);
        trayGrab();

    }
}