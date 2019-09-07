package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Reverse")
public class ReverseTest extends OpMode{

    private boolean padState;
    private boolean counter;
    private boolean reverse;
    private static double neg;

    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;

    public void init(){

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        reverse = false;
        neg = 1;
    }

    public void loop(){

        if(gamepad1.right_trigger > 0) {
            reverse = true;
        } else {
            if (gamepad1.left_trigger > 0)  {
                reverse = false;
            }
        }
        if(reverse = true) {
            neg = -1;
        } else {
            if(reverse = false) {
                neg = 1;
            }
        }


        if (gamepad1.right_bumper == true) {
            counter = true;
        } else {
            if (gamepad1.left_bumper == true) {
                counter = false;
            }
        }

        if (counter == false) {
            double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = h * Math.cos(robotAngle) + (neg)*rightX;
            final double v2 = h * Math.sin(robotAngle) - (neg)*rightX;
            final double v3 = h * Math.sin(robotAngle) + (neg)*rightX;
            final double v4 = h * Math.cos(robotAngle) - (neg)*rightX;


            motorLeft.setPower((neg)*v1);
            motorRight.setPower((neg)*-v2);
            motorLeftBack.setPower((neg)*v3);
            motorRightBack.setPower((neg)*-v4);

        }
        if (counter == true) {
            double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = h * Math.cos(robotAngle) + (neg)*rightX;
            final double v2 = h * Math.sin(robotAngle) - (neg)*rightX;
            final double v3 = h * Math.sin(robotAngle) + (neg)*rightX;
            final double v4 = h * Math.cos(robotAngle) - (neg)*rightX;


            motorLeft.setPower((neg)*v1 / 3);
            motorRight.setPower((neg)*-v2 / 3);
            motorLeftBack.setPower((neg)*v3 / 3);
            motorRightBack.setPower((neg)*-v4 / 3);

        }

    }
}
