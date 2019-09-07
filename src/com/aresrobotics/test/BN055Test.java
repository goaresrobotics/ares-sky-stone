package com.aresrobotics.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp(name="imu test")
@Disabled
public class BN055Test extends LinearOpMode {

    @Override
    public void runOpMode() {
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);

        imu.initialize(parameters);

        telemetry.addLine("initialized");
        telemetry.update();

        waitForStart();
        telemetry.clear();

        while (!isStopRequested()) {
            Orientation orientation  = imu.getAngularOrientation();
            telemetry.addData("1", orientation.firstAngle);
            telemetry.addData("2", orientation.secondAngle);
            telemetry.addData("3", orientation.thirdAngle);
            telemetry.update();
        }
    }

}
