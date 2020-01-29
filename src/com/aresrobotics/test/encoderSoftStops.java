package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class encoderSoftStops extends OpMode {

    public DcMotor liftMotor;
    private double currentPosition;
    private double min = 0;
    private double wristMax = 90;
    private double max = 900;
    private double liftPower;
    private double wristPosition;
    public Servo wrist;
    private double barHeight = 10;

    public void init() {

        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        wrist = hardwareMap.servo.get("wrist");

    }

    public void loop() {

        currentPosition = liftMotor.getCurrentPosition();

        if ((gamepad2.right_trigger == 0 && gamepad2.left_trigger == 0) || (currentPosition <= min && gamepad2.left_trigger > 0.01) || (currentPosition <= max && gamepad2.right_trigger > 0.1)) {

            liftPower = 0;

        } else {

            if (gamepad2.right_trigger > 0) {

                liftPower = gamepad2.right_trigger;

            }

            if (gamepad2.left_trigger > 0) {

                liftPower = -gamepad2.left_trigger;

            }

        }

        if (currentPosition < wristMax) {

            if (liftMotor.getCurrentPosition() < barHeight && wrist.getPosition() > 0.25 && wrist.getPosition() < 0.75) {

                if(wrist.getPosition() > 0.5) {
                    wrist.setPosition(0.751);
                } else {
                    wrist.setPosition(0.245);
                }
            }
        }


            liftMotor.setPower(liftPower);
            wrist.setPosition(wristPosition);


        }
    }
