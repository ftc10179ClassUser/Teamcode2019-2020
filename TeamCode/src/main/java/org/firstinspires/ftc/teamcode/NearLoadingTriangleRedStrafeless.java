package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.lib.Configurator;
import org.firstinspires.ftc.teamcode.lib.Timeout;
import org.firstinspires.ftc.teamcode.lib.WheelController;

@Autonomous(name="NearLoadingTriangleRedStrafeless")
public class NearLoadingTriangleRedStrafeless extends LinearOpMode {
    Configurator config;
    WheelController wheelController;

    public void runOpMode(){
        config = new Configurator(this);
        wheelController = new WheelController(config);
        Servo foundationGrabber;


        foundationGrabber = hardwareMap.servo.get("foundationGrabber");
        foundationGrabber.setPosition(1);

        waitForStart();
        wheelController.runWithoutEncoder();

        //move to the foundation
        wheelController.moveXY(-0.25, -0.25);
        Timeout.waitUnlessInterrupt(3500, () -> (!opModeIsActive()));

        //stop at the foundation
        wheelController.stopWheels();

        //deploy the foundation grabber
        foundationGrabber.setPosition(0);
        Timeout.waitUnlessInterrupt(800, () -> (!opModeIsActive()));

        //move back to the loading zone
        wheelController.moveXY(0, 0.4);
        Timeout.waitUnlessInterrupt(4000, () -> (!opModeIsActive()));

        //turn to nudge the foundation into the triangle
        wheelController.moveTurn(0.5);
        Timeout.waitUnlessInterrupt(600, () -> (!opModeIsActive()));

        //park
        wheelController.stopWheels();

        //retract the foundation grabber
        foundationGrabber.setPosition(1);
        Timeout.waitUnlessInterrupt(800, () -> (!opModeIsActive()));
    }
}
