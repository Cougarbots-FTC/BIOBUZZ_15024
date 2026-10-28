
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake {

    private final DcMotor intakeMotor1;
    private final DcMotor intakeMotor2;
    private final Gamepad driver1;

    private static final double INTAKE_POWER = 1.0;
    private static final double REVERSE_POWER = -1.0;
    private static final double TRIGGER_THRESHOLD = 0.1;

    public Intake(OpMode opMode) {

        driver1 = opMode.gamepad1;

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

        boolean rightTrigger =
                driver1.right_trigger > TRIGGER_THRESHOLD;
        boolean leftTrigger =
                driver1.left_trigger > TRIGGER_THRESHOLD;

        if (rightTrigger && !leftTrigger) {
            // Right trigger: reverse intake
            intakeMotor1.setPower(REVERSE_POWER);
        } else if (leftTrigger && !rightTrigger) {
            // Left trigger: forward intake
            intakeMotor1.setPower(INTAKE_POWER);
        } else {
            // Neither trigger, or both triggers: stop
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
