/*/
Might have to change the latch's name
After you hit start on the auto thing it releases the latch

/*/
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto latch release")
public class AutoLatchRelease extends LinearOpMode{
    @Override
    public void runOpMode() {
        Servo intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");

        waitForStart();
<<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/subsystems/intakeLatch.java
        while (opModeIsActive()) {
            if (gamepad1.a) {
                intakeLatch.setPosition(-0.5);
            } else if (gamepad1.b) {
                intakeLatch.setPosition(-0.0);
            }
        }
========
        telemetry.addData("it works");
        intakeLatch.setPosition(0.5);
>>>>>>>> 80d5f9e4457ba5396c2ae38566d83afdd6771d61:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opModes/AutoLatchRelease.java
    }
}
