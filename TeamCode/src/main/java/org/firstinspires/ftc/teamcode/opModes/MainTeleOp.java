package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.subsystems.Delivery;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveTest;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name="Main TeleOp")
public class MainTeleOp extends OpMode {

    private MecanumDriveTest mecanumDrive;
    private Outtake outTake;
    private CRServo lebronA;
    private CRServo lebronB;
    private Intake intake;
    private Delivery delivery;
    @Override

    public void init() {
        intake = new Intake(this);
        outTake = new Outtake(this);
        mecanumDrive = new MecanumDriveTest(this);
        delivery = new Delivery(this);


    }

    @Override
    public void loop() {
        mecanumDrive.mecanumDrive();
        outTake.teleOp();
        intake.teleOp();
        delivery.teleOp();
        //    outTake.addTelemetry(this);


    }

}
