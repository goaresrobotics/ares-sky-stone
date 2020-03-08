package com.aresrobotics.library.hardware;

import com.aresrobotics.auto.Auto;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

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

    public OpenGLMatrix lastLocation;

    public VuforiaLocalizer vuforia;

    public VuforiaTrackables Skystone;

    public VuforiaTrackable TargetElement;

    public List<VuforiaTrackable> allTrackables;

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

        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters vuforiaParameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        vuforiaParameters.vuforiaLicenseKey = "AdzxQPD/////AAABmWQr+AytbUpprVx2VNTgNiJgawbK313otTyXa3Th2KAhi06wLMwml/nAjh58jdIbDitq5cji21735oTIvYjaoFNdeEZzhQW6aieofpzebPDxtAUTXKVDws1MCES3iCBk2z0z8YhwaRfREOj6VdiqY1zPyhc5vBrnc8ioV2B1Jyuz56SGeHq9tmQ5KiYwUPfGSKZ1+p3vWqymEmwOcN7Ym/oOf6ZVLJgrd+NEJM2TNg1xwepmiexQVVwiBWpUDx9/Q6DpQIPpapiyoCDzXZoOMBUxxqw3HhHI1ZWA//utIdWRElWbZ2+Y4umURnZg54HT4PVe6nMzR8t56YXZUB851ly0D6G3WQoySEOkVg46CrL7";

        vuforiaParameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(vuforiaParameters);

        //after assetName: write the name of the .XML dataset to load in quotes
        Skystone = this.vuforia.loadTrackablesFromAsset("Skystone");

        TargetElement = Skystone.get(0);
        TargetElement.setName("TargetElement");

        allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(Skystone);

        OpenGLMatrix skyStoneLocation = OpenGLMatrix.translation(0, 0, 0).multiplied(Orientation.getRotationMatrix
                (AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES, 0, 0, 0));
        TargetElement.setLocation(skyStoneLocation);

        OpenGLMatrix phoneLocation = OpenGLMatrix.translation(0, 0, 0).multiplied(Orientation.getRotationMatrix
                (AxesReference.EXTRINSIC, AxesOrder.YZY, AngleUnit.DEGREES, 0, 0, 0));

        ((VuforiaTrackableDefaultListener) TargetElement.getListener()).setPhoneInformation(phoneLocation, vuforiaParameters.cameraDirection);


        telemetry.addData(">", "Waiting for start");
        telemetry.update();

    }
}