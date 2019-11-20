package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "ToFCoordinateTest")
public class timeOfFlightCoordinateTest extends OpMode {

    private DistanceSensor left;
    private DistanceSensor right;
    private DistanceSensor back;
    private DistanceSensor front;

    private double x;
    private double y;
    private double previousX;
    private double previousY;

    private double displacedX;
    private double displacedY;

    private boolean isThereDisplacementX;
    private boolean isThereDisplacementY;

    private double orientation;
    private double leftDistance;
    private double rightDistance;
    private double backDistance;
    private double frontDistance;

    private boolean hasTimeStarted = false;

    public ElapsedTime passedTime;

    @Override
    public void init() {

        left = hardwareMap.get(DistanceSensor.class, "left");
        right = hardwareMap.get(DistanceSensor.class, "right");
        back = hardwareMap.get(DistanceSensor.class, "back");
        front = hardwareMap.get(DistanceSensor.class, "front");
        hasTimeStarted = false;

    }

    @Override
    public void loop() {

        telemetry.clearAll();

        if (!hasTimeStarted) {

            passedTime.startTime();
            hasTimeStarted = true;
            previousX = x;
            previousY = y;

        }

        if (x != 1000) {

            if (xInterruption(passedTime.milliseconds()) == true) {

                previousX = x;
                isThereDisplacementX = false;

                telemetry.addData("X Position", x);

            }


            if (xInterruption(passedTime.milliseconds()) == false) {

                displacedX = previousX - x;
                isThereDisplacementX = true;

                telemetry.addData("X Position", x + displacedX);

            }


        } else {


            telemetry.addData("X is Out of Range", x);

        }

        if (y != 1000) {

            if (yInterruption(passedTime.milliseconds()) == true) {

                previousY = y;
                isThereDisplacementX = false;
                telemetry.addData("Y Position", y);

            }


            if (yInterruption(passedTime.milliseconds()) == false) {

                displacedY = previousY - y;
                isThereDisplacementY = true;
                telemetry.addData("Y Position", y + displacedY);

            }

        } else {

            telemetry.addLine("Y is Out of Range");

        }

        telemetry.addData("Time", passedTime);

        if (passedTime.milliseconds() >= 100) {

            passedTime.reset();

        }

        telemetry.update();

    }

    public boolean xInterruption(double timePassed) {

        if (timePassed >= 100 && x - previousX > 3) {

            return false;

        } else {

            return true;

        }
    }

    public boolean yInterruption(double timePassed) {

        if (timePassed >= 100 && y - previousY > 3) {

            return false;

        } else {

            return true;

        }
    }
}