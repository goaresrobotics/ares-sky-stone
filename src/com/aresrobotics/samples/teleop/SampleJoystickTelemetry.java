package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Joystick TelemetryOld")
@Disabled
public class SampleJoystickTelemetry extends OpMode {

     DcMotor motorLeft;
     DcMotor motorRight;



    @Override
    public void init() {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");


    }

    @Override
    public void loop() {
        double speed = gamepad1.left_stick_y;
        double speed2 = gamepad1.right_stick_y;
        //double backward = -gamepad.left_stick_y;

        telemetry.addData("joystickY Left", gamepad1.left_stick_y);
        telemetry.addData("joystickY Right", gamepad1.right_stick_y);

        motorLeft.setPower(speed);
        motorRight.setPower(speed2);


    }
}
