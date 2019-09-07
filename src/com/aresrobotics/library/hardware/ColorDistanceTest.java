package com.aresrobotics.library.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "ColorDistanceTest")
public class ColorDistanceTest extends OpMode{

DistanceSensor colDisSense;

public void init(){

    colDisSense = hardwareMap.get(DistanceSensor.class, "disSense");

}

public void loop(){

    double numb = colDisSense.getDistance(DistanceUnit.MM);
    telemetry.addData("Distance", numb);
}

}
