package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class intakeTest extends OpMode {

    private com.aresrobotics.subSystems.intake intake;

    @Override
    public void init() {

        intake.initIntake(hardwareMap);

    }

    @Override
    public void loop() {

        intake.runIntake(gamepad2.right_stick_y);
    }
}
