package com.aresrobotics.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "Ftc Dashboard Test")
public class FtcDashboardTest extends LinearOpMode {

    public static int robotConstant = 0;

    @Override
    public void runOpMode(){

        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        waitForStart();

        while(!isStopRequested()) {

            packet.put("number", robotConstant);
            dashboard.sendTelemetryPacket(packet);
            telemetry.addData("Number", robotConstant);
            telemetry.update();

        }
    }
}
