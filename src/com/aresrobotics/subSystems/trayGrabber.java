package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class trayGrabber{

    Gamepad localGamepad;

   public void trayGrabber(Gamepad gp){

       gp = localGamepad;

   }

    public void runGrabber(Servo trayGrabber){

        double up = 1;
        double down = 0;

        if(localGamepad.a) {
            trayGrabber.setPosition(down);
         } else {
            if(localGamepad.b) {
                trayGrabber.setPosition(up);
            }
        }

    }
}