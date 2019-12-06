package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "Tray Position")
public class trayPosition extends Auto {

    public void run(){

        encoderDrive(1, 1, 1, 0.5);
        turn(90);
        encoderDrive(1, -1, -1, 0.5);
        //tray grab
        encoderDrive(1, 1, 1, 0.5);
        turn(90);
        encoderDrive(1, 1, 1, 0.5);

    }

}