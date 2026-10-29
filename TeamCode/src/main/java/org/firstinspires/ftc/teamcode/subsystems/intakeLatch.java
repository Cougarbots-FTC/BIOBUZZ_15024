
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto latch release")
public class AutoLatchRelease extends LinearOpMode {
    @Override
    public void runOpMode() {
        Servo intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");


 waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                intakeLatch.setPosition(-0.5);
            } else if (gamepad1.b) {
                intakeLatch.setPosition(-0.0);
            }
        }

        telemetry.addData("it works");
        telemetry.update();
        
    }
}
