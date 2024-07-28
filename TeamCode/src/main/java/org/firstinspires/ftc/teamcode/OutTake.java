package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class OutTake {

    // Hardware mapping to control the motor
    public HardwareMapping mapping;

    // Constructor to initialize the hardware mapping
    public OutTake(HardwareMapping mapping) {
        this.mapping = mapping;
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
        mapping.motorLift.setTargetPosition(LiftPositions[position.ordinal()]);
    }

    // Method to move the lift down
    private void moveDown() {
        // If the lift is not at the lowest position, move to the next lower position
        if (position.ordinal() > Pos.DOWN.ordinal()) {
            position = Pos.values()[position.ordinal() - 1];
            // Set the target position for the lift motor
            mapping.motorLift.setTargetPosition(LiftPositions[position.ordinal()]);
        }
    }
}
