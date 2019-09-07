package com.aresrobotics.samples.FailedAttempts;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name= "Depot Trash")
@Disabled
public class depot extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftMotor;
    DcMotor rightMotor;

    final double COUNTS_PER_MOTOR_REV = 560;
    final double DRIVE_GEAR_REDUCTION = 40.0;
    final double WHEEL_DIAMETER_INCHES = 4.0;
    final double COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION / WHEEL_DIAMETER_INCHES * 3.1415;


    @Override
    public void runOpMode() {

        leftMotor  = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        encoderDrive(1, 1000000, 10000000);
    }

    public void encoderDrive(double speed, double leftInches, double rightInches)
    {

            int newLeftTarget;
            int newRightTarget;

            if (opModeIsActive()) {

                newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
                leftMotor.setTargetPosition(newLeftTarget);
                rightMotor.setTargetPosition(newRightTarget);

                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();
                leftMotor.setPower(Math.abs(speed));
                rightMotor.setPower(Math.abs(speed));

//                leftMotor.setPower(0);
//                rightMotor.setPower(0);

                leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }


}
