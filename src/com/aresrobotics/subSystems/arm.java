package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class arm {

    Servo spinner;
    Servo dropper;
    DcMotor armRotate;

    public void arm() {

    }

    public void initArm(HardwareMap hwMap) {

        spinner = hwMap.servo.get("spinner");
        dropper = hwMap.servo.get("dropper");
        armRotate = hwMap.dcMotor.get("armRotate");

    }

    public void runArm(boolean x, boolean y, boolean left_bumper, boolean right_bumper, double left_stick_y) {

        double drop = 1;
        double hold = 0;
        double spinnerPosition = 0;

        if (x && y) {

        } else {

            if (x) {
                dropper.setPosition(drop);
            }

            if (y) {
                dropper.setPosition((hold));
            }
        }

        if(left_bumper && right_bumper) {

        } else {

            if (left_bumper) {
                spinnerPosition += 0.1;
            }

            if (right_bumper) {
                spinnerPosition -= 0.1;
            }
        }

        spinner.setPosition(spinnerPosition);

        armRotate.setPower(left_stick_y/3);

    }
}
