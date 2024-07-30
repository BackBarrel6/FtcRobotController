package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.HardwareMapping;
import org.firstinspires.ftc.teamcode.utils.PIDController;

public class Lift {

    public HardwareMapping mapping;
    private PIDController pidController;

    public Lift(HardwareMapping mapping) {
        this.mapping = mapping;
        this.pidController = new PIDController(0.1, 0.01, 0.05, -1.0, 1.0, 0.0, 0.0, false);
    }

    enum Pos {
        DOWN, MIDLOW, MIDHIGH, UP
    }

    final int[] LiftPositions = {0, 300, 600, 900};
    Pos position = Pos.DOWN;

    public void oninit() {
        int initialPosition = LiftPositions[position.ordinal()];
        mapping.motorLift.setTargetPosition(initialPosition);
        mapping.motorLift2.setTargetPosition(initialPosition);  // Set initial position for new motor
        mapping.motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mapping.motorLift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);  // Set mode for new motor
        pidController.setSetpoint(initialPosition);
    }

    public void update(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            moveUp();
        } else if (gamepad.dpad_down) {
            moveDown();
        }

        double currentPosition = mapping.motorLift.getCurrentPosition();
        double power = pidController.calculate(currentPosition);
        mapping.motorLift.setPower(power);
        mapping.motorLift2.setPower(power);  // Apply power to new motor
    }

    private void moveUp() {
        if (position == Pos.UP) {
            position = Pos.DOWN;
        } else {
            position = Pos.values()[position.ordinal() + 1];
        }
        int targetPosition = LiftPositions[position.ordinal()];
        pidController.setSetpoint(targetPosition);
        mapping.motorLift.setTargetPosition(targetPosition);
        mapping.motorLift2.setTargetPosition(targetPosition);  // Set target position for new motor
    }

    private void moveDown() {
        if (position.ordinal() > Pos.DOWN.ordinal()) {
            position = Pos.values()[position.ordinal() - 1];
            int targetPosition = LiftPositions[position.ordinal()];
            pidController.setSetpoint(targetPosition);
            mapping.motorLift.setTargetPosition(targetPosition);
            mapping.motorLift2.setTargetPosition(targetPosition);  // Set target position for new motor
        }
    }
}
