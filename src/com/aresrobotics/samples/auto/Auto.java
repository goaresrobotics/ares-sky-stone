package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public abstract class Auto extends LinearOpMode{
    public  AresSampleRobot aresBot   = new AresSampleRobot(telemetry, this);
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 560;
    static final double     DRIVE_GEAR_REDUCTION    = -2.0;
    static final double     WHEEL_DIAMETER_INCHES   = 4;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = -0.6;

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

        telemetry.addData("Path0",  "Starting at %7d :%7d",
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

            newLeftTarget = aresBot.motorLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = aresBot.motorRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = aresBot.motorLeftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = aresBot.motorRightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
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

                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
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
    public void turn(double angle, double turnspeed, DcMotor motorLeft, DcMotor motorRight, DcMotor motorLeftBack, DcMotor motorRightBack)
    {
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        Orientation orientation = imu.getAngularOrientation();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);


        double left;
        double right;

        left = turnspeed;
        right = -turnspeed;

        while (angle>Math.abs(orientation.firstAngle) && !isStopRequested()){
            motorLeft.setPower(left);
            motorLeftBack.setPower(left);
            motorRight.setPower(right);
            motorRightBack.setPower(right);
            orientation = imu.getAngularOrientation();
            telemetry.addData("Gyro", orientation.firstAngle);
            telemetry.update();
        }
        motorLeft.setPower(0);
        motorLeftBack.setPower(0);
        motorRight.setPower(0);
        motorRightBack.setPower(0);

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

    }

}
