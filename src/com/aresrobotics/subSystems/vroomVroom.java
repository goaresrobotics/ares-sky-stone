package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class vroomVroom extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    }

    public void drive(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight){


        double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = h * Math.cos(robotAngle) - rightX;
        final double v2 = h * Math.sin(robotAngle) + rightX;
        final double v3 = h * Math.sin(robotAngle) - rightX;
        final double v4 = h * Math.cos(robotAngle) + rightX;

        frontLeft.setPower(-v1);
        frontRight.setPower(v2);
        backLeft.setPower(-v3);
        backRight.setPower(v4);

    }
}
