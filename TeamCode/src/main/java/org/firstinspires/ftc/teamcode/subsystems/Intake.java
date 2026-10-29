
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake {

    private final DcMotor intakeMotor1;
    private final DcMotor intakeMotor2;
    private final Gamepad driver1;
    private final Gamepad driver2;

    private static final double INTAKE_POWER = 1.0;
    private static final double REVERSE_POWER = -1.0;
    private static final double TRIGGER_THRESHOLD = 0.1;

    public Intake(OpMode opMode) {

        driver1 = opMode.gamepad1;
        driver2 = opMode.gamepad2;
        intakeMotor1 = opMode.hardwareMap.get(
                DcMotor.class, "intakeMotor");

        intakeMotor2 = opMode.hardwareMap.get(
                DcMotor.class, "deliveryMotor");

        intakeMotor1.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor1.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void teleOp() {

        // Driver 1 keeps trigger control
        boolean g1Forward =
                driver1.left_trigger > TRIGGER_THRESHOLD;
        boolean g1Reverse =
                driver1.right_trigger > TRIGGER_THRESHOLD;

        // Driver 2 uses thumbsticks
        boolean g2Forward =
                driver2.right_stick_y < -TRIGGER_THRESHOLD;
        boolean g2Reverse =
                driver2.left_stick_y < -TRIGGER_THRESHOLD;

        if (g1Forward || g2Forward) {
            if (!(g1Reverse || g2Reverse)) {
                intakeMotor1.setPower(INTAKE_POWER);
            } else {
                intakeMotor1.setPower(0);
            }
        } else if (g1Reverse || g2Reverse) {
            intakeMotor1.setPower(REVERSE_POWER);
        } else {
            intakeMotor1.setPower(0);
        }
    }

    public void intakeOn(double power) {
        intakeMotor1.setPower(
                Math.max(-1.0, Math.min(1.0, power)));
    }

    public void intakeOff() {
        intakeMotor1.setPower(0);
    }
}
