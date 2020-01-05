package com.aresrobotics.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous (name = "insidePositionGrabTrayBlue")
public class insidePositionGrabTrayBlue extends Auto {

    public static int firstMove = 8;
    public static int secondMove = 50;
    public static int thirdMove = -23;
    public static int fourthMove = 23;
    public static int fifthMove = -8;
    public static int sixthMove = 33;

    public void run(){

        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        packet.put("number", firstMove);
        packet.put("number", secondMove);
        packet.put("number", thirdMove);
        packet.put("number", fourthMove);
        packet.put("number", fifthMove);
        packet.put("number", sixthMove);

        dashboard.sendTelemetryPacket(packet);

        encoderDrive(0.4, firstMove, firstMove, 2);
        turn(88, 3);
        encoderDrive(0.4, secondMove, secondMove, 7);
        turn(179.5,3);
        encoderDrive(0.2, thirdMove, thirdMove, 3);
        trayGrab();
        sleep(1000);
        encoderDrive(0.3, fourthMove, fourthMove,  7);
        turn(-110,3);
        encoderDrive(0.1,fifthMove,fifthMove,1.5);
        trayRelease();
        sleep(1000);
        encoderDrive(0.4, sixthMove, sixthMove, 7);
        turn(-90,3);

    }
}