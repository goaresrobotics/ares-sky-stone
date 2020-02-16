package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Vuforia Test")
public class vuforiaTest extends LinearOpMode {

    OpenGLMatrix lastLocation;

    VuforiaLocalizer vuforia;

    double path = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AdzxQPD/////AAABmWQr+AytbUpprVx2VNTgNiJgawbK313otTyXa3Th2KAhi06wLMwml/nAjh58jdIbDitq5cji21735oTIvYjaoFNdeEZzhQW6aieofpzebPDxtAUTXKVDws1MCES3iCBk2z0z8YhwaRfREOj6VdiqY1zPyhc5vBrnc8ioV2B1Jyuz56SGeHq9tmQ5KiYwUPfGSKZ1+p3vWqymEmwOcN7Ym/oOf6ZVLJgrd+NEJM2TNg1xwepmiexQVVwiBWpUDx9/Q6DpQIPpapiyoCDzXZoOMBUxxqw3HhHI1ZWA//utIdWRElWbZ2+Y4umURnZg54HT4PVe6nMzR8t56YXZUB851ly0D6G3WQoySEOkVg46CrL7";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        //after assetName: write the name of the .XML dataset to load in quotes
        VuforiaTrackables Skystone = this.vuforia.loadTrackablesFromAsset("Skystone");

        //C3P0 is the name of one of the images, 0 shows that it is the first image
        VuforiaTrackable TargetElement = Skystone.get(0);
        TargetElement.setName("TargetElement");


        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(Skystone);

        float mmPerInch = 25.4f;
        float mmFTCFieldWidth = (12 * 12-2) * mmPerInch;

        OpenGLMatrix skyStoneLocation = OpenGLMatrix.translation(0, 0, 0).multiplied(Orientation.getRotationMatrix
                (AxesReference.EXTRINSIC, AxesOrder.XZX, AngleUnit.DEGREES, 0, 0, 0));
        TargetElement.setLocation(skyStoneLocation);

        OpenGLMatrix phoneLocation = OpenGLMatrix.translation(0, 0, 0).multiplied(Orientation.getRotationMatrix
                (AxesReference.EXTRINSIC, AxesOrder.YZY, AngleUnit.DEGREES, 0, 0, 0));

        ((VuforiaTrackableDefaultListener) TargetElement.getListener()).setPhoneInformation(phoneLocation, parameters.cameraDirection);

        telemetry.addData(">", "Wait for start");
        telemetry.update();

        waitForStart();

        telemetry.clear();
        telemetry.update();

        Skystone.activate();

        while (opModeIsActive()) {
            for (VuforiaTrackable trackable : allTrackables) {
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
            }

            telemetry.clear();

            if (lastLocation == null) {
                telemetry.addData("Location:", "Unknown");
            } else {

                telemetry.addData("z", lastLocation.get(0, 3));  //first value

                //block1 193
                //block2 -37.5
                //block3 -219
                telemetry.addData("z", lastLocation.get(1, 3));  //second value
                //block1 175
                //block2 200
                //block3 196.5
                telemetry.addData("z", lastLocation.get(2, 3));  //third value
                //block1 460
                //block2 479
                //block3 480
                if(lastLocation.get(0, 3) > 140 && lastLocation.get(0, 3) < 260)
                {

                    path = 1;

                }
                if(lastLocation.get(0, 3) > -100 && lastLocation.get(0, 3) < 40)
                {

                    path = 2;

                }
                if(lastLocation.get(0, 3) > -280 && lastLocation.get(0, 3) < -155)
                {

                    path = 3;

                }

            }

            telemetry.addData("Path # ", path);

            telemetry.update();


        }

    }

    String format(OpenGLMatrix transformationMatrix) {
        return transformationMatrix.formatAsTransform();
    }

}