package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="latch test")
public class LatchReleaseTest extends LinearOpMode {


    private Servo intakeLatch;

    @Override
    public void runOpMode() {

        intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");


        waitForStart();


        while (opModeIsActive()) {


            if (gamepad1.a) {
                intakeLatch.setPosition(0.5);
            }

        }
    }
}
