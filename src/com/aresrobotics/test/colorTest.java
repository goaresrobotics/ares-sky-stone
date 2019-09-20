package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class colorTest extends OpMode {

    double red = 1;

    @Override
    public void init()
    {

    }

    @Override
    public void loop()
    {
        telemetry.addData("Red", red);
        telemetry.update();
        red += 3;
    }
 }
