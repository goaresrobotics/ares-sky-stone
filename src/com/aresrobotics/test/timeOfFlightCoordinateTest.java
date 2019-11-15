package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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

        if (left.getDistance(DistanceUnit.INCH) < 78.7401574803) {
            x = -141.23 / 2 + left.getDistance(DistanceUnit.INCH);
        } else {
            if (right.getDistance(DistanceUnit.INCH) < 78.7401574803) {
                x = 141.23 / 2 - right.getDistance(DistanceUnit.INCH);
            } else {
                //make this useless if it happens(this only happens if both sensors aren't working/out of range
                x = 100;
            }
        }

        if (front.getDistance(DistanceUnit.INCH) < 78.7401574803) {
            y = 141.23 / 2 - front.getDistance(DistanceUnit.INCH);
        } else {
            if (back.getDistance(DistanceUnit.INCH) < 78.7401574803) {
                y = -141.23 / 2 + back.getDistance(DistanceUnit.INCH);
            } else {
                //make this useless if it happens(this only happens if both sensors aren't working/out of range
                y = 100;
            }
        }

        if (!hasTimeStarted) {

            passedTime.startTime();
            hasTimeStarted = true;
            previousX = x;

        }

        if (xInterruption(passedTime.milliseconds()) == true) {

            previousX = x;
            isThereDisplacementX = false;

        }


        if (xInterruption(passedTime.milliseconds()) == false ) {

            displacedX = previousX - x;
            isThereDisplacementX = true;

        }

        if (passedTime.milliseconds() >= 100) {

            passedTime.reset();

        }

    }

    public boolean xInterruption(double timePassed) {

        if (timePassed >= 100 && x - previousX > 3) {

            return false;

        } else {

            return true;

        }


    }

}