package com.aresrobotics.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.aresrobotics.auto.Auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "autoMethodsTest")
public class autoMethodsTest extends Auto {

    public void run(){

        while(isStarted()) {

            turn(0, 3, false);
            encoderDrive(0.2, 0.2, 15, 15, 5);
            turn(90, 3, false);
            encoderDrive(0.2, 0.2, 15, 15, 5);
            turn(180, 3, false);
            encoderDrive(0.2, 0.2, 15, 15, 5);
            turn(270, 3, false);
            encoderDrive(0.2, 0.2, 15, 15, 5);

            telemetry.addData("Motor Back Right Direction", aresBot.motorRightBack.getDirection());
            telemetry.addData("Motor Right Direction", aresBot.motorRight.getDirection());
            telemetry.addData("Motor Back Left Direction", aresBot.motorLeftBack.getDirection());
            telemetry.addData("Motor Left Direction", aresBot.motorLeft.getDirection());
            telemetry.update();

        }
        //grabBlock(true);

    }
}