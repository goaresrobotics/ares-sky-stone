package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class trayGrabber extends LinearOpMode {

    @Override
    public void runOpMode() {

    }

    public void grabber(Servo trayGrabber){
        
        trayGrabber = hardwareMap.servo.get("trayGrabber");

        double up = 1;
        double down = 0;

        if(gamepad2.a) {
            trayGrabber.setPosition(down);
         } else {
            if(gamepad2.b) {
                trayGrabber.setPosition(up);
            }
        }

    }
}