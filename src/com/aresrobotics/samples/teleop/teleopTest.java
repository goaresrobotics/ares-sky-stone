package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "teleopTest")

public class teleopTest extends OpMode {

    double speed = 0;
    DcMotor motor0;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;


    @Override
    public void init() {

        motor0 = hardwareMap.dcMotor.get("motor0");
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");


    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            speed += 0.2;

        } else {
            if (gamepad1.left_bumper) {
                speed -= 0.2;
            }
            motor0.setPower(gamepad1.left_stick_y * speed);
            motor1.setPower(gamepad1.left_stick_y * speed);
            motor2.setPower(gamepad1.left_stick_y * speed);
            motor3.setPower(gamepad1.left_stick_y * speed);


                motor1.setPower(-gamepad1.right_stick_y);
                motor2.setPower(-gamepad1.right_stick_y);
                motor0.setPower(gamepad1.right_stick_y);
                motor3.setPower(gamepad1.right_stick_y);




                motor0.setPower(-gamepad1.right_stick_y);
                motor3.setPower(-gamepad1.right_stick_y);
                motor1.setPower(gamepad1.right_stick_y);
                motor2.setPower(gamepad1.right_stick_y);




        }


    }
}



