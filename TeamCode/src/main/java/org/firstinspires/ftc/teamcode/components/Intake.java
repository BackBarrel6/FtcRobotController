package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.HardwareMapping;

public class Intake {

    public final HardwareMapping mapping;

    public Intake(HardwareMapping mapping) {
        this.mapping=mapping;
    }

    public void update(Gamepad gamepad) {
        if(gamepad.a)mapping.intakeMotor.setPower(1);
        else if(gamepad.x)mapping.intakeMotor.setPower(-1);
        else mapping.intakeMotor.setPower(0);
    }
}
