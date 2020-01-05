package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class lift {

    DcMotor liftMotor;
    double liftPower = 0;

    public void lift(){

    }

    public void initLift(HardwareMap hwMap) {

        liftMotor = hwMap.dcMotor.get("liftMotor");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void runLift(double right_trigger, double left_trigger) {




            if (right_trigger > 0) {

                liftPower = right_trigger;

            }

            if (left_trigger > 0) {

                liftPower = -left_trigger;

            }
            if(right_trigger==0 && left_trigger==0)
            {

                liftPower = 0;

            }


            liftMotor.setPower(liftPower);

    }
}