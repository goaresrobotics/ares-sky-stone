package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="crater3", group="Samples")
public class crater3 extends Auto {

    @Override
    public void run() {

        encoderDrive(DRIVE_SPEED,  -13,  -13, 10);
        turn(90, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -31, -31, 10);
        turn(90, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  -72,  -72, 10);
        turn(30, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  -44,  -44, 10);
        turn(180, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1,  -62,  -62, 10);
    }

}
