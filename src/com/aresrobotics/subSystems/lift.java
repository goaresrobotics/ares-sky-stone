package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class lift {

    Servo spinner;
    Servo dropper;
    DcMotor armRotate;
    double whereArmStopped;
    double armCurrentPosition;
    boolean isArmMoving = false;
    double stoppedMotorPower;
    double position = 0;
    double PC = 0.025;

    double dropperPosition = 0.58;
    double spinnerPosition = 0.09;

    DcMotor liftMotor;
    double liftPower;

    boolean hasReachedIncrement;

    final double oneIncrement = 5; //Unmeasured //INCHES
    final double startingHeight = 1; //Unmeasured  //How many INCHES off the ground the bottom increment should be

    final double COUNTS_PER_SHAFT_REV = 280;
    final double PULLEY_CIRCUMFERENCE = 4.24115; //INCHES
    final double COUNTS_PER_INCH = COUNTS_PER_SHAFT_REV/PULLEY_CIRCUMFERENCE;

    boolean isBusy;
    boolean isRunningToPosition;

    boolean hasStarted = false;


    public void lift(){

    }

    public void initLift(HardwareMap hwMap) {

        liftMotor = hwMap.dcMotor.get("liftMotor");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        spinner = hwMap.servo.get("spinner");
        dropper = hwMap.servo.get("dropper");
        armRotate = hwMap.dcMotor.get("armRotate");

        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }

    boolean prevValue = false;
    int liftHeightValue = 0;


    public void runLift(boolean dpad_down, double left_trigger, double right_trigger, boolean x, boolean y, boolean left_bumper, boolean right_bumper, double left_stick_y) {

 /*       if(hasStarted = false) {

            armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            hasStarted = true;

        }
*/

        if(dpad_down && !isBusy)
        {

            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            armRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            isRunningToPosition = true;

            spinner.setPosition(0.04);
            dropper.setPosition(0.2);

            liftMotor.setTargetPosition(-1500);
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(0.4);

            armRotate.setTargetPosition(0);
            armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armRotate.setPower(0.1);
            armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            liftMotor.setTargetPosition(0);
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
            liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            dropper.setPosition(0.58);

            isRunningToPosition = false;

            hasStarted = false;

            liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }


        if (right_trigger > 0) {

            liftPower = right_trigger;
            isBusy = true;

        }

        if (left_trigger > 0) {

            liftPower = -left_trigger;
            isBusy = true;

        }

        if(left_trigger == 0 && right_trigger == 0){

            liftPower = 0;
            isBusy = false;

        }
            liftMotor.setPower(liftPower);


        if (x) {
            dropperPosition = 0.31;
        }

        if (y) {
            dropperPosition = 0.58;
        }

        dropper.setPosition(dropperPosition);


        if (left_bumper) {
            spinnerPosition = 1;
        } else {

            if (right_bumper) {
                spinnerPosition = 0.04;
            }
        }
        spinner.setPosition(spinnerPosition);


        if(left_stick_y>0)
        {

            position+=1;

        }

        if(left_stick_y<0)
        {

            position-=1;

        }
        armRotate.setPower((position-armRotate.getCurrentPosition())*PC);

    }
}