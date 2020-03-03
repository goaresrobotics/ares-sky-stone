package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    double armPower;

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

    double liftPosition;

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


    public void runLift(boolean dpad_down, boolean dpad_up, double right_trigger, double left_trigger, boolean x, boolean y, boolean left_bumper, boolean right_bumper, double left_stick_y, Telemetry telemetry) {

        liftPosition = liftMotor.getCurrentPosition() * COUNTS_PER_INCH;

        /*
        if(liftHeightValue < 0){

            liftHeightValue = 0;

        } else {

            if (dpad_up == true && prevValue == false) {
                liftHeightValue = +1;
                prevValue = true;
                hasReachedIncrement = false;
            }

            if (dpad_up == false) {
                prevValue = false;
            }

            if (dpad_down == true && prevValue == false) {
                liftHeightValue -= 1;
                prevValue = true;
                hasReachedIncrement = false;
            }

            if (dpad_down == false) {
                prevValue = false;
            }
        } */

        if (right_trigger > 0) {

            liftPower = -right_trigger;
            hasReachedIncrement = true;

        }

        if (left_trigger > 0) {

            liftPower = left_trigger;
            hasReachedIncrement = true;

        }

        if(left_trigger == 0 && right_trigger == 0){

            liftPower = 0;

        }

        /*if(liftMotor.getCurrentPosition() <= 0 && liftPower < 0){

            liftPower = 0;

        }*/

        /*
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
*/

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

            if(liftMotor.getCurrentPosition() > 0){
                liftPower = -0.3;
            }

            if (liftMotor.getCurrentPosition() < -3450){

                liftPower = 0;

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
/*
        if(left_stick_y<0){
            armRotate.setPower(left_stick_y);
        }
        if(left_stick_y>0){
            armRotate.setPower(left_stick_y/4);
        }
        if(left_stick_y==0){

            armRotate.setPower(0);

        }
        */

        if(left_stick_y>0)
        {

            position+=1;

        }
        if(left_stick_y<0)
        {

            position-=1;

        }

        armPower = (position-armRotate.getCurrentPosition())*PC;

        if(position <= -250){

            position = -249;

        }

        if(position >= 1){

            position = 0;

        }

        armRotate.setPower(armPower);

        telemetry.addData("Position", position);
        telemetry.addData("Power", armPower);
        telemetry.update();

    }
}