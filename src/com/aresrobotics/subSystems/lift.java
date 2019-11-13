package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class lift {

    Gamepad localGamepad;

    public void lift(Gamepad gp){

        gp = localGamepad;

    }

    public void runLift(DcMotor liftMotor) {

        double liftPower = (localGamepad.right_trigger - localGamepad.left_trigger) / 2;

        liftMotor.setPower(liftPower);

    }
}