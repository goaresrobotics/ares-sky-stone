package com.aresrobotics.samples.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="auto test if gyro is wrong or are we just bad", group="Samples")
public class AutoTestIfGyroIsWrongOrAreWeJustBad extends Auto
{

    @Override
    public void run()
    {
        encoderDrive(DRIVE_SPEED,  77,  -77, 10);
    }

}
