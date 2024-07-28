package org.firstinspires.ftc.teamcode;

public class PIDController {
    private double kP, kI, kD;  // PID coefficients
    private double setpoint;  // Desired target value
    private double previousError = 0;  // Error from the previous calculation
    private double integral = 0;  // Integral of the error
    private double maxOutput;  // Maximum output value
    private double minOutput;  // Minimum output value

    // Constructor to initialize the PID controller with coefficients and output limits
    public PIDController(double kP, double kI, double kD, double minOutput, double maxOutput) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
    }

    // Method to set the desired target value (setpoint)
    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    // Method to calculate the output value based on the current position
    public double calculate(double currentPosition) {
        double error = setpoint - currentPosition;  // Calculate the error
        integral += error;  // Accumulate the integral of the error
        double derivative = error - previousError;  // Calculate the derivative of the error
        previousError = error;  // Update the previous error

        // Calculate the PID output
        double output = kP * error + kI * integral + kD * derivative;

        // Clamp the output to the specified limits
        return Math.max(minOutput, Math.min(maxOutput, output));
    }
}
