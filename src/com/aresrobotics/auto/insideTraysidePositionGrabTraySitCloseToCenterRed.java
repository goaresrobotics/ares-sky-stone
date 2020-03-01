package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "insideTraysidePositionGrabTraySitCloseToCenterRed")
public class insideTraysidePositionGrabTraySitCloseToCenterRed extends Auto {

    public static int firstMove = 8;
    public static int secondMove = 36;
    public static int thirdMove = -16;
    public static int fourthMove = 23;
    public static int fifthMove = 50;

    public static int firstTurn = -85;
    public static int secondTurn = -180;
    public static int thirdTurn = 101;
    public static int fourthTurn = 90;

    public void run(){

        encoderDrive(0.4, 0.4, firstMove, firstMove, 2);
        turn(firstTurn, 3, false);
        encoderDrive(0.4, 0.4, secondMove, secondMove, 7);
        turn(secondTurn, 3, false);
        encoderDrive(0.2, 0.2, thirdMove, thirdMove, 3);
        trayGrab();
        sleep(1000);
        encoderDrive(0.35, 0.1, 55,55, 20);
        turn(90, 3, false);
        trayRelease();
        encoderDrive(0.4, 0.4, -15, -15, 3);
        turn(90,1, false);
        strafe(true, 1100);
        turn(90, 1, false);
        encoderDrive(0.4,0.4, 38,38,5);
        trayGrab();

    }
}
