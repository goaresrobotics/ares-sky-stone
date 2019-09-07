package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="crater1", group="Samples")
public class crater1 extends Auto {

    @Override
    public void run() {
            // Step through each leg of the path,
            // Note: Reverse movement is obtained by setting a negative distance (not speed)
            //deploy(aresBot.lift, aresBot.lift2, aresBot.ratchet);
            encoderDrive(DRIVE_SPEED, 22, 22, 10);
            encoderDrive(DRIVE_SPEED, -77/4, 77/4, 10);
            //turn(80, 0.5,aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
            encoderDrive(DRIVE_SPEED, 44, 44, 10);
            encoderDrive(DRIVE_SPEED, -77/8, 77/8, 10);

        //turn(40, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
            encoderDrive(DRIVE_SPEED, 45, 45, 10);
        aresBot.markerRelease.setPosition(0.19607843137);
        sleep(1000);
        aresBot.markerRelease.setPosition(0.96078431372);
            //turn(179.5, 0.5, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -77/2, 77/2, 10);
        encoderDrive(1, 88, 88, 10);
    }

}
