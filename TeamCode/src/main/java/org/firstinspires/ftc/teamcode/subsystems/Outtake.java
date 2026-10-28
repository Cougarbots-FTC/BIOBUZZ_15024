
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Outtake {

    // Motors
    private final DcMotorEx outtakeB;
    private final DcMotorEx outtakeR;

    // OpMode and controller
    private final OpMode opMode;
    private final Gamepad driver2;

    // Toggle state
    private boolean outtakeOn = false;
    private boolean prevX = false;

    // Speed control (encoder ticks per second)
    private int outtakePower = 1500;

    private static final int POWER_STEP = 100;
    private static final int MAX_POWER = 1500;
    private static final int MIN_POWER = 0;

    public Outtake(OpMode opMode) {
        this.opMode = opMode;
        this.driver2 = opMode.gamepad2;

        outtakeB = opMode.hardwareMap.get(
                DcMotorEx.class, "outtakeB");

        outtakeR = opMode.hardwareMap.get(
                DcMotorEx.class, "outtakeR");

        outtakeB.setDirection(DcMotor.Direction.REVERSE);
        outtakeR.setDirection(DcMotor.Direction.REVERSE);

        outtakeB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtakeB.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.FLOAT);

        outtakeR.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.FLOAT);

        stop();
    }

    // Call this every loop in TeleOp
    public void teleOp() {
        handleToggle();
        handleSpeedAdjust();

        if (outtakeOn) {
            outtakeB.setVelocity(outtakePower);
            outtakeR.setVelocity(outtakePower);
        } else {
            stop();
        }

        sendTelemetry();
    }

    // Toggle ON/OFF with gamepad 2 X
    private void handleToggle() {
        boolean xPressed = driver2.x;

        if (xPressed && !prevX) {
            outtakeOn = !outtakeOn;
        }

        prevX = xPressed;
    }

    // Adjust speed with gamepad 2 D-pad
    private void handleSpeedAdjust() {
        if (driver2.dpadUpWasPressed()) {
            outtakePower += POWER_STEP;
        }

        if (driver2.dpadDownWasPressed()) {
            outtakePower -= POWER_STEP;
        }

        // Keep target velocity within safe configured limits
        outtakePower = Math.max(
                MIN_POWER,
                Math.min(MAX_POWER, outtakePower));
    }

    // Stop both motors
    private void stop() {
        outtakeB.setVelocity(0);
        outtakeR.setVelocity(0);
    }

    // Optional manual power control
    public void setPower(double power) {
        double safePower = Math.max(-1.0, Math.min(1.0, power));

        outtakeB.setPower(safePower);
        outtakeR.setPower(safePower);
    }

    // Turn off the outtake
    public void outtakeOff() {
        outtakeOn = false;
        stop();
    }

    // Telemetry
    private void sendTelemetry() {
        opMode.telemetry.addData(
                "Outtake Status", outtakeOn ? "ON" : "OFF");

        opMode.telemetry.addData(
                "Target Velocity", outtakePower);

        opMode.telemetry.addData(
                "Outtake B Velocity",
                "%.0f", outtakeB.getVelocity());

        opMode.telemetry.addData(
                "Outtake R Velocity",
                "%.0f", outtakeR.getVelocity());
    }
}
