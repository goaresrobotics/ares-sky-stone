package com.aresrobotics.subSystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class driveBase {

    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;

    public static double v1Power;
    public static double v2Power;
    public static double v3Power;
    public static double v4Power;

    boolean slowModeButton = false;

    private void driveBase(){

    }

    public void driveInit(HardwareMap hwMap, Telemetry telemetry){

        motorLeft = hwMap.dcMotor.get("motorLeft");
        motorRight = hwMap.dcMotor.get("motorRight");
        motorLeftBack = hwMap.dcMotor.get("motorLeftBack");
        motorRightBack = hwMap.dcMotor.get("motorRightBack");

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());


    }

    public void runDrive(double leftX, double leftY, double rightX, boolean b, boolean a, boolean dPad_Up, boolean dPad_Down){

        double h = Math.hypot(leftX, -leftY);
        double robotAngle = Math.atan2(-leftY, leftX) - Math.PI / 4;
        final double v1 = h * Math.cos(robotAngle) + rightX;
        final double v2 = h * Math.sin(robotAngle) - rightX;
        final double v3 = h * Math.sin(robotAngle) + rightX;
        final double v4 = h * Math.cos(robotAngle) - rightX;

        if(v1 >= 0) {
            v1Power = Math.pow(v1, 2);
        } else {
            v1Power = -Math.pow(v1, 2);
        }

        if(v2 >= 0) {
            v2Power = Math.pow(v2, 2);
        } else {
            v2Power = -Math.pow(v2, 2);
        }

        if(v3 >= 0) {
            v3Power = Math.pow(v3, 2);
        } else {
            v3Power = -Math.pow(v3, 2);
        }

        if(v4 >= 0) {
            v4Power = Math.pow(v4, 2);
        } else {
            v4Power = -Math.pow(v4, 2);
        }


        if(b){

            slowModeButton=true;

        }
        if(a){

            slowModeButton=false;

        }

        if(dPad_Up) {

            v1Power = 0.5;
            v2Power = 0.5;
            v3Power = 0.5;
            v4Power = 0.5;

        }

        if(dPad_Down) {

            v1Power = -0.5;
            v2Power = -0.5;
            v3Power = -0.5;
            v4Power = -0.5;

        }

        if(slowModeButton){

            motorLeft.setPower(-v1Power * 1/2);
            motorRight.setPower(v2Power * 1/2);
            motorLeftBack.setPower(-v3Power * 1/2);
            motorRightBack.setPower(v4Power * 1/2);

        }
        if(!slowModeButton){

            motorLeft.setPower(-v1Power);
            motorRight.setPower(v2Power);
            motorLeftBack.setPower(-v3Power);
            motorRightBack.setPower(v4Power);

        }

    }
}
