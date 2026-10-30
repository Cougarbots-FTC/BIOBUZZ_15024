package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeLatch {
    private Servo intakeLatch;
    public intakeLatch(HardwareMap hardwareMap) {
        intakeLatch = hardwareMap.get(Servo.class, "intakeLatch");
    }
//call this releaseLatch in the autos
    public void releaseLatch() {
        intakeLatch.setPosition(0.5);
    }
}
