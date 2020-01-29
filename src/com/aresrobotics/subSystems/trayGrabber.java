package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class trayGrabber{

    Servo trayGrabberLeft;
    Servo trayGrabberRight;

    boolean isStarted = false;

    double upLeft = 0.88;
    double upRight = 0.12;
    double downLeft = 0.32;
    double downRight = 0.69;

   public void trayGrabber(){

   }

   public void  initTrayGrab(HardwareMap hwmap){

       trayGrabberLeft = hwmap.servo.get("trayGrabberLeft");
       trayGrabberRight = hwmap.servo.get("trayGrabberRight");

   }

    public void runGrabber(boolean a, boolean b){

       if(!isStarted){

           trayGrabberRight.setPosition(downRight);
           trayGrabberLeft.setPosition(downLeft);

           isStarted = true;

       }


        if(a) {
            trayGrabberLeft.setPosition(downLeft);
            trayGrabberRight.setPosition((downRight));
         } else {
            if(b) {
                trayGrabberLeft.setPosition(upLeft);
                trayGrabberRight.setPosition(upRight);
            }
        }
    }
}