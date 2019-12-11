package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "hsvColor")
public class hsvTest extends OpMode
{

    ColorSensor senseColor;
    double red;

    public void init() {

        senseColor = hardwareMap.colorSensor.get("senseColor");

    }



    public void loop() {
        
        senseColor.enableLed(false);

        red = senseColor.red();

        telemetry.addData("Red", red);
        telemetry.update();


        while(red<70) {
            red = senseColor.red();
            telemetry.addLine("Got Skystone");
            telemetry.addData("Red", red);
            telemetry.update();
        }

    }


}