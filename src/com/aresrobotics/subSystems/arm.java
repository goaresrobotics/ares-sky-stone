package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class arm {

    Servo spinner;
    Servo dropper;
    DcMotor armRotate;
    double drop = 0.5;
    double hold = 0.18;
    double spinnerPosition = 0;

    public void arm() {

    }

    public void initArm(HardwareMap hwMap) {

        spinner = hwMap.servo.get("spinner");
        dropper = hwMap.servo.get("dropper");
        armRotate = hwMap.dcMotor.get("armRotate");

        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void runArm(boolean x, boolean y, boolean left_bumper, boolean right_bumper, double left_stick_y) {

            if (x) {
                spinner.setPosition(drop);
            }

            if (y) {
                spinner.setPosition((hold));
            }


                    if (left_bumper) {
                spinnerPosition = 1;
            } else {

                if (right_bumper) {
                    spinnerPosition = 0;
                }
            }



        dropper.setPosition(spinnerPosition);

        if(left_stick_y<0){
            armRotate.setPower(-left_stick_y/1.5);
        }
        if(left_stick_y>0){
            armRotate.setPower(-left_stick_y/4);
        }
        if(left_stick_y==0){
            armRotate.setPower(0);
        }


    }
}
