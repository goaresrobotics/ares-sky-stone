package com.aresrobotics.finalCodeSkystone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.aresrobotics.subSystems.trayGrabber;
import com.aresrobotics.subSystems.driveBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOp")
public class finalTeleop extends OpMode{

    private com.aresrobotics.subSystems.intake intake;
    private com.aresrobotics.subSystems.lift lift;
    private trayGrabber grabber;
    private driveBase drive;
    private com.aresrobotics.subSystems.arm arm;

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
    Gamepad gp;

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
        gp.setGamepadId(1);
        //id of 1 refers to the second gamepad

    }

    @Override
    public void loop() {

    intake.runIntake(intakeLeft, intakeRight);
    lift.runLift(liftMotor);
    grabber.runGrabber(trayGrabber);
    drive.runDrive(frontLeft, frontRight, backLeft, backRight);
    arm.runArm(dropper, spinner);

    }
}