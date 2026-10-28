
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Delivery {

    private final OpMode opMode;
    private final DcMotor deliveryMotor;

    private static final double DELIVERY_POWER = 0.7;
    private static final double TRIGGER_THRESHOLD = 0.1;

    public Delivery(OpMode opMode) {
        this.opMode = opMode;

        deliveryMotor = opMode.hardwareMap.get(
                DcMotor.class, "deliveryMotor");

        deliveryMotor.setDirection(DcMotor.Direction.FORWARD);
        deliveryMotor.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void teleOp() {

        boolean g1Forward =
                opMode.gamepad1.right_trigger > TRIGGER_THRESHOLD;
        boolean g1Reverse =
                opMode.gamepad1.left_trigger > TRIGGER_THRESHOLD;

        boolean g2Forward =
                opMode.gamepad2.right_trigger > TRIGGER_THRESHOLD;
        boolean g2Reverse =
                opMode.gamepad2.left_trigger > TRIGGER_THRESHOLD;

        double power = 0.0;

        // Either driver can operate the delivery independently.
        boolean forward = g1Forward || g2Forward;
        boolean reverse = g1Reverse || g2Reverse;

        if (forward && !reverse) {
            power = DELIVERY_POWER;
        } else if (reverse && !forward) {
            power = -DELIVERY_POWER;
        }

        deliveryMotor.setPower(power);

        opMode.telemetry.addData("Delivery Power", power);
        opMode.telemetry.addData("Driver 1 Forward", g1Forward);
        opMode.telemetry.addData("Driver 1 Reverse", g1Reverse);
        opMode.telemetry.addData("Driver 2 Forward", g2Forward);
        opMode.telemetry.addData("Driver 2 Reverse", g2Reverse);
    }

    public void deliveryOn(double power) {
        deliveryMotor.setPower(power);
    }

    public void deliveryOff() {
        deliveryMotor.setPower(0);
    }
}
