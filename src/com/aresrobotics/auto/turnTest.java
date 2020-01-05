package com.aresrobotics.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.aresrobotics.auto.Auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "turnTest")
public class turnTest extends Auto {

    public static int angle = 0;
    /* public static int firstMove = 90;
    public static int secondMove = -90;
    public static int thirdMove = 0;
    public static int fourthMove = -179;
    public static int fifthMove = 179; */

    public void run(){

        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

      /*turn(firstMove, 4);
        sleep(1000);
        turn(secondMove, 4);
        sleep(1000);
        turn(thirdMove, 4);
        sleep(1000);
        turn(fourthMove, 4);
        sleep(1000);
        turn(fifthMove, 4); */

        while(isStarted()) {

            turn(angle, 10);

            packet.put("number", angle);
            dashboard.sendTelemetryPacket(packet);
            dashboard.updateConfig();

        }

    }
}