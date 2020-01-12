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

    boolean prevValue = false;
    int liftHeightValue = 0;


    public void runLift(boolean right_bumper, double right_Trigger) {



        if(right_bumper == true && prevValue == false){
            liftHeightValue =+ 1;
            prevValue=true;
        }

        if(right_bumper == false){
            prevValue=false;
        }

        liftMotor.setPower(right_Trigger);

        if(liftHeightValue==0)
        {

        }
        if(liftHeightValue==1)
        {

        }
        if(liftHeightValue==2)
        {

        }
        if(liftHeightValue==3)
        {

        }
        if(liftHeightValue==4)
        {

        }
        if(liftHeightValue==5)
        {

        }
        if(liftHeightValue==6)
        {

        }
        if(liftHeightValue==7)
        {

        }
        if(liftHeightValue==8)
        {

        }
            /*
            if (right_bumper > 0) {


                liftPower = right_bumper;

            }

            if (left_trigger > 0) {

                liftPower = -left_trigger;

            }
            if(right_trigger==0 && left_trigger==0)
            {

                liftPower = 0;

            }


            liftMotor.setPower(liftPower);
            */

    }
}