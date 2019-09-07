package com.aresrobotics.library.hardware;

import com.aresrobotics.samples.auto.Auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class AresSampleRobot
{
    public DcMotor  motorLeft;
    public DcMotor  motorRight;
    public DcMotor  motorRightBack;
    public DcMotor  motorLeftBack;
    public DcMotor lift;
    public DcMotor lift2;

    DistanceSensor colDisSense;

    private volatile boolean stopRequested = false;

    public Servo markerRelease;
    public Servo intake;
    public Servo ratchet;

    HardwareMap hwMap           =  null;
    Telemetry telemetry;
    private Auto auto;
    private ElapsedTime period  = new ElapsedTime();

    public AresSampleRobot(Telemetry telemetry, Auto auto)
    {
        this.telemetry = telemetry;
        this.auto = auto;
    }


    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        this.stopRequested   = false;
        motorLeft  = hwMap.get(DcMotor.class, "motorLeft");
        motorRight = hwMap.get(DcMotor.class, "motorRight");
        motorLeftBack  = hwMap.get(DcMotor.class, "motorLeftBack");
        motorRightBack  = hwMap.get(DcMotor.class, "motorRightBack");
        markerRelease = hwMap.get(Servo.class, "markerRelease");
        lift  = hwMap.get(DcMotor.class, "lift");
        lift2  = hwMap.get(DcMotor.class, "lift2");
        intake = hwMap.get(Servo.class, "intake");
        ratchet = hwMap.get(Servo.class, "ratchet");
        colDisSense = ahwMap.get(DistanceSensor.class, "disSense");

        motorLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        motorRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        intake.setPosition(1);
        ratchet.setPosition(0.19607843137);
        markerRelease.setPosition(0.96078431372);
        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        lift.setPower(0);
        lift2.setPower(0);


        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }



}