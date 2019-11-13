package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class arm {

        Gamepad localGamepad;

        public void arm(Gamepad gp){

            gp = localGamepad;

        }

    public void runArm(Servo dropper, Servo spinner) {

        double drop = 1;
        double hold = 0;
        double spinnerPosition = 0;

        if (localGamepad.x) {
            dropper.setPosition(drop);
        } else {
            if (localGamepad.y) {
                dropper.setPosition((hold));
            }
        }

        if (localGamepad.left_bumper) {
            spinnerPosition += 0.1;
        } else {
            if (localGamepad.right_bumper) {
                spinnerPosition -= 0.1;
            }
        }


        spinner.setPosition(spinnerPosition);

    }
}
