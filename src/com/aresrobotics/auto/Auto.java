package com.aresrobotics.auto;

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

    static final double COUNTS_PER_MOTOR_REV = 560;
    static final double DRIVE_GEAR_REDUCTION = -1;
    static final double WHEEL_DIAMETER_INCHES = 4;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;

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

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        if (opModeIsActive() && !isStopRequested()) {

            aresBot.motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            aresBot.motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            aresBot.motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            aresBot.motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
            aresBot.motorLeft.setPower(Math.abs(speed));
            aresBot.motorRight.setPower(Math.abs(speed));
            aresBot.motorRightBack.setPower(Math.abs(speed));
            aresBot.motorLeftBack.setPower(Math.abs(speed));

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

    public void turn(double angle) {
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        Orientation orientation = imu.getAngularOrientation();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);

        double left;
        double right;

        double PCoefficient = 0.03;

        while (orientation.firstAngle < angle && !isStopRequested() ) {

            double turnspeed = (angle - orientation.firstAngle) * PCoefficient;

            left = turnspeed;
            right = -turnspeed;

            telemetry.addData("Angle", orientation.firstAngle);
            telemetry.addData("Target", angle);
            telemetry.update();


            aresBot.motorLeft.setPower(right);
            aresBot.motorLeftBack.setPower(left);
            aresBot.motorRight.setPower(left);
            aresBot.motorRightBack.setPower(right);

            telemetry.addData("Gyro", orientation.firstAngle);
            telemetry.update();
        }

        while (orientation.firstAngle > angle && !isStopRequested()) {

            double turnspeed = (angle - orientation.firstAngle) * PCoefficient;

            left = turnspeed;
            right = -turnspeed;


            telemetry.addData("angle", orientation.firstAngle);
            telemetry.addData("Target", angle);
            telemetry.update();

            aresBot.motorLeft.setPower(left);
            aresBot.motorLeftBack.setPower(right);
            aresBot.motorRight.setPower(right);
            aresBot.motorRightBack.setPower(left);

            orientation = imu.getAngularOrientation();
            telemetry.addData("Gyro", orientation.firstAngle);
            telemetry.update();
        }

        aresBot.motorLeft.setPower(0);
        aresBot.motorLeftBack.setPower(0);
        aresBot.motorRight.setPower(0);
        aresBot.motorRightBack.setPower(0);

    }
/*
    public void skystoneFinder() {

        //skyStonePosition shows where the skyStone is. 1 mean the skystone is the first block from the bridge. 2 means the second block from the bridge. etc
        int skyStonePosition;

        boolean isBlockFound = false;

        if(!isBlockFound) {

            aresBot.motorLeft.setPower(0.3);
            aresBot.motorRight.setPower(0.3);
            aresBot.motorLeftBack.setPower(0.3);
            aresBot.motorRightBack.setPower(0.3);



        }

    }

    cpublic void intake(boolean in) {

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


    //If grabIsTrue is true it will grab, if grabIsTrue is false it will release
    public void trayGrab(boolean grabIsTrue) {

        double grabTray = 0.8;
        double releaseTray = 0.2;

        if(grabIsTrue){

            aresBot.trayGrabber.setPosition(grabTray);

        } else {

            aresBot.trayGrabber.setPosition(releaseTray);

        }

    }



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