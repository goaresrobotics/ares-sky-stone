package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class arm {

        Gamepad gamepad;

    public void arm(Servo dropper, Servo spinner) {

        double drop = 1;
        double hold = 0;
        double spinnerPosition = 0;

        if (gamepad.x) {
            dropper.setPosition(drop);
        } else {
            if (gamepad.y) {
                dropper.setPosition((hold));
            }
        }

        if (gamepad.left_bumper) {
            spinnerPosition += 0.1;
        } else {
            if (gamepad.right_bumper) {
                spinnerPosition -= 0.1;
            }
        }


        spinner.setPosition(spinnerPosition);

    }
}
