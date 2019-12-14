package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

//@TeleOp(name = "Skystone Detector")
public class skystoneDetector extends OpMode {
    ColorSensor senseColor;
    double red;
    boolean gotSkystone = false;

    public void init() {

        senseColor = hardwareMap.colorSensor.get("senseColor");

        senseColor.enableLed(false);
        red = senseColor.red();
    }

    public void loop() {

        telemetry.addData("Red", red);
        telemetry.update();

        if(red<50)
        {
            gotSkystone = true;
        }

    }


}