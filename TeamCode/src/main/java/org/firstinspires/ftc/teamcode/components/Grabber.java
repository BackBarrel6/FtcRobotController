package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.utils.HardwareMapping;

public class Grabber {

    private final HardwareMapping mapping;
    private final Gamepad prevGamepad = new Gamepad();

    public Grabber(HardwareMapping mapping) {
        this.mapping = mapping;
    }
    public void onInit()
    {
        mapping.ServoR.setPosition(0);
        mapping.ServoL.setPosition(0);
    }
    public void update(Gamepad gamepad) {
        mapping.ServoR.setPosition(gamepad.right_bumper && !prevGamepad.right_bumper ? 1 : 0);
        mapping.ServoL.setPosition(gamepad.left_bumper && !prevGamepad.left_bumper ? 1 : 0);
        prevGamepad.copy(gamepad);
    }
}
