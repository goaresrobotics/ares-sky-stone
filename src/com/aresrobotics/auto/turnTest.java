package com.aresrobotics.auto;

import com.aresrobotics.auto.Auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "turnTest")
public class turnTest extends Auto {

    public void run(){

        turn(90);

    }
}