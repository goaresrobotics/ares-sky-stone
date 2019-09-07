package com.aresrobotics.samples.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="depot1", group="Samples")
public class depot1 extends Auto {

    @Override
    public void run() {

        telemetry.addLine("Starting encoder drive");
        encoderDrive(DRIVE_SPEED,  57,  57, 10);
        aresBot.markerRelease.setPosition(0.1960784313714492);
        sleep(1000);
        aresBot.markerRelease.setPosition(0.96078431371992);
        //turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, ((77*3) + 5)/8, -(77*3)/8, 10);
        encoderDrive(1,90,90,10);
        //turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);

        //encoderDrive(1,  -100,  -100, 10);
    }
}
