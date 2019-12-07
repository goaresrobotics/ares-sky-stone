package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "mecanum")
public class    mecanum extends OpMode {
    //private ElapsedTime runtime = new ElapsedTime();


    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;

    private double LDistanceInches;
    private double LBDistanceInches;
    private double RDistanceInches;
    private double RBDistanceInches;

    /*private double previousPosL = 0;
    private double previousPosLB = 0;
    private double previousPosR = 0;
    private double previousPosRB = 0;

    private double rightDifference = 0;
    private double rightBackDifference = 0;
    private double leftDifference = 0;
    private double leftBackDifference = 0;*/

    static final double COUNTS_PER_MOTOR_REV = 560;
    static final double DRIVE_GEAR_REDUCTION = -1;
    static final double WHEEL_DIAMETER_INCHES = 4;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    //private boolean isTimeStarted = false;
    @Override
    public void init()
    {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    @Override
    public void loop()
    {
        /*if(!isTimeStarted) {

            runtime.reset();
            isTimeStarted = true;
        }*/

        LDistanceInches = motorLeft.getCurrentPosition() * COUNTS_PER_INCH;
        LBDistanceInches = motorLeftBack.getCurrentPosition() * COUNTS_PER_INCH;
        RDistanceInches = motorRight.getCurrentPosition() * COUNTS_PER_INCH;
        RBDistanceInches = motorRightBack.getCurrentPosition() * COUNTS_PER_INCH;

        /*rightBackDifference = RBDistanceInches - previousPosRB;
        rightDifference = RDistanceInches - previousPosR;
        leftBackDifference = LBDistanceInches - previousPosLB;
        leftDifference = LDistanceInches - previousPosL;*/


        double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = h * Math.cos(robotAngle) + rightX;
        final double v2 = h * Math.sin(robotAngle) - rightX;
        final double v3 = h * Math.sin(robotAngle) + rightX;
        final double v4 = h * Math.cos(robotAngle) - rightX;



        /*if(gamepad1.left_stick_x == 0.0 && gamepad1.left_stick_y == 0.0 && gamepad1.right_stick_x == 0.0 && leftDifference > 0.1) {



        }*/

        motorLeft.setPower(-v1 * 4/5);
        motorRight.setPower(v2 * 4/5);
        motorLeftBack.setPower(-v3 * 4/5);
        motorRightBack.setPower(v4 * 4/5);

        /*if (runtime.milliseconds() >= 100) {

            previousPosL = LDistanceInches;
            previousPosLB = LBDistanceInches;
            previousPosR = RDistanceInches;
            previousPosRB = RBDistanceInches;

            runtime.reset();

        }*/
    }
}