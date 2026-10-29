package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto Latch Release")
public class AutoLatchRelease extends LinearOpMode {
    @Override
    public void runOpMode() {

        Servo intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");



        waitForStart();


        intakeLatch.setPosition(0.5);

        telemetry.addData("Status", "Latch Released!");
        telemetry.update();
    }
}
