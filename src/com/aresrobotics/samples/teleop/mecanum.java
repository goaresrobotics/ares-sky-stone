package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@TeleOp(name = "mecanum")
public class mecanum extends OpMode {


    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;

    @Override
    public void init()
    {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

    }

    @Override
    public void loop()
    {

        double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = h * Math.cos(robotAngle) - rightX;
        final double v2 = h * Math.sin(robotAngle) + rightX;
        final double v3 = h * Math.sin(robotAngle) - rightX;
        final double v4 = h * Math.cos(robotAngle) + rightX;

        motorLeft.setPower(-v1);
        motorRight.setPower(v2);
        motorLeftBack.setPower(-v3);
        motorRightBack.setPower(v4);
/*
 */
    }

}