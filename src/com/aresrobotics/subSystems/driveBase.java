package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class driveBase {

    Gamepad localGamepad;

    public void driveBase(Gamepad gp){

        gp = localGamepad;

    }

    public void runDrive(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight){


        double h = Math.hypot(localGamepad.left_stick_x, -localGamepad.left_stick_y);
        double robotAngle = Math.atan2(-localGamepad.left_stick_y, localGamepad.left_stick_x) - Math.PI / 4;
        double rightX = localGamepad.right_stick_x;
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
