package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class lift {

    DcMotor liftMotor;

    public void lift(){

    }

    public void initLift(HardwareMap hwMap) {

        liftMotor = hwMap.dcMotor.get("liftMotor");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void runLift(double right_trigger, double left_trigger) {

        double liftPower = (right_trigger - left_trigger) / 2;
        liftMotor.setPower(liftPower);

    }
}