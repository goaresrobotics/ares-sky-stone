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
            PCoefficient = 0.0095;
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

        } else {

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

    public void detectSkystone(boolean isOnBlue) {

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
                    telemetry.addData("z", aresBot.lastLocation.get(1, 3));  //second value
                    //block1 175
                    //block2 200
                    //block3 196.5
                    telemetry.addData("z", aresBot.lastLocation.get(2, 3));  //third value
                    //block1 460
                    //block2 479
                    //block3 480
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

                    

                }

                if(pathNumber == 2){



                }

                if(pathNumber == 3){



                }

            }

        }
    }
}