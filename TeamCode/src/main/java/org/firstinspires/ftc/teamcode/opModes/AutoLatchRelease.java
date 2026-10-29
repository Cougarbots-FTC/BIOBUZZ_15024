/*/
Might have to change the latch's name
After you hit start on the auto thing it releases the latch

/*/
package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto latch release")
public class AutoLatchRelease extends LinearOpMode{
    @Override
    public void runOpMode() {
        Servo intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");

        waitForStart();
        telemetry.addData("it works");
        intakeLatch.setPosition(0.5);
    }
}
