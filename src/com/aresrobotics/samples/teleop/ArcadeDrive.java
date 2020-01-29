package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

//@TeleOp(name = "Drive")
public class ArcadeDrive extends OpMode {

    private boolean padState;
    private boolean counter;
    private boolean reverse;
    private double neg;

    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;
    private DcMotor spinner;
    private DcMotor slides;
    private DcMotor lift;
    private DcMotor lift2;
    private Servo intake;
    private Servo markerRelease;
    private Servo ratchet;
    private DigitalChannel MLS;
    /*private DigitalChannel MLS2;
    private DigitalChannel MLSV;
    private DigitalChannel MLSV2;*/

    static final double INCREMENT = 0.02;
    static final int CYCLE_MS = 50;
    static final double MAX_POS = 1.0;
    static final double MIN_POS = 0.173;

    double position = MAX_POS;

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void init() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");
        spinner = hardwareMap.dcMotor.get("spinner");
        slides = hardwareMap.dcMotor.get("slides");
        lift = hardwareMap.dcMotor.get("lift");
        lift2 = hardwareMap.dcMotor.get("lift2");
        intake = hardwareMap.servo.get("intake");
        markerRelease = hardwareMap.servo.get("markerRelease");
        ratchet = hardwareMap.servo.get("ratchet");
        MLS = hardwareMap.get(DigitalChannel.class, "MLS");
        /*MLS2 = hardwareMap.get(DigitalChannel.class, "MLS2");
        MLSV = hardwareMap.get(DigitalChannel.class, "MLSV");
        MLSV2 = hardwareMap.get(DigitalChannel.class, "MLSV2");*/

        markerRelease.setPosition(0.95);
        intake.setPosition(position);
        ratchet.setPosition(0.5);
        MLS.setMode(DigitalChannel.Mode.INPUT);
        /*MLS2.setMode(DigitalChannel.Mode.INPUT);
        MLSV.setMode(DigitalChannel.Mode.INPUT);
        MLSV2.setMode(DigitalChannel.Mode.INPUT);*/

        reverse = false;
        neg = 1;
    }

    @Override
    public void loop() {

        //Mecanum

        if(gamepad1.right_trigger>0.8) {
            reverse = true;
        } else {
            if (gamepad1.left_trigger>0.8)  {
                reverse = false;
            }
        }
            if(reverse = true) {
            neg = -1;
            } else {
            if(reverse = false) {
                neg = 1;
                }
            }


        if (gamepad1.right_bumper == true) {
                counter = true;
        } else {
            if (gamepad1.left_bumper == true) {
                counter = false;
            }
        }

        if (counter == false) {
            double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = h * Math.cos(robotAngle) + rightX;
            final double v2 = h * Math.sin(robotAngle) - rightX;
            final double v3 = h * Math.sin(robotAngle) + rightX;
            final double v4 = h * Math.cos(robotAngle) - rightX;


            motorLeft.setPower((neg)*v1);
            motorRight.setPower((neg)*-v2);
            motorLeftBack.setPower((neg)*v3);
            motorRightBack.setPower((neg)*-v4);

        }
        if (counter == true) {
            double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = h * Math.cos(robotAngle) + rightX;
            final double v2 = h * Math.sin(robotAngle) - rightX;
            final double v3 = h * Math.sin(robotAngle) + rightX;
            final double v4 = h * Math.cos(robotAngle) - rightX;


            motorLeft.setPower((neg)*v1 / 3);
            motorRight.setPower((neg)*-v2 / 3);
            motorLeftBack.setPower((neg)*v3 / 3);
            motorRightBack.setPower((neg)*-v4 / 3);

        }


        //Magnetic Limit Switches

        double liftNumber;
        double unstableNumber;
        double ratchetNumber = 0.710;

        /*if (MLSV.getState() == true) {
            liftNumber = 0.6;
        } else {
            if (MLSV2.getState() == true) {
            liftNumber = -0.6;
            }
            else {
            if (gamepad2.left_trigger > 0) {
                liftNumber = 0.5;
            } else {
                if (gamepad2.right_trigger > 0) {
                    liftNumber = -0.5;
                } else {
                    liftNumber = 0;
                }
            }
        }
    }
        if (MLS.getState() == true) {
            unstableNumber = 0.5;
        } else { if (MLS2.getState() == true) {
            unstableNumber = -0.5;
        } else { unstableNumber = gamepad2.left_stick_y; }

        }*/

        unstableNumber = gamepad2.left_stick_y;

        if (gamepad2.left_trigger > 0) {
            liftNumber = 0.5;
        } else {
            if (gamepad2.right_trigger > 0) {
                liftNumber = -0.5;
            } else {
                liftNumber = 0;
            }
        }
            slides.setPower(unstableNumber / 2);

            lift.setPower(liftNumber);
            lift2.setPower(liftNumber);

            if (MLS.getState() == true) {

            }

            //Lift Mechanism


            // Servo

                /*boolean dad = false;
                boolean dad2 = true;
            if (gamepad2.dpad_up == true && dad == false) {
                dad2 = dad;
                dad = true;
            } else {
                if (gamepad2.dpad_up == true && dad == true)
                dad2 = dad;
                dad = false;
            }


            if (dad2 == false) {
                ratchetNumber = 0.196;
            } else {
                if (dad2 = true) {
                    ratchetNumber = 0.960;
                }
            }
*/

                if(gamepad2.dpad_up == true){
                    padState = true;
                } else {
                    if(gamepad2.dpad_down == true){
                        padState = false;
                    }
                }

                if(padState == true){
                    ratchetNumber = 0.05098039215;
                } else {
                    if (padState == false){
                        ratchetNumber = 0.58823529411;
                    }
                }

            ratchet.setPosition(ratchetNumber);

            if (gamepad2.left_bumper) {
                position += INCREMENT;
                if (position >= MAX_POS) {
                    position = MAX_POS;
                }
            }
            if (gamepad2.right_bumper) {
                position -= INCREMENT;
                if (position <= MIN_POS) {
                    position = MIN_POS;
                }
            }

            intake.setPosition(position);
            sleep(CYCLE_MS);

            spinner.setPower(gamepad2.right_stick_y);


    }
}

