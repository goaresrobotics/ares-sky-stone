package com.aresrobotics.auto;

import com.acmerobotics.dashboard.config.Config;
import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public abstract class Auto extends LinearOpMode {
    public AresSampleRobot aresBot = new AresSampleRobot(telemetry, this);
    private ElapsedTime runtime = new ElapsedTime();

    final double COUNTS_PER_MOTOR_REV = 560;
    final double DRIVE_GEAR_REDUCTION = -1;
    final double WHEEL_DIAMETER_INCHES = 4;
    final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    final double DRIVE_SPEED = 0.6;

    double turnspeed;

    double strafeSpeed = 0.4;

    double upLeft = 0.88;
    double upRight = 0.12;
    double downLeft = 0.32;
    double downRight = 0.69;

    public void runOpMode() {

        aresBot.init(hardwareMap);

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        aresBot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aresBot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aresBot.motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aresBot.motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        aresBot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        aresBot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        aresBot.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        aresBot.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path0", "Starting at %7d :%7d",
                aresBot.motorLeft.getCurrentPosition(),
                aresBot.motorRight.getCurrentPosition());
        telemetry.update();

        waitForStart();

        run();

    }

    abstract void run();

    public void encoderDrive(double speedLeft, double speedRight,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        if (opModeIsActive() && !isStopRequested()) {

            newLeftTarget = aresBot.motorLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = aresBot.motorRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = aresBot.motorLeftBack.getCurrentPosition() + (int) (leftInches * -COUNTS_PER_INCH);
            newRightBackTarget = aresBot.motorRightBack.getCurrentPosition() + (int) (rightInches * -COUNTS_PER_INCH);
            aresBot.motorLeft.setTargetPosition(newLeftTarget);
            aresBot.motorRight.setTargetPosition(newRightTarget);
            aresBot.motorLeftBack.setTargetPosition(newLeftBackTarget);
            aresBot.motorRightBack.setTargetPosition(newRightBackTarget);

            aresBot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            aresBot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            aresBot.motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            aresBot.motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            aresBot.motorLeft.setPower(Math.abs(speedLeft));
            aresBot.motorRight.setPower(Math.abs(speedRight));
            aresBot.motorRightBack.setPower(Math.abs(speedRight));
            aresBot.motorLeftBack.setPower(Math.abs(speedLeft));

            while (!isStopRequested() &&
                    (runtime.seconds() < timeoutS) &&
                    (aresBot.motorLeft.isBusy() && aresBot.motorRight.isBusy())) {

                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        aresBot.motorLeft.getCurrentPosition(),
                        aresBot.motorRight.getCurrentPosition());
                telemetry.update();
            }

            aresBot.motorLeft.setPower(0);
            aresBot.motorRight.setPower(0);
            aresBot.motorRightBack.setPower(0);
            aresBot.motorLeftBack.setPower(0);

            aresBot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            aresBot.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            aresBot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            aresBot.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void strafe(boolean isStrafeRight, int milliseconds){

        runtime.reset();

        while(runtime.milliseconds() < milliseconds && isStarted()){

            if(isStrafeRight){

            aresBot.motorLeft.setPower(-strafeSpeed);
            aresBot.motorRightBack.setPower(strafeSpeed);
            aresBot.motorRight.setPower(strafeSpeed);
            aresBot.motorLeftBack.setPower(-strafeSpeed);

        } else {

            aresBot.motorLeft.setPower(strafeSpeed);
            aresBot.motorRightBack.setPower(-strafeSpeed);
            aresBot.motorRight.setPower(-strafeSpeed);
            aresBot.motorLeftBack.setPower(strafeSpeed);

        }

        }

        aresBot.motorLeft.setPower(0);
        aresBot.motorRightBack.setPower(0);
        aresBot.motorRight.setPower(0);
        aresBot.motorLeftBack.setPower(0);

    }

    public void turn(double angle, double timeout) {

        runtime.reset();
        runtime.startTime();
        Orientation orientation = aresBot.imu.getAngularOrientation();

        double PCoefficient = 0.0105;

        while (runtime.seconds() < timeout && isStarted() && isStarted()) {

            turnspeed = (angle - orientation.firstAngle) * PCoefficient;

            telemetry.addData("Current Angle", orientation.firstAngle);
            telemetry.addData("Target Angle", angle);
            telemetry.addData("Turning Speed", turnspeed);
            telemetry.update();

            aresBot.motorLeft.setPower(turnspeed);
            aresBot.motorLeftBack.setPower(-turnspeed);
            aresBot.motorRight.setPower(-turnspeed);
            aresBot.motorRightBack.setPower(turnspeed);

            orientation = aresBot.imu.getAngularOrientation();

            if (orientation.firstAngle >= angle - 0.5 && orientation.firstAngle <= angle + 0.5) {

                break;

            }
        }

        aresBot.motorLeft.setPower(0);
        aresBot.motorLeftBack.setPower(0);
        aresBot.motorRight.setPower(0);
        aresBot.motorRightBack.setPower(0);

    }

        public void intake(boolean on){

            if(on && isStarted()) {

            aresBot.intakeLeft.setPower(-1);
            aresBot.intakeRight.setPower(1);

            }

            if (!on && isStarted()){

            aresBot.intakeLeft.setPower(0);
            aresBot.intakeRight.setPower(0);

            }

        }

        public void grabBlock(boolean onBlue){

        if(onBlue){

            turn(-45, 3);
            intake(true);
            encoderDrive(0.4, 0.4, 5, 5, 2);
            encoderDrive(0.4, 0.4, -5, -5, 2);
            intake(false);
            turn(-90, 3);

        }

        }

    /*
        public void skystoneFinder()
        {
            int skyStonePosition = 0;
            boolean foundSkystone = false;
            encoderDrive(0.3, 8, 8, 2);
            while (1 == 1)
            {


                if (aresBot.senseColor.red() < 50)
                {

                    foundSkystone = true;

                } else
                    {

                    skyStonePosition = +1;

                }

                if (foundSkystone==true)
                {

                    //grab skystone

                }
                }
            }


        }

        /*

            }

            public void intake(boolean in) {

                int speedIn;
                int speedOut;

                speedIn = 1;
                speedOut = -1;

                runtime.reset();


                while (runtime.seconds() < 2) {

                    if (in) {

                        aresBot.intake1.setPower(speedIn);
                        aresBot.intake2.setPower(-speedIn);

                    } else {

                        aresBot.intake1.setPower(speedOut);
                        aresBot.intake2.setPower(-speedOut);

                    }
                }
            }

        */
    //If grabIsTrue is true it will grab, if grabIsTrue is false it will release
    public void trayGrab() {

        aresBot.trayGrabberLeft.setPosition(downLeft);
        aresBot.trayGrabberRight.setPosition(downRight);
        sleep(1000);

    }


    public void trayRelease() {

        aresBot.trayGrabberLeft.setPosition(upLeft);
        aresBot.trayGrabberRight.setPosition(upRight);
        sleep(1000);

    }




/*
    public void deploy(DcMotor lift, DcMotor lift2, Servo ratchet)
    {

        Double liftSpeed;
        Double ratchetPos;

        ratchetPos = 0.98;
        liftSpeed = -0.3;
        ratchet.setPosition(ratchetPos);
        lift.setPower(liftSpeed);
        lift2.setPower(liftSpeed);
        sleep(3000);
        liftSpeed = 0.0;

        sleep(500);

        aresBot.motorLeftBack.setPower(0.5);
        aresBot.motorLeft.setPower(-0.5);
        aresBot.motorRight.setPower(0.5);
        aresBot.motorRightBack.setPower(-0.5);

        sleep(500);

        aresBot.motorLeftBack.setPower(0.0);
        aresBot.motorLeft.setPower(0.0);
        aresBot.motorRight.setPower(0.0);
        aresBot.motorRightBack.setPower(0.0);

    }*/
}