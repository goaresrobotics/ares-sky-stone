package com.aresrobotics.test;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


//@TeleOp(name = "Test: Slides")
public class slideTest extends OpMode{

    ///DigitalChannel touchMe;
    //DigitalChannel touchMeToo;

    private DcMotor lift;
    private DcMotor lift2;

    @Override
    public void init()
    {

        lift = hardwareMap.dcMotor.get("lift");
        lift2 = hardwareMap.dcMotor.get("lift2");
        //touchMe.setMode(DigitalChannel.Mode.INPUT);
        //touchMeToo.setMode(DigitalChannel.Mode.INPUT);

    }
    @Override
    public void loop()
    {
        double liftNumber;
        if (gamepad2.left_trigger > 0)
        {
            liftNumber = 0.5;
        }
        else
        {
            if (gamepad2.right_trigger > 0)
            {
                liftNumber = -0.5;
            }
            else
            {
                liftNumber = 0;
            }
        }

        lift.setPower(liftNumber/5);
        lift2.setPower(liftNumber/5);
        /*if (touchMe.getState() == true) {
            slides.setPower(-0.1); }
        else {
            slides.setPower(intakeSlide/15); }


        if (touchMeToo.getState() == true) {
            slides.setPower(0.1); }
        else {
            slides.setPower(intakeSlide/15); }*/

    }
}
