package com.aresrobotics.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

public class vuforiaTest extends LinearOpMode {

    OpenGLMatrix lastLocation;

    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AdzxQPD/////AAABmWQr+AytbUpprVx2VNTgNiJgawbK313otTyXa3Th2KAhi06wLMwml/nAjh58jdIbDitq5cji21735oTIvYjaoFNdeEZzhQW6aieofpzebPDxtAUTXKVDws1MCES3iCBk2z0z8YhwaRfREOj6VdiqY1zPyhc5vBrnc8ioV2B1Jyuz56SGeHq9tmQ5KiYwUPfGSKZ1+p3vWqymEmwOcN7Ym/oOf6ZVLJgrd+NEJM2TNg1xwepmiexQVVwiBWpUDx9/Q6DpQIPpapiyoCDzXZoOMBUxxqw3HhHI1ZWA//utIdWRElWbZ2+Y4umURnZg54HT4PVe6nMzR8t56YXZUB851ly0D6G3WQoySEOkVg46CrL7";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        //after assetName: write the name of the .XML dataset to load in quotes(aresSkystoneStickers is just a temporary name)
        VuforiaTrackables aresSkystoneStickers = this.vuforia.loadTrackablesFromAsset("aresSkystoneStickers");

        //C3P0 is the name of one of the images, 0 shows that it is the first image
        VuforiaTrackable redPerimeterTarget1 = aresSkystoneStickers.get(0);
        redPerimeterTarget1.setName("redPerimeterTarget1");

        VuforiaTrackable redPerimeterTarget2 = aresSkystoneStickers.get(1);
        redPerimeterTarget2.setName("redPerimeterTarget2");

        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(aresSkystoneStickers);

        float mmPerInch        = 25.4f;
        float mmFTCFieldWidth  = (12*12 - 2) * mmPerInch;

        OpenGLMatrix redBlockSideBridgeTarget1 = OpenGLMatrix.translation(mmFTCFieldWidth/2, 13*mmPerInch, 0).multiplied(Orientation.getRotationMatrix(
                AxesReference.EXTRINSIC, AxesOrder.XZX,
                AngleUnit.DEGREES, 90, 90, 0));
                redPerimeterTarget1.setLocation(redBlockSideBridgeTarget1);



    }
}
