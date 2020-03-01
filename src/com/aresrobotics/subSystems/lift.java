package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class lift {

    Servo spinner;
    Servo dropper;
    DcMotor armRotate;

    double position = 0;
    double PC = 0.025;

    boolean runRetract = false;

    double dropperPosition = 0.58;
    double spinnerPosition = 0.09;

    DcMotor liftMotor;
    double liftPower;

    final double COUNTS_PER_SHAFT_REV = 280;
    final double PULLEY_CIRCUMFERENCE = 4.24115; //INCHES
    final double COUNTS_PER_INCH = COUNTS_PER_SHAFT_REV/PULLEY_CIRCUMFERENCE;

    boolean isBusy;




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



    public void runLift(boolean dpad_down, double left_trigger, double right_trigger, boolean x, boolean y, boolean left_bumper, boolean right_bumper, double left_stick_y, Telemetry telemetry) {

 /*       if(hasStarted = false) {

            armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            hasStarted = true;

        }
*/

        if(dpad_down){

            runRetract = true;

        }

        if(left_trigger != 0 || right_trigger != 0 || x || y || left_bumper || right_bumper || left_stick_y != 0){

            runRetract = false;


        }

        if(runRetract)
        {
            telemetry.addData("Retracting", runRetract);

            armRotate.setDirection(DcMotorSimple.Direction.REVERSE);

            spinnerPosition = 0.04;
            dropperPosition = 0.2;

            if(armRotate.getCurrentPosition() != 0) {


                armRotate.setTargetPosition(0);
                armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else {

            if(liftMotor.getCurrentPosition() != 0) {

                liftMotor.setTargetPosition(0);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else {

                armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                dropperPosition = 0.58;

                liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                runRetract = false;
            }
        }

        } else {

            if(liftMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION){

                liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            }

            if(armRotate.getMode() == DcMotor.RunMode.RUN_TO_POSITION){

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
            armRotate.setPower((position-armRotate.getCurrentPosition())*PC);

        }

        //dropper.setPosition(dropperPosition);
        //spinner.setPosition(spinnerPosition);

        telemetry.update();

    }
}