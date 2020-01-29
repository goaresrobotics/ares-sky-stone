package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


//@TeleOp(name = "stickyTest")
public class stickyTest extends OpMode{
    private static boolean lastState;

    @Override
     public void init(){}

    @Override
    public void loop(){

    boolean dad = false;

            if (gamepad2.dpad_up == true && dad == false) {
        lastState = dad;
        dad = true;
    } else {
        if (gamepad2.dpad_up == true && dad == true)
        {
            lastState = dad;
        dad = false;
        } else {



        }
    }

    telemetry.addData("D-Pad", lastState);



    }

}
