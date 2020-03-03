package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class lift {

    Servo spinner;
    Servo dropper;
    //DcMotor armRotate;

    int position = 0;
    double PC = 0.025;

    boolean runRetract = false;

    double dropperPosition = 0.58;
    double spinnerPosition = 0.09;

    DcMotorEx liftMotor;
    double liftPower;

    DcMotorEx armRotate;

    boolean isBusy;

    boolean isArmDown = false;
    boolean isLiftDown= false;
    boolean isLiftRetracting = false;
    int liftStage = 0;

    boolean hasStarted = false;


    public void lift(){

    }

    public void initLift(HardwareMap hwMap) {

        liftMotor = hwMap.get(DcMotorEx.class, "liftMotor");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setTargetPositionTolerance(20);

        spinner = hwMap.servo.get("spinner");
        dropper = hwMap.servo.get("dropper");
        //armRotate = hwMap.dcMotor.get("armRotate");

        //armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armRotate = hwMap.get(DcMotorEx.class, "armRotate");
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotate.setTargetPositionTolerance(35);

    }



    public void runLift(boolean dpad_down, double left_trigger, double right_trigger, boolean x, boolean y, boolean left_bumper, boolean right_bumper, double left_stick_y, Telemetry telemetry) {

      if(hasStarted = false) {

            armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            hasStarted = true;

        }



        if(dpad_down){

            runRetract = true;

        }

        if(left_trigger != 0 || right_trigger != 0 || x || y || left_bumper || right_bumper || left_stick_y != 0){

            runRetract = false;


        }

        if(runRetract)
        {

            spinnerPosition = 0.04;
            dropperPosition = 0.2;

            if(armRotate.getCurrentPosition() != 0 && !isArmDown) {

                armRotate.setTargetPosition(0);
                armRotate.setPower(1);
                armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                position = 0;

            }

            if(armRotate.getCurrentPosition() >= -10){

                isArmDown = true;
                position = 0;

            }

            if(liftMotor.getCurrentPosition() != 0 && isArmDown && !isLiftDown) {

                isLiftRetracting = true;
                liftMotor.setTargetPosition(0);
                liftMotor.setPower(1);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftStage = 1;

            }

            if (liftMotor.getCurrentPosition() >= -10) {

                isLiftDown = true;
                liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                liftStage = 2;

            }

            if(isArmDown && isLiftDown) {

                dropperPosition = 0.58;
                runRetract = false;

            }

        } else {

            liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            isLiftDown = false;
            isArmDown = false;

            if (right_trigger > 0) {

                liftPower = right_trigger;
                isBusy = true;

            }

            if (left_trigger > 0) {

                liftPower = -left_trigger;
                isBusy = true;

            }

            if (left_trigger == 0 && right_trigger == 0) {

                liftPower = 0;
                isBusy = false;

            }


            if (x) {
                dropperPosition = 0.31;
            }

            if (y) {
                dropperPosition = 0.58;
            }



            if (left_bumper) {
                spinnerPosition = 1;
            } else {

                if (right_bumper) {
                    spinnerPosition = 0.04;
                }
            }


            if (left_stick_y > 0) {

                position += 1;

            }

            if (left_stick_y < 0) {

                position -= 1;

            }

            liftMotor.setPower(liftPower);
            liftStage = 3;

            //armRotate.setPower((position-armRotate.getCurrentPosition())*PC);
            armRotate.setPower(0.8);
            armRotate.setTargetPosition(position*3);

            armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }

        //dropper.setPosition(dropperPosition);
        //spinner.setPosition(spinnerPosition);

        telemetry.addData("Is Arm Down", isArmDown);
        telemetry.addData("Arm Position", armRotate.getCurrentPosition());
        telemetry.addData("Retracting if true", runRetract);
        telemetry.addData("Lift Position", liftMotor.getCurrentPosition());
        telemetry.addData("Is Lift Down", isLiftDown);
        telemetry.addData("Is Lift Retracting", isLiftRetracting);
        telemetry.addData("Lift Stage", liftStage);
        telemetry.addData("Lift Set Position", liftMotor.getTargetPosition());
        telemetry.addData("Lift Runmode", liftMotor.getMode());
        telemetry.update();

    }
}