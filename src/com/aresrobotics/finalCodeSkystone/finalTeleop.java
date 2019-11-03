package com.aresrobotics.finalCodeSkystone;

import com.aresrobotics.subSystems.armCode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.aresrobotics.subSystems.intakeCode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.aresrobotics.subSystems.liftCode;
import com.aresrobotics.subSystems.trayGrabber;
import com.aresrobotics.subSystems.vroomVroom;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOp")
public class finalTeleop extends OpMode{

    private intakeCode intake;
    private liftCode lift;
    private trayGrabber grabber;
    private vroomVroom drive;
    private armCode arm;

    DcMotor intakeLeft;
    DcMotor intakeRight;
    DcMotor liftMotor;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    Servo spinner;
    Servo dropper;
    Servo trayGrabber;

    @Override
    public void init() {

        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");
        spinner = hardwareMap.servo.get("spinner");
        dropper = hardwareMap.servo.get("dropper");
        trayGrabber = hardwareMap.servo.get("trayGrabber");

    }

    @Override
    public void loop() {

    intake.intake(intakeLeft, intakeRight);
    lift.lift(liftMotor);
    grabber.grabber(trayGrabber);
    drive.drive(frontLeft, frontRight, backLeft, backRight);

    }
}