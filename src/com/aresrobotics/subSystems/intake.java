package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {

    public DcMotor intakeLeft;
    public DcMotor intakeRight;


    public void intake(){

    }

    public void initIntake(HardwareMap hwMap){

        intakeLeft = hwMap.dcMotor.get("intakeLeft");
        intakeRight = hwMap.dcMotor.get("intakeRight");

    }

       public void runIntake(double right_stick_y) {

        double intakeSpeed = right_stick_y;

        intakeLeft.setPower(intakeSpeed);
        intakeRight.setPower(intakeSpeed);

    }
}