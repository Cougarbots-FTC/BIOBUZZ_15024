package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intakeLatch;

@Autonomous(name="Auto Latch Release test")
public class AutoLatchReleaseTest extends LinearOpMode {
    @Override
    public void runOpMode() {

        intakeLatch latch = new intakeLatch(hardwareMap);

        waitForStart();
        latch.releaseLatch();

        telemetry.addData("Status", "works");
        telemetry.update();

    }
}

