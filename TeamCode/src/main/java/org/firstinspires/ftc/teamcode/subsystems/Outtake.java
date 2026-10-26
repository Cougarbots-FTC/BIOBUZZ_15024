package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {

    // Motors
    private DcMotorEx outtakeB; //Used to be "leftShooter"
    private DcMotorEx outtakeR; //Used to be "rightShooter"

    // OpMode & Gamepads
    private final OpMode opMode;
    private final Gamepad driver2;

    // State
    private boolean outtakeOn = false;

    // Button edge tracking
    private boolean prevX = false;
    private boolean prevRB = false;
    private boolean prevLB = false;

    // Power control
    private double outtakePower = -0.8;
    private static final double POWER_STEP = 0.075;
    private static final double MAX_POWER = 1.0;
    private static final double MIN_POWER = -1.0;

    // Constructor
    public Outtake(OpMode opMode) {
        this.opMode = opMode;
        HardwareMap hardwareMap = opMode.hardwareMap;

        driver2 = opMode.gamepad2;

        outtakeB = hardwareMap.get(DcMotorEx.class, "outtake1");
        outtakeR = hardwareMap.get(DcMotorEx.class, "outtake2");

        outtakeB.setDirection(DcMotor.Direction.FORWARD);
        outtakeR.setDirection(DcMotor.Direction.FORWARD);

        outtakeB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        outtakeB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    // Call this every loop in TeleOp
    public void teleOp() {
        handleToggle();
        handleSpeedAdjust();

        if (outtakeOn) {
            setPower(outtakePower);
        } else {
            stop();
        }

        sendTelemetry();
    }

    // Toggle outtake ON/OFF with X
    private void handleToggle() {
        boolean xPressed = driver2.x;

        if (xPressed && !prevX) {
            outtakeOn = !outtakeOn;
        }

        prevX = xPressed;
    }

    // Adjust speed with bumpers
    private void handleSpeedAdjust() {
        boolean rb = driver2.right_bumper;
        boolean lb = driver2.left_bumper;

        if (rb && !prevRB) {
            outtakePower += POWER_STEP;
        }

        if (lb && !prevLB) {
            outtakePower -= POWER_STEP;
        }

        // Clamp power
        outtakePower = Math.max(MIN_POWER, Math.min(MAX_POWER, outtakePower));

        prevRB = rb;
        prevLB = lb;
    }

    // Motor helpers
    public void setPower(double power) {
        outtakeB.setPower(power);
        outtakeR.setPower(power);
    }

    private void stop() {
        outtakeB.setPower(0);
        outtakeR.setPower(0);
    }

    // Telemetry
    private void sendTelemetry() {
        opMode.telemetry.addData("Outtake", outtakeOn ? "ON" : "OFF");
        opMode.telemetry.addData("Outtake Power", "%.2f", outtakePower);
        opMode.telemetry.update();
    }
}