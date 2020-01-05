package com.aresrobotics.test;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


//@TeleOp(name = "Test : Servo")
@Disabled
public class intakeServo extends OpMode
{

    private Servo intake;

    static final double INCREMENT = 0.02;
    static final int CYCLE_MS = 50;
    static final double MAX_POS = 1;
    static final double MIN_POS = 0.196;

    double position = MAX_POS;
    boolean rampUp = true;

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void init()
    {
        intake = hardwareMap.servo.get("intake");
        intake.setPosition(1);
        telemetry.addLine(String.valueOf(position));
        telemetry.update();
    }

    @Override
    public void loop()
    {

        if (rampUp && gamepad2.left_bumper) {
            position += INCREMENT;
            if (position >= MAX_POS) {
                position = MAX_POS;
                rampUp = false;  // Switch ramp direction
            }
        }
        if (!rampUp && gamepad2.right_bumper) {
            position -= INCREMENT;
            if (position <= MIN_POS) {
                position = MIN_POS;
                rampUp = true;  // Switch ramp direction
            }
        }

        intake.setPosition(position);
        sleep(CYCLE_MS);
        telemetry.addLine(String.valueOf(position));
        telemetry.update();


    }
}
