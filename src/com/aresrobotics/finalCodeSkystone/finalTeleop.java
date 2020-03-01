package com.aresrobotics.finalCodeSkystone;

import com.aresrobotics.subSystems.arm;
import com.aresrobotics.subSystems.autoRetract;
import com.aresrobotics.subSystems.intake;
import com.aresrobotics.subSystems.lift;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.aresrobotics.subSystems.trayGrabber;
import com.aresrobotics.subSystems.driveBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOp")
public class finalTeleop extends OpMode{

    private com.aresrobotics.subSystems.intake intake = new intake();
    private com.aresrobotics.subSystems.lift lift = new lift();
    private trayGrabber grabber = new trayGrabber();
    private driveBase drive = new driveBase();

    @Override
    public void init() {

        drive.driveInit(hardwareMap);
        intake.initIntake(hardwareMap);
        lift.initLift(hardwareMap);
        grabber.initTrayGrab(hardwareMap);

    }

    @Override
    public void loop() {

        intake.runIntake(gamepad1.right_trigger, gamepad1.left_trigger);
        lift.runLift(gamepad2.dpad_down, gamepad2.left_trigger, gamepad2.right_trigger, gamepad2.x, gamepad2.y, gamepad2.left_bumper, gamepad2.right_bumper, gamepad2.left_stick_y,  telemetry);
        grabber.runGrabber(gamepad1.right_bumper, gamepad1.left_bumper);
        drive.runDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.b, gamepad1.a);

    }
}