package com.aresrobotics.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.aresrobotics.auto.Auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class turnTest extends Auto {

    public void run(){

        strafe(false, 1100);
        sleep(2000);
        strafe(true, 1100);

    }
}