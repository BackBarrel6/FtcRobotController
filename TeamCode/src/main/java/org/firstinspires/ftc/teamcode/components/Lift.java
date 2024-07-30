package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.HardwareMapping;
import org.firstinspires.ftc.teamcode.utils.PIDController;

public class Lift {

    // Hardware mapping to control the motor
    public HardwareMapping mapping;
    private PIDController pidController;

    // Constructor to initialize the hardware mapping
    public Lift(HardwareMapping mapping) {
        this.mapping = mapping;
        this.pidController = new PIDController(0.1, 0.01, 0.05, -1.0, 1.0, 0.0, 0.0, 0.0);  // Example PID values
    }

    // Enumeration to represent the different lift positions
    enum Pos {
        DOWN, MIDLOW, MIDHIGH, UP
    }

    // Array to store target positions for the lift motor
    final int[] LiftPositions = {0, 300, 600, 900};
    // Variable to track the current position of the lift
    Pos position = Pos.DOWN;

    // Method to initialize the lift position
    public void oninit() {
        // Set the initial position of the lift to 'DOWN'
        mapping.motorLift.setTargetPosition(LiftPositions[position.ordinal()]);
        mapping.motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pidController.setSetpoint(LiftPositions[position.ordinal()]);
    }

    // Method to update the lift position based on gamepad input
    public void update(Gamepad gamepad) {
        // Move the lift up if dpad_up is pressed
        if (gamepad.dpad_up) {
            moveUp();
        }
        // Move the lift down if dpad_down is pressed
        else if (gamepad.dpad_down) {
            moveDown();
        }

        // Update motor power based on PID calculation
        double currentPosition = mapping.motorLift.getCurrentPosition();
        double power = pidController.calculate(currentPosition);
        mapping.motorLift.setPower(power);
    }

    // Method to move the lift up
    private void moveUp() {
        // If the lift is already at the highest position, reset to the lowest position
        if (position == Pos.UP) {
            position = Pos.DOWN;
        }
        // Otherwise, move to the next higher position
        else {
            position = Pos.values()[position.ordinal() + 1];
        }
        // Set the target position for the lift motor
        pidController.setSetpoint(LiftPositions[position.ordinal()]);
    }

    // Method to move the lift down
    private void moveDown() {
        // If the lift is not at the lowest position, move to the next lower position
        if (position.ordinal() > Pos.DOWN.ordinal()) {
            position = Pos.values()[position.ordinal() - 1];
            // Set the target position for the lift motor
            pidController.setSetpoint(LiftPositions[position.ordinal()]);
        }
    }
}
