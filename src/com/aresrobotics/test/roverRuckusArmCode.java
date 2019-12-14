package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//@TeleOp(name = "roverRuckusArmCode")

public class roverRuckusArmCode extends OpMode {

    DcMotor liftMotor;


    @Override
    public void init()
    {


       liftMotor = hardwareMap.dcMotor.get("liftMotor");

    }

    public void loop()
    {
        liftMotor.setPower(gamepad2.right_stick_y);
    }

}
