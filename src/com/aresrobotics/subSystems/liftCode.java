package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class liftCode extends LinearOpMode {

    @Override
    public void runOpMode(){

    }

    public void lift(DcMotor liftMotor) {


        double liftPower = (gamepad2.right_trigger-gamepad2.left_trigger)/2;

        liftMotor.setPower(liftPower);

    }
}