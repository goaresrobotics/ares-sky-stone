package com.aresrobotics.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class insidePositionGrabTrayBlue extends Auto {

    public static int firstMove = 8;
    public static int secondMove = 65;
    public static int thirdMove = -22;
    public static int fourthMove = 23;
    public static int fifthMove = 50;

    public static int firstTurn = 85;
    public static int secondTurn = 180;
    public static int thirdTurn = -101;
    public static int fourthTurn = -90;

    public void run(){

        encoderDrive(0.4, 0.4, firstMove, firstMove, 2);
        turn(firstTurn, 3, false);
        encoderDrive(0.4, 0.4, secondMove, secondMove, 7);
        turn(secondTurn,3, false);
        encoderDrive(0.2, 0.2, thirdMove, thirdMove, 3);
        trayGrab();
        sleep(1000);
        encoderDrive(0.11, 0.3, 60,60, 20);
        encoderDrive(0.1,0.1,-0.3,-0.3,2);
        trayRelease();
        encoderDrive(0.4,0.4, 24,24,5);
        turn(-90,3, false);
        trayGrab();

        //encoderDrive(0.3, 0.3, fourthMove, fourthMove,  7);
        //turn(thirdTurn,3);
        //trayRelease();
        //sleep(1000);
        //encoderDrive(0.4, 0.4, fifthMove, fifthMove, 7);
        //turn(fourthTurn,3);

    }
}