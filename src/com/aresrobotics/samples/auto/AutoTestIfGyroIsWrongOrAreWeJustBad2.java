package com.aresrobotics.samples.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="auto test if gyro is wrong or are we just bad 2.0", group="Samples")
public class AutoTestIfGyroIsWrongOrAreWeJustBad2 extends Auto
{

    @Override
    public void run()
    {
        turn(179,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(165,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(150,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(135,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(120,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(105,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(75,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(60,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(45,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(30,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  3,  3, 10);
        turn(15,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  4,  4, 10);








    }

}

