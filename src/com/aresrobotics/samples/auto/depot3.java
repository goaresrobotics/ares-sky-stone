/*
   Modified from the FIRST examples included in the examples folder in ftc_app
 */

package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Illustrates the use of the motor encoders to run to position
 */

@Autonomous(name="depot3", group="Samples")
public class depot3 extends Auto {

    @Override
    public void run() {

        encoderDrive(DRIVE_SPEED, -15, -15, 10);
        turn(-90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -33, -33, 10);
        turn(-60,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -5, -5, 10);
        turn(-90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -50, -50, 10);
        turn(180,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1, -84, -84, 10);
    }
}
