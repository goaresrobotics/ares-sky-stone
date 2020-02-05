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
            aresBot.motorRight.setPower(0.4);
            aresBot.motorRightBack.setPower(0.4);
            aresBot.motorLeft.setPower(-0.4);
            aresBot.motorLeftBack.setPower(-0.4);

            sleep(2000);

            aresBot.motorRight.setPower(0);
            aresBot.motorRightBack.setPower(0);
            aresBot.motorLeft.setPower(0);
            aresBot.motorLeftBack.setPower(0);

            sleep(2000);
        }
        //grabBlock(true);

    }
}