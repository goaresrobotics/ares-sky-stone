package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class intake {

    Gamepad gamepad;

       public void intake(DcMotor intakeLeft, DcMotor intakeRight) {

        double intakeSpeed = gamepad.right_stick_y;

        intakeLeft.setPower(intakeSpeed);
        intakeRight.setPower(-intakeSpeed);

    }
}