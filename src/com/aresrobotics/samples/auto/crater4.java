package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="crater4", group="Samples")
public class crater4 extends Auto {

    @Override
    public void run() {



        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  -23,  -23, 10);
        turn(90, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -25, -25, 10);
        turn(60, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  -72,  -72, 10);
        turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1,  -87,  -87, 10);
    }
}
