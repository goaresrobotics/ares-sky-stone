package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class robotLocalizationTest extends LinearOpMode {

    DcMotorEx motor1;
    DcMotorEx motor2;
    DcMotorEx motor3;
    DcMotorEx motor4;

    @Override
    public void runOpMode() {

        motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
        motor2 = hardwareMap.get(DcMotorEx.class, "motor2");
        motor3 = hardwareMap.get(DcMotorEx.class, "motor3");
        motor4 = hardwareMap.get(DcMotorEx.class, "motor4");

        while(!isStopRequested()) {

            motor1.getVelocity(AngleUnit.DEGREES);
            motor2.getVelocity(AngleUnit.DEGREES);
            motor3.getVelocity(AngleUnit.DEGREES);
            motor4.getVelocity(AngleUnit.DEGREES);



        }


    }
}
