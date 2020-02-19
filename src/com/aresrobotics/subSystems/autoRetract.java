package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class autoRetract
{

    lift lifta = new lift();


    public void retractLift(boolean dpad_down)
    {

        if(dpad_down)
        {
            lifta.liftMotor.setTargetPosition(2000);
            lifta.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifta.liftMotor.setPower(0.4);

            lifta.spinner.setPosition(0.04);
            lifta.dropper.setPosition(0.58);

            lifta.armRotate.setTargetPosition(0);
            lifta.armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifta.armRotate.setPower(0.1);
            lifta.armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            lifta.liftMotor.setTargetPosition(0);
            lifta.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lifta.liftMotor.setPower(0.4);
            lifta.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

}
