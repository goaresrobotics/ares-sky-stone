package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@TeleOp(name = "Sample: TeleOp Test", group = "Samples")
@Disabled
public class SampleJoystickTeleop extends OpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;


    @Override
    public void init() {
        // setup the motors
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
    }


    @Override
    public void loop() {
        double speedLeft = gamepad1.left_stick_y;
        double speedRight = gamepad1.right_stick_y;

        motorLeft.setPower(speedLeft);
        motorRight.setPower(-speedLeft);

        telemetry.addData("joystickX", gamepad1.left_stick_x);
        telemetry.addData("joystickY", gamepad1.left_stick_y);
    }
}
