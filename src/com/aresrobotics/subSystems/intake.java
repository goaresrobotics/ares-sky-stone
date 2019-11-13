package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class intake {

    Gamepad localGamepad;

    public void intake(Gamepad gp){

        gp = localGamepad;

    }

       public void runIntake(DcMotor intakeLeft, DcMotor intakeRight) {

        double intakeSpeed = localGamepad.right_stick_y;

        intakeLeft.setPower(intakeSpeed);
        intakeRight.setPower(-intakeSpeed);

    }
}