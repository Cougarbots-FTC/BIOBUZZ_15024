/*/
Might have to change the latch's name
/*/
package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Intake Latch")
public class intakeLatch extends LinearOpMode{

    @Override
    public void runOpMode() {
        Servo intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                intakeLatch.setPosition(0.5);
            } else if (gamepad1.b) {
                intakeLatch.setPosition(0.0);
            }
        }
    }
}
