package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class insidePositionGrabTraySitCloseToCenterRed extends Auto {

    public static int firstMove = 8;
    public static int secondMove = 65;
    public static int thirdMove = -22;
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
        turn(secondTurn,3, false);
        encoderDrive(0.2, 0.2, thirdMove, thirdMove, 3);
        trayGrab();
        sleep(1000);
        encoderDrive(0.3, 0.11, 60,60, 20);
        encoderDrive(0.1,0.1,-0.3,-0.3,2);
        trayRelease();
        turn(0, 3, false);
        encoderDrive(0.3, 0.3, 17, 17, 3);
        turn(90, 3, false);
        encoderDrive(0.3, 0.3, 26, 26, 3);
        trayGrab();


    }
}
