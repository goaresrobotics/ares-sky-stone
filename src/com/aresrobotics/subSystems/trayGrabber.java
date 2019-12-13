package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class trayGrabber{

    Servo trayGrabber;

   public void trayGrabber(){

   }

   public void  initTrayGrab(HardwareMap hwmap){

       trayGrabber = hwmap.servo.get("trayGrabber");

   }

    public void runGrabber(boolean a, boolean b){

        double up = 0.8;
        double down = 0.62;

        if(a) {
            trayGrabber.setPosition(down);
         } else {
            if(b) {
                trayGrabber.setPosition(up);
            }
        }
    }
}