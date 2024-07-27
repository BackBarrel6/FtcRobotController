package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake {

    public final HardwareMapping mapping;

    public Intake(HardwareMapping mapping) {
        this.mapping=mapping;
    }

    public void update(Gamepad gamepad) {
        if(gamepad.a)mapping.intakeMotor.setPower(1);
        if(gamepad.x)mapping.intakeMotor.setPower(-1);
    }
}
