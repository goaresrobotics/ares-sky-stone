package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "ToF Test")
public class revTimeOfFlightTest extends LinearOpMode {

    private DistanceSensor timeOfFlight;

    @Override
    public void runOpMode() {

        timeOfFlight = hardwareMap.get(DistanceSensor.class, "timeOfFlight");

        telemetry.addData("-", "Waiting for Start");
        telemetry.update();

        waitForStart();

        while (!isStopRequested()) {

            telemetry.clear();

            if (timeOfFlight.getDistance(DistanceUnit.CM) > 0) {
                telemetry.addData("Distance in in", timeOfFlight.getDistance(DistanceUnit.INCH));
                telemetry.addData("Distance in cm", timeOfFlight.getDistance(DistanceUnit.CM));
            } else {
                telemetry.addData("-", "Distance equals zero");
            }
            telemetry.update();
        }
    }
}