package com.aresrobotics.samples.FailedAttempts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@Disabled
public class driveByGyro extends LinearOpMode {

    public DcMotor  motorLeft   = null;
    public DcMotor  motorRight  = null;
    public DcMotor  motorLeftBack = null;
    public DcMotor  motorRightBack = null;

    @Override
    public void runOpMode() {

        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeftBack = hardwareMap.get(DcMotor.class,"motorLeftBack");
        motorRightBack = hardwareMap.get(DcMotor.class, "motorRightBack");


        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);

        telemetry.addLine("initialized");
        telemetry.update();

        waitForStart();
        telemetry.clear();



    }

    public void turn(double angle, DcMotor motorLeft, DcMotor motorRight, DcMotor motorLeftBack, DcMotor motorRightBack)
    {

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        Orientation orientation = imu.getAngularOrientation();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);

        double left;
        double right;

        if(orientation.firstAngle-180>0)
        {

            left = 1;
            right = -1;

        }
        else
        {

            left = -1;
            right = 1;

        }
        while (orientation.firstAngle<angle) {
            motorLeft.setPower(left);
            motorLeftBack.setPower(left);
            motorRight.setPower(right);
            motorRightBack.setPower(right);
            orientation = imu.getAngularOrientation();
            telemetry.addData("1", orientation.firstAngle);
            telemetry.update();
        }

        motorLeft.setPower(0);
        motorLeftBack.setPower(0);
        motorRight.setPower(0);
        motorRightBack.setPower(0);

    }

}
