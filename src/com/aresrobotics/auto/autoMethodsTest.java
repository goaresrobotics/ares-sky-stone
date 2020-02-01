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

        intake(true, true);
        sleep(1000);
        intake(false, true);

        sleep(1000);

        //grabBlock(true);

    }
}