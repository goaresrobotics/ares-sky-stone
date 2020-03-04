package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "slapperTest")
public class slapperTest extends OpMode {

    double slapperPosition;

    Servo blockSlapper;

    @Override
    public void init() {

        blockSlapper = hardwareMap.servo.get("blockSlapper");

    }

    @Override
    public void loop() {

        if (gamepad1.dpad_up){

            slapperPosition = 0.9;

        }

        if (gamepad1.dpad_down) {

            slapperPosition = 0.15;

        }

        if (gamepad1.dpad_left){

            slapperPosition = 0.4;

        }

        blockSlapper.setPosition(slapperPosition);

    }
}
