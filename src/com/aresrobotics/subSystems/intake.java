package com.aresrobotics.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class intake {

    public DcMotor intakeLeft;
    public DcMotor intakeRight;

    public Servo blockSlapper;

    public boolean isSlapping = false;
    public boolean slappingIn = true;
    public boolean slappingOut = false;

    public double slapperPosition;

    public void intake(){



    }

    public void initIntake(HardwareMap hwMap){

        intakeLeft = hwMap.dcMotor.get("intakeLeft");
        intakeRight = hwMap.dcMotor.get("intakeRight");

        blockSlapper = hwMap.servo.get("blockSlapper");

    }

       public void runIntake(float right_trigger, float left_trigger, boolean dpadLeft, boolean dpadUp, boolean dpadDown) {

        double intakeSpeed = right_trigger-left_trigger;

        intakeLeft.setPower(-intakeSpeed);
        intakeRight.setPower(intakeSpeed);


        if (dpadUp){

            slapperPosition = 0.9;

        }

           if (dpadDown) {

               slapperPosition = 0.15;

           }

        if (dpadLeft){

            slapperPosition = 0.4;

        }

        blockSlapper.setPosition(slapperPosition);

    }
}