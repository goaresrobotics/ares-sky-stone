package com.aresrobotics.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "Tray Position")
public class trayPosition extends Auto {

    public void run(){

        encoderDrive(1, 1, 1, 0.5);
        //Gyro Turn Right
        encoderDrive(1, 1, 1, 0.5);
        //Gyro Turn Left
        //Sampling
        encoderDrive(-1, 1, 1, 0.5);
        //Gyro Turn Left
        encoderDrive(1, 1, 1, 0.5);
        //drop skystone
        //Gyro Turn right
        encoderDrive(1, 1, 1, 0.5);
        //Gyro Turn left
        encoderDrive(1, 1, 1, 0.5);
        //Gyro Turn left
        encoderDrive(1, 1, 1, 0.5);

    }

}