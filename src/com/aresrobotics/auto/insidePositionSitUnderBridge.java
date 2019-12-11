package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "insidePositionSitUnderBridge")
public class insidePositionSitUnderBridge extends Auto {

    public void run(){

        sleep(5000);
        encoderDrive(1,14,14,0.5);

    }
}