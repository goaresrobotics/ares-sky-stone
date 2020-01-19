package com.aresrobotics.samples.teleop;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "fieldCentricDriveVisualTest")
public class fieldCentricDriveVisualTest extends OpMode {

    private BNO055IMU gyro;

    @Override
    public void init()
    {
        gyro = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        gyro.initialize(parameters);

    }

    private double newX = 0;
    private double newY = 0;

    @Override
    public void loop()
    {


        Orientation orientation  = gyro.getAngularOrientation();
        double angle = orientation.firstAngle;
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double power = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        double joystickAngle = Math.atan(x/y);
        double joystickRobotAngle = angle-joystickAngle;
        double newRobotAngle = joystickAngle-joystickRobotAngle;


        if((newRobotAngle<=90 && newRobotAngle>0)||(newRobotAngle>360 && newRobotAngle<=450)||(newRobotAngle<=-270&&newRobotAngle>-360))
        {
            newX = -Math.abs(power*(Math.cos(angle-Math.acos(x/power))));
            newY = Math.abs(power*(Math.sin(angle-Math.acos(x/power))));
        }
        if((newRobotAngle<=180 && newRobotAngle>90)||(newRobotAngle>450 && newRobotAngle<=540)||(newRobotAngle<=-180&&newRobotAngle>-270))
        {
            newX = -Math.abs(power*(Math.cos(angle-Math.acos(x/power))));
            newY = -Math.abs(power*(Math.sin(angle-Math.acos(x/power))));
        }
        if((newRobotAngle<=-90 && newRobotAngle>-180)||(newRobotAngle>180 && newRobotAngle<=270)||(newRobotAngle<=-450&&newRobotAngle>-540))
        {
            newX = Math.abs(power*(Math.cos(angle-Math.acos(x/power))));
            newY = -Math.abs(power*(Math.sin(angle-Math.acos(x/power))));
        }
        if((newRobotAngle<=0 && newRobotAngle>-90)||(newRobotAngle>270 && newRobotAngle<=360)||(newRobotAngle<=-360&&newRobotAngle>-450))
        {
            newX = Math.abs(power*(Math.cos(angle-Math.acos(x/power))));
            newY = Math.abs(power*(Math.sin(angle-Math.acos(x/power))));
        }

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
