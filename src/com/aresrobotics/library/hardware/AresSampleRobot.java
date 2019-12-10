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
    //public DcMotor intake1;
    //public DcMotor intake2;

    //public ColorSensor colorSense;

    private volatile boolean stopRequested = false;

   public Servo trayGrabber;

   public BNO055IMU imu;

    HardwareMap hwMap = null;
    Telemetry telemetry;
    private Auto auto;
    private ElapsedTime period = new ElapsedTime();

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

        //colorSense = hwMap.get(ColorSensor.class, "ColorSensor");

        //intake1 = hwMap.get(DcMotor.class, "intake1");
        //intake2 = hwMap.get(DcMotor.class, "intake2");

      //  trayGrabber = hwMap.get(Servo.class, "trayGrabber");

        BNO055IMU imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);

        motorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        motorRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        //intake1.setDirection(DcMotor.Direction.FORWARD);
        //intake2.setDirection(DcMotor.Direction.REVERSE);

       // trayGrabber.setDirection(Servo.Direction.FORWARD);

        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);

       // intake1.setPower(0);
        //intake2.setPower(0);

    //    trayGrabber.setPosition(0.8);

    //    colorSense.enableLed(false);

        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);



    }
}