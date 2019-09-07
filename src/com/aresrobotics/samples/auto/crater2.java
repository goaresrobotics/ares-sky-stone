package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="crater2", group="Samples")
public class crater2 extends Auto {


    @Override
    public void run() {

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED, -13, -13, 10);
        turn(90, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -31, -31, 10);
        turn(90, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -72, -72, 10);
        turn(30, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(1, -44, -44, 10);
        turn(45, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(1, -5, -5, 10);
        turn(45, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1, -82, -82, 10);
    }

}