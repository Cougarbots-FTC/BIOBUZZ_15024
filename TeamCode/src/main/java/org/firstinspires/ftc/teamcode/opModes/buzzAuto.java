
package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Buzz Auto")
public class buzzAuto extends LinearOpMode {

    private Servo intakeLatch;

    private DcMotor leftDrive;
    private DcMotor rightDrive;

    @Override
    public void runOpMode() {

        // Initialize servo
        intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");

        // Initialize drive motors
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        // Set motor directions
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Initial servo position
        intakeLatch.setPosition(1.0);

        // Wait for driver to press START
        waitForStart();

        if (opModeIsActive()) {

            // Release intake latch
            intakeLatch.setPosition(1.0);

            // Allow latch to release
            sleep(500);

            // Drive forward slowly
            leftDrive.setPower(0.25);
            rightDrive.setPower(0.25);

            // Drive forward for a short distance of time
            sleep(750);

            // Stop the robot
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
    }
}



