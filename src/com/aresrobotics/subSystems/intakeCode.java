package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class intakeCode extends LinearOpMode {

    @Override
    public void runOpMode() {


    }

    public void intake(DcMotor intakeLeft, DcMotor intakeRight) {

        double intakeSpeed = gamepad2.right_stick_y;

        intakeLeft.setPower(intakeSpeed);
        intakeRight.setPower(-intakeSpeed);

    }
}