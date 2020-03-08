package com.aresrobotics.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "insideTraysidePositionGrabTrayBlue")
public class insideTraysidePositionGrabTrayBlue extends Auto {

    public static int firstMove = 8;
    public static int secondMove = 42;
    public static int thirdMove = -15;
    public static int fourthMove = 23;
    public static int fifthMove = 50;

    public static int firstTurn = 85;
    public static int secondTurn = 180;
    public static int thirdTurn = 101;
    public static int fourthTurn = 90;

    public void run(){


        encoderDrive(0.4, 0.4, firstMove, firstMove, 2);
        turn(firstTurn, 1.5, false);
        encoderDrive(0.4, 0.4, secondMove, secondMove, 5);
        turn(secondTurn, 1.5, false);
        encoderDrive(0.2, 0.2, thirdMove, thirdMove, 3);
        trayGrab();
        sleep(1000);
        encoderDrive(0.08, 0.5, 30,70, 6);
        turn(-90, 1.5, false);
        encoderDrive(0.4, 0.4, -14, -14, 3);
        turn(-90,1.5, false);
        trayRelease();
        strafe(true, 800);
        encoderDrive(0.4,0.4, 36,36,5);
        trayGrab();

    }
    }
