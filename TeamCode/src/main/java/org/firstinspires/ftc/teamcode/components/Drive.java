package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.utils.HardwareMapping;

public class Drive {
    private Gamepad prevGamepad = new Gamepad();
    private final HardwareMapping mapping;

    public Drive(HardwareMapping mapping) {
        this.mapping = mapping;
    }

    public void update(Gamepad gamepad) {
        double x = gamepad.left_stick_x * 1.1;
        double y = -gamepad.left_stick_y;
        double r = gamepad.right_trigger - gamepad.left_trigger;

        double den = Math.abs(x) + Math.abs(y) + Math.abs(r);
        double power = (gamepad.a && !prevGamepad.a) ? 1.0 : 1.5;

        double frontRightPower = (y - x - r) / (den * power);
        double frontLeftPower = (y + x + r) / (den * power);
        double backLeftPower = (y - x + r) / (den * power);
        double backRightPower = (y + x - r) / (den * power);

        mapping.frontRightMotor.setPower(frontRightPower);
        mapping.frontLeftMotor.setPower(frontLeftPower);
        mapping.backRightMotor.setPower(backRightPower);
        mapping.backLeftMotor.setPower(backLeftPower);

        prevGamepad.copy(gamepad);
    }
}
