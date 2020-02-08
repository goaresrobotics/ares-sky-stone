package com.aresrobotics.library.hardware;

import com.aresrobotics.auto.Auto;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.motors.RevRoboticsHdHexMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DistanceSensor;

public class AresSampleRobot {

    public DcMotor motorLeft;
    public DcMotor motorRight;
    public DcMotor motorRightBack;
    public DcMotor motorLeftBack;
    public Servo spinner;
    public Servo dropper;
    public Servo trayGrabberLeft;
    public Servo trayGrabberRight;
    public DcMotor armRotate;
    public DcMotor intakeLeft;
    public DcMotor intakeRight;
    public DcMotor liftMotor;

    private volatile boolean stopRequested = false;

    public BNO055IMU imu;

    HardwareMap hwMap = null;
    Telemetry telemetry;
    private Auto auto;

    public AresSampleRobot(Telemetry telemetry, Auto auto) {
        this.telemetry = telemetry;
        this.auto = auto;
    }


    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        this.stopRequested = false;
        motorLeft = hwMap.get(DcMotor.class, "motorLeft");
        motorRight = hwMap.get(DcMotor.class, "motorRight");
        motorLeftBack = hwMap.get(DcMotor.class, "motorLeftBack");
        motorRightBack = hwMap.get(DcMotor.class, "motorRightBack");
        spinner = hwMap.servo.get("spinner");
        dropper = hwMap.servo.get("dropper");
        armRotate = hwMap.dcMotor.get("armRotate");
        trayGrabberLeft = hwMap.servo.get("trayGrabberLeft");
        trayGrabberRight = hwMap.servo.get("trayGrabberRight");
        intakeLeft = hwMap.dcMotor.get("intakeLeft");
        intakeRight = hwMap.dcMotor.get("intakeRight");
        liftMotor = hwMap.dcMotor.get("liftMotor");

        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);

        motorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        motorRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);

        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);



    }
}