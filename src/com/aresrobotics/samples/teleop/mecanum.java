package com.aresrobotics.samples.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "mecanum")
public class    mecanum extends OpMode {
    //private ElapsedTime runtime = new ElapsedTime();


    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;
    private BNO055IMU gyro;

    @Override
    public void init()
    {
        gyro = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        gyro.initialize(parameters);
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

    }

    @Override
    public void loop()
    {


        Orientation orientation  = gyro.getAngularOrientation();
        double angle = orientation.firstAngle;
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double power = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        double angleToZero = Math.acos(y/power);

        if(angleToZero-2*Math.PI*power>0)


                    {

        }
        double newX = power*(Math.cos(angle-Math.acos(x/power)));
        double newY = power*(Math.sin(angle-Math.acos(x/power)));

        double h = Math.hypot(newX, -newY);
        double robotAngle = Math.atan2(-newY, newX) - Math.PI / 4;
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