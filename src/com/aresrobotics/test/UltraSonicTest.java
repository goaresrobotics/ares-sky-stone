package com.aresrobotics.test;

import com.aresrobotics.library.hardware.MaxSonarEZ1UltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//@TeleOp (name="UltraSonic Test")
@Disabled
public class UltraSonicTest extends LinearOpMode {

private DistanceUnit unit;

    @Override
    public void runOpMode() {
        MaxSonarEZ1UltrasonicSensor UltraSonic = new MaxSonarEZ1UltrasonicSensor(hardwareMap.analogInput.get("UltraSonic"));

        telemetry.addLine("initialized");
        telemetry.update();

        waitForStart();
        telemetry.clear();

        while (!isStopRequested()) {
            double unitIn = UltraSonic.getDistance(unit);
            telemetry.addData("Distance", unitIn);
        }
    }
}