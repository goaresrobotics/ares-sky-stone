package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class lift {

    DcMotor liftMotor;
    double liftPower;

    boolean hasReachedIncrement;

    final double oneIncrement = 5; //Unmeasured //INCHES
    final double startingHeight = 1; //Unmeasured  //How many INCHES off the ground the bottom increment should be

    final double COUNTS_PER_SHAFT_REV = 280;
    final double PULLEY_CIRCUMFERENCE = 4.24115; //INCHES
    final double COUNTS_PER_INCH = COUNTS_PER_SHAFT_REV/PULLEY_CIRCUMFERENCE;

    double liftPosition;

    public void lift(){

    }

    public void initLift(HardwareMap hwMap) {

        liftMotor = hwMap.dcMotor.get("liftMotor");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    boolean prevValue = false;
    int liftHeightValue = 0;


    public void runLift(boolean left_bumper, boolean right_bumper, double left_trigger, double right_trigger) {

        liftPosition = liftMotor.getCurrentPosition() * COUNTS_PER_INCH;

        if(liftHeightValue < 0){

            liftHeightValue = 0;

        } else {

            if (right_bumper == true && prevValue == false) {
                liftHeightValue = +1;
                prevValue = true;
                hasReachedIncrement = false;
            }

            if (right_bumper == false) {
                prevValue = false;
            }

            if (left_bumper == true && prevValue == false) {
                liftHeightValue -= 1;
                prevValue = true;
                hasReachedIncrement = false;
            }

            if (left_bumper == false) {
                prevValue = false;
            }
        }

        if (right_trigger > 0) {

            liftPower = right_trigger;
            hasReachedIncrement = true;

        }

        if (left_trigger > 0) {

            liftPower = -left_trigger;
            hasReachedIncrement = true;

        }

        if(liftPosition > (liftHeightValue*oneIncrement) + startingHeight + 0.25 && !hasReachedIncrement) {

            liftPower = -0.3; //go down

        }

        if(liftPosition < (liftHeightValue*oneIncrement) + startingHeight - 0.25 && !hasReachedIncrement) {

            liftPower = 0.3; //go up

        }

        if(liftPosition <= (liftHeightValue*oneIncrement) + startingHeight + 0.25 && liftPosition >= (liftHeightValue*oneIncrement) + startingHeight - 0.25 && !hasReachedIncrement) {

            liftPower = 0;
            hasReachedIncrement = true;

        }


        /*

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

        */

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

            liftMotor.setPower(liftPower);

    }
}