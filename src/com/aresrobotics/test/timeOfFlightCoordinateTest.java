package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class timeOfFlightCoordinateTest extends OpMode {

    private DistanceSensor left;
    private DistanceSensor right;
    private DistanceSensor back;
    private DistanceSensor front;

    private double x;
    private double y;
    private double orientation;


    @Override
    public void init() {

         left = hardwareMap.get(DistanceSensor.class, "left");
         right = hardwareMap.get(DistanceSensor.class, "right");
         back = hardwareMap.get(DistanceSensor.class, "back");
         front = hardwareMap.get(DistanceSensor.class, "front");

    }

    @Override
    public void loop() {

    

    }
}