package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class oneButtonPress extends OpMode {

    DcMotor liftMotor;
    DcMotor arm;
    Servo wrist;
    public boolean dpadLeftHasBeenPressed = false;
    public double goingDown = 1;
    public double bottom = 0;
    public boolean isArmDown;
    public double armDown = 0;
    public boolean isWristIn;
    public double wristInValue = 0;


    @Override
    public void init() {

        liftMotor = hardwareMap.dcMotor.get("liftMotor");

    }

    @Override
    public void loop() {

        if (gamepad2.dpad_down == true) {

            dpadLeftHasBeenPressed = true;

        }

            if (gamepad2.dpad_down == false && dpadLeftHasBeenPressed == true) {

                dpadLeftHasBeenPressed = false;

                goingDown *= -1;

            }

            if(isWristIn == false && goingDown < 0 && wrist.getPosition() != wristInValue) {

                wrist.setPosition(wristInValue);

            } else {

                isWristIn = true;

            }

            if(goingDown < 0 && arm.getCurrentPosition() > armDown && isWristIn == true) {

                arm.setPower(-0.2);
                isArmDown = false;

            } else {
                // kenton wrote this line
                arm.setPower(0);

                if(isWristIn) {

                   isArmDown = true;

                } else {

                    isArmDown = false;

                }
            }

            if (goingDown < 0 && liftMotor.getCurrentPosition() > bottom && isArmDown == true) {

                liftMotor.setPower(-0.3);

            } else {

                liftMotor.setPower(0);

            }
    }
}