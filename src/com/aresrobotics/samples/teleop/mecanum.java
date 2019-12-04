package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.lang.Math;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "mecanum")
public class    mecanum extends OpMode {


    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;
    private BNO055IMU gyrosensor;

    @Override
    public void init()
    {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");
        gyrosensor = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        gyrosensor.initialize(parameters);


    }

    @Override
    public void loop()
    {

        Orientation orientation  = gyrosensor.getAngularOrientation();
        telemetry.addData("1", orientation.firstAngle);

        double a = orientation.firstAngle;
        double x1 = gamepad1.left_stick_x;
        double y1 = gamepad1.left_stick_y;

        double newX = Math.sqrt(Math.pow(x1, 2)+ Math.pow(y1, 2))*Math.cos(a-Math.acos((x1)/Math.sqrt((Math.pow(x1, 2)+ Math.pow(y1, 2)))));
        double newY = Math.sqrt(Math.pow(x1, 2)+ Math.pow(y1, 2))*Math.sin(a-Math.acos((x1)/Math.sqrt((Math.pow(x1, 2)+ Math.pow(y1, 2)))));

        telemetry.addData("newX",newX);
        telemetry.addData("newY",newY);
        telemetry.addData("angle",a);
        telemetry.addData("gamepad y",y1);
        telemetry.addData("gamepad x",x1);
        telemetry.update();

        double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = h * Math.cos(robotAngle) + rightX;
        final double v2 = h * Math.sin(robotAngle) - rightX;
        final double v3 = h * Math.sin(robotAngle) + rightX;
        final double v4 = h * Math.cos(robotAngle) - rightX;

        motorLeft.setPower(-v1 * 4/5);
        motorRight.setPower(v2 * 4/5);
        motorLeftBack.setPower(-v3 * 4/5);
        motorRightBack.setPower(v4 * 4/5);

    }

}