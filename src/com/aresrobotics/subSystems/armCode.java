package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class armCode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    }

    public void arm(Servo dropper, Servo spinner){

        double drop = 1;
        double hold = 0;
        double spinnerPosition = 0;

        if(gamepad2.x) {
            dropper.setPosition(drop);
        } else {
            if(gamepad2.y) {
                dropper.setPosition((hold));
            }
        }

        if (gamepad2.left_bumper) {
            spinnerPosition += 0.1;
        } else {
            if (gamepad2.right_bumper) {
                spinnerPosition -= 0.1;
            }
        }


        spinner.setPosition(spinnerPosition);

    }
}
