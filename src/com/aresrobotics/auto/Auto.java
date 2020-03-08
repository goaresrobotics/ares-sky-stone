package com.aresrobotics.auto;

import com.acmerobotics.dashboard.config.Config;
import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

public abstract class Auto extends LinearOpMode {
    public AresSampleRobot aresBot = new AresSampleRobot(telemetry, this);
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime totalRuntime = new ElapsedTime();


    final double COUNTS_PER_MOTOR_REV = 560;
    final double DRIVE_GEAR_REDUCTION = -1;
    final double WHEEL_DIAMETER_INCHES = 4;
    final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    final double DRIVE_SPEED = 0.6;

    double turnspeed;

    double PCoefficient;

    double strafeSpeed = 0.4;

    double upLeft = 0.88;
    double upRight = 0.12;
    double downLeft = 0.32;
    double downRight = 0.69;

    double grabBlockTurnIn = 45;
    double grabBlockMove = 12;

    double intakePower;

    double pathNumber;


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
                telemetry.addData("Motor Left Mode", aresBot.motorLeft.getMode());
                telemetry.addData("Motor Left Back Mode", aresBot.motorLeftBack.getMode());
                telemetry.addData("Motor Right Mode", aresBot.motorRight.getMode());
                telemetry.addData("Motor Right Back Mode", aresBot.motorRightBack.getMode());
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

    public void timer() {

        telemetry.addData("Time", totalRuntime.seconds());
        telemetry.update();

    }

    public void strafe(boolean isStrafeRight, int milliseconds) {

        runtime.reset();

        while (runtime.milliseconds() < milliseconds && isStarted()) {

            if (isStrafeRight) {

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

    public void turn(double angle, double timeout, boolean isStrongTurn) {

        runtime.reset();
        runtime.startTime();
        Orientation orientation = aresBot.imu.getAngularOrientation();

        if (!isStrongTurn) {
            PCoefficient = 0.01;
        } else {
            PCoefficient = 0.02;
        }

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

    public void intake(boolean on, boolean in) {

        if (in) {

            intakePower = 1;

        } if(!in) {

            intakePower = -1;

        }

        if (on && isStarted()) {

            aresBot.intakeLeft.setPower(-intakePower);
            aresBot.intakeRight.setPower(intakePower);

        }

        if (!on && isStarted()) {

            aresBot.intakeLeft.setPower(0);
            aresBot.intakeRight.setPower(0);

        }

    }

    public void grabBlock(boolean onBlue) {

        if (onBlue && isStarted()) {

            turn(-grabBlockTurnIn, 3, false);
            intake(true, true);
            encoderDrive(0.4, 0.4, grabBlockMove, grabBlockMove, 2);
            encoderDrive(0.4, 0.4, -grabBlockMove, -grabBlockMove, 2);
            intake(false, true);
            turn(-90, 3, false);

        }

        if (!onBlue && isStarted()) {

            turn(grabBlockTurnIn, 3, false);
            intake(true, true);
            encoderDrive(0.4, 0.4, grabBlockMove, grabBlockMove, 2);
            encoderDrive(0.4, 0.4, -grabBlockMove, -grabBlockMove, 2);
            intake(false, true);
            turn(90, 3, false);

        }
    }


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

    public void detectSkystone(boolean isOnBlue, boolean isOnRed) {

        aresBot.Skystone.activate();



        while (opModeIsActive()) {
            for (VuforiaTrackable trackable : aresBot.allTrackables) {
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    aresBot.lastLocation = robotLocationTransform;
                }
            }

            if (isOnBlue) {

                if (aresBot.lastLocation == null) {
                    telemetry.addData("Location:", "Unknown");
                } else {

                    telemetry.addData("z", aresBot.lastLocation.get(0, 3));  //first value

                    //block1 193
                    //block2 -37.5
                    //block3 -219

                    telemetry.update();

                    if(aresBot.lastLocation.get(0, 3) > 110 && aresBot.lastLocation.get(0, 3) < 240)
                    {

                        pathNumber = 1;
                        telemetry.addData("z1", aresBot.lastLocation.get(0, 3));  //first value
                        telemetry.update();

                    }
                    if(aresBot.lastLocation.get(0, 3) > -90 && aresBot.lastLocation.get(0, 3) < 100)
                    {

                        pathNumber = 2;
                        telemetry.addData("z2", aresBot.lastLocation.get(0, 3));  //first value
                        telemetry.update();

                    }
                    if(aresBot.lastLocation.get(0, 3) > -300 && aresBot.lastLocation.get(0, 3) < -100)
                    {

                        pathNumber = 3;
                        telemetry.addData("z3", aresBot.lastLocation.get(0, 3));  //first value
                        telemetry.update();

                    }

                }

                if(pathNumber == 1){

                    intake(true, true);
                    turn(25, 1, false);
                    encoderDrive(0.25, 0.25, 24, 24, 4);
                    sleep(500);
                    encoderDrive(0.5, 0.5, -13, -13, 4);
                    intake(false, true);
                    sleep(500);
                    turn(90, 2, false);
                    encoderDrive(0.6, 0.6, 60, 60, 12);
                    sleep(500);
                    intake(true, false);
                    encoderDrive(0.3, 0.3, -15, -15 ,3);
                    encoderDrive(0.6, 0.5, -66, -66, 12);
                    sleep(500);
                    intake(false, false);
                    turn(0, 2, false);
                    intake(true, true);
                    encoderDrive(0.25, 0.25, 16, 16, 4);
                    sleep(500);
                    encoderDrive(0.5, 0.5, -16, -16, 4);
                    turn(90, 2, false);
                    encoderDrive(0.6, 0.6, 70, 70, 8);
                    sleep(500);
                    intake(true, false);
                    encoderDrive(0.3, 0.6, -16, -16, 3);
                    intake(false, false);
                    break;

                }

                if(pathNumber == 2){

                    intake(true, true);
                    encoderDrive(0.25, 0.25, 24, 24, 4);
                    sleep(500);
                    encoderDrive(0.5, 0.5, -13, -13, 4);
                    intake(false, true);
                    sleep(500);
                    turn(90, 2, false);
                    encoderDrive(0.6, 0.6, 60, 60, 12);
                    sleep(500);
                    intake(true, false);
                    encoderDrive(0.3, 0.3, -15, -15 ,3);
                    encoderDrive(0.6, 0.5, -74, -74, 12);
                    sleep(500);
                    intake(false, false);
                    turn(0, 2, false);
                    intake(true, true);
                    encoderDrive(0.25, 0.25, 16, 16, 4);
                    sleep(500);
                    encoderDrive(0.5, 0.5, -16, -16, 4);
                    turn(90, 2, false);
                    encoderDrive(0.6, 0.6, 70, 70, 8);
                    sleep(500);
                    intake(true, false);
                    encoderDrive(0.3, 0.6, -16, -16, 3);
                    intake(false, false);
                    break;

                }

                if(pathNumber == 3){

                    intake(true, true);
                    turn(-25, 1, false);
                    encoderDrive(0.25, 0.25, 24, 24, 4);
                    sleep(500);
                    encoderDrive(0.5, 0.5, -13, -13, 4);
                    intake(false, true);
                    sleep(500);
                    turn(90, 2, false);
                    encoderDrive(0.6, 0.6, 60, 60, 12);
                    sleep(500);
                    intake(true, false);
                    encoderDrive(0.3, 0.3, -15, -15 ,3);
                    encoderDrive(0.6, 0.5, -74, -74, 12);
                    sleep(500);
                    intake(false, false);
                    turn(-25, 2, false);
                    intake(true, true);
                    encoderDrive(0.25, 0.25, 16, 16, 4);
                    sleep(500);
                    encoderDrive(0.5, 0.5, -16, -16, 4);
                    turn(90, 2, false);
                    encoderDrive(0.6, 0.6, 70, 70, 8);
                    sleep(500);
                    intake(true, false);
                    encoderDrive(0.3, 0.6, -16, -16, 3);
                    intake(false, false);
                    break;

                }

            }
            if (isOnRed) {

                if (aresBot.lastLocation == null) {
                    telemetry.addData("Location:", "Unknown");
                } else {

                    telemetry.addData("z", aresBot.lastLocation.get(0, 3));  //first value

                    //block1 193
                    //block2 -37.5
                    //block3 -219

                    if(aresBot.lastLocation.get(0, 3) > 140 && aresBot.lastLocation.get(0, 3) < 260)
                    {

                        pathNumber = 1;

                    }
                    if(aresBot.lastLocation.get(0, 3) > -100 && aresBot.lastLocation.get(0, 3) < 40)
                    {

                        pathNumber = 2;

                    }
                    if(aresBot.lastLocation.get(0, 3) > -280 && aresBot.lastLocation.get(0, 3) < -155)
                    {

                        pathNumber = 3;

                    }

                }

                if(pathNumber == 1){

                    intake(true, true);
                    turn(-25, 2, false);
                    encoderDrive(0.4, 0.4, 12, 12, 3);
                    encoderDrive(0.4, 0.4, -12, -12, 3);
                    turn(-90, 3, false);
                    encoderDrive(0.6, 0.6, -8, -8, 3);
                    encoderDrive(0.7, 0.7, 40, 40, 3);
                    intake(true, false);
                    encoderDrive(0.7, 0.7, -40, -40, 3);
                    turn(0, 3, true);
                    break;

                }

                if(pathNumber == 2){

                    intake(true, true);
                    encoderDrive(0.4, 0.4, 8, 8, 3);
                    intake(false, true);
                    encoderDrive(0.4, 0.4, -8, -8, 3);
                    turn(-90, 3, true);
                    encoderDrive(1, 1, -8, -8, 3);
                    encoderDrive(0.4, 0.4, 54, 54, 3);
                    intake(true, false);
                    encoderDrive(0.4, 0.4, -54, -54, 3);
                    turn(0, 3, true);
                    break;

                }

                if(pathNumber == 3){

                    intake(true, true);
                    turn(25, 2, false);
                    encoderDrive(0.4, 0.4, 12, 12, 3);
                    intake(false, true);
                    encoderDrive(0.4, 0.4, -12, -12, 3);
                    turn(-90, 3, true);
                    encoderDrive(1, 1, -8, -8, 3);
                    encoderDrive(0.4, 0.4, 54, 54, 3);
                    intake(true, false);
                    encoderDrive(0.4, 0.4, -54, -54, 3);
                    turn(0, 3, true);
                    break;

                }

            }

        }
    }
}