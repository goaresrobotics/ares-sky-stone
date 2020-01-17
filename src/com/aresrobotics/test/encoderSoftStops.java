package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class encoderSoftStops extends OpMode {

    public DcMotor liftMotor;
    public double currentPosition;
    public double min = 0;
    public double max = 900;
    public double liftPower;

    public void init() {

        liftMotor = hardwareMap.dcMotor.get("liftMotor");

}

    public void loop(){

        currentPosition = liftMotor.getCurrentPosition();

        if((gamepad2.right_trigger == 0 && gamepad2.left_trigger == 0) || (currentPosition <= min && gamepad2.left_trigger > 0.01) || (currentPosition <= max && gamepad2.right_trigger > 0.1)){

            liftPower = 0;

        } else {

                if (gamepad2.right_trigger > 0) {

                    liftPower = gamepad2.right_trigger;

                }

                if (gamepad2.left_trigger > 0) {

                    liftPower = -gamepad2.left_trigger;

                }

            }


        liftMotor.setPower(liftPower);

    }

}
