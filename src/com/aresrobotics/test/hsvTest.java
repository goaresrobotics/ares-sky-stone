package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "hsvColor")
public class hsvTest extends OpMode {
    ColorSensor senseColor;
    double red;
    double green;
    double blue;
    double redPrevious;
    double greenPrevious;
    double bluePrevious;
    double max;
    double min;
    double change;
    double value;
    double saturation;
    double hue;

    public void init() {

        senseColor = hardwareMap.colorSensor.get("senseColor");

    }

    public void loop() {

        senseColor.enableLed(false);

        redPrevious = senseColor.red();
        greenPrevious = senseColor.green();
        bluePrevious = senseColor.blue();

        red = redPrevious / 255;
        green = greenPrevious / 255;
        blue = bluePrevious / 255;

        if (red > green) {
            if (red > blue) {
                max = red;
            } else {
                max = blue;
            }
        } else {
            if (green > blue) {
                max = green;
            } else {
                max = blue;
            }
        }

        if (red < blue) {
            if (red < green) {
                min = red;
            } else {
                min = green;
            }
        } else {
            if (blue < green) {
                min = blue;
            } else {
                min = green;
            }
        }


        change = max - min;

        value = max;

        if (change == 0) {
            hue = 0;
        } else {
            if (max == red) {
                hue = (green - blue) / change;
            } else {
                if (max == green) {
                    hue = ((blue - red) / change) + 2;
                } else {
                    if (max == blue) {
                        hue = ((red - green) / change) + 4;
                    }
                }
            }
        }

        if (value == 0) {
            saturation = 0;
        } else {
            saturation = change / value;
        }

        telemetry.addData("Red", red);
        telemetry.addData("Green", green);
        telemetry.addData("Blue", blue);
        telemetry.addData("Hue", hue);
        telemetry.addData("Saturation", saturation);
        telemetry.addData("Value", value);
        telemetry.addData("Min", min);
        telemetry.addData("Max", max);
        telemetry.addData("Change", change);
        telemetry.update();

    }


}