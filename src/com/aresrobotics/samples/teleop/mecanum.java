package com.aresrobotics.samples.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "mecanum")
public class mecanum extends OpMode {


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
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    private double angleradians = 0;
    private double newX = 0;
    private double newY = 0;
    private double angle = 0;
    private double x = 0;
    private double y = 0;
    private double power = 0;
    private double joystickAngle = 0;
    private double joystickRobotAngle = 0;
    private double newRobotAngle = 0;

    @Override
    public void loop()
    {

        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);

        Orientation orientation  = gyro.getAngularOrientation();
        angle = orientation.firstAngle;
        angleradians = angle*(Math.PI/180);
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        power = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));  //check if zero and fix it later
        joystickAngle = Math.atan(x/y);
        joystickRobotAngle = angle-joystickAngle;
        newRobotAngle = joystickAngle-joystickRobotAngle;


        if((newRobotAngle<=90 && newRobotAngle>0)||(newRobotAngle>360 && newRobotAngle<=450)||(newRobotAngle<=-270&&newRobotAngle>-360))
        {
            newX = -Math.abs(power*(Math.cos(angleradians-Math.acos(x/power))));
            newY = Math.abs(power*(Math.sin(angleradians-Math.acos(x/power))));
        }
        if((newRobotAngle<=180 && newRobotAngle>90)||(newRobotAngle>450 && newRobotAngle<=540)||(newRobotAngle<=-180&&newRobotAngle>-270))
        {
            newX = -Math.abs(power*(Math.cos(angleradians-Math.acos(x/power))));
            newY = -Math.abs(power*(Math.sin(angleradians-Math.acos(x/power))));
        }
        if((newRobotAngle<=-90 && newRobotAngle>-180)||(newRobotAngle>180 && newRobotAngle<=270)||(newRobotAngle<=-450&&newRobotAngle>-540))
        {
            newX = Math.abs(power*(Math.cos(angleradians-Math.acos(x/power))));
            newY = -Math.abs(power*(Math.sin(angleradians-Math.acos(x/power))));
        }
        if((newRobotAngle<=0 && newRobotAngle>-90)||(newRobotAngle>270 && newRobotAngle<=360)||(newRobotAngle<=-360&&newRobotAngle>-450))
        {
            newX = Math.abs(power*(Math.cos(angleradians-Math.acos(x/power))));
            newY = Math.abs(power*(Math.sin(angleradians-Math.acos(x/power))));
        }

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

        telemetry.addData("oldX", x);
        telemetry.addData("oldY", y);
        telemetry.addData("newX", newX);
        telemetry.addData("newY", newY);
        telemetry.addData("angle", angle);
        telemetry.addData("power", power);
        telemetry.addData("joystickAngle", joystickAngle);
        telemetry.addData("joystickRobotAngle", joystickRobotAngle);
        telemetry.addData("newRobotAngle", newRobotAngle);
    }
}