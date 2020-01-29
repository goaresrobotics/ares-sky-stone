package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveBase {

    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;

    boolean slowModeButton = false;

    private void driveBase(){

    }

    public void driveInit(HardwareMap hwMap){

        motorLeft = hwMap.dcMotor.get("motorLeft");
        motorRight = hwMap.dcMotor.get("motorRight");
        motorLeftBack = hwMap.dcMotor.get("motorLeftBack");
        motorRightBack = hwMap.dcMotor.get("motorRightBack");

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void runDrive(double leftX, double leftY, double rightX, boolean b, boolean a){

        double h = Math.hypot(leftX, -leftY);
        double robotAngle = Math.atan2(-leftY, leftX) - Math.PI / 4;
        final double v1 = h * Math.cos(robotAngle) + rightX;
        final double v2 = h * Math.sin(robotAngle) - rightX;
        final double v3 = h * Math.sin(robotAngle) + rightX;
        final double v4 = h * Math.cos(robotAngle) - rightX;

        if(b){

            slowModeButton=true;

        }
        if(a){

            slowModeButton=false;

        }

        if(slowModeButton){

            motorLeft.setPower(-v1 * 1/2);
            motorRight.setPower(v2 * 1/2);
            motorLeftBack.setPower(-v3 * 1/2);
            motorRightBack.setPower(v4 * 1/2);

        }
        if(!slowModeButton){

            motorLeft.setPower(-v1);
            motorRight.setPower(v2);
            motorLeftBack.setPower(-v3);
            motorRightBack.setPower(v4);

        }
    }
}
