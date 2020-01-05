package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "outsidePositionSitUnderBridge")
public class outsidePositionSitUnderBridge extends Auto {

    public void run(){

        sleep(5000);
        encoderDrive(0.3, 30, 30, 5);

    }
}