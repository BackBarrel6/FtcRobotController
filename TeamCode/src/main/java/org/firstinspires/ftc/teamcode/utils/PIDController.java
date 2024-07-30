package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController {
    private double kP, kI, kD, kF;  // PID and feedforward coefficients
    private double setpoint;  // Desired target value
    private double previousError = 0;  // Error from the previous calculation
    private double integral = 0;  // Integral of the error
    private double maxOutput;  // Maximum output value
    private double minOutput;  // Minimum output value
    private boolean outputClampingEnabled;  // Flag to enable/disable output clamping
    private ElapsedTime timer;  // Timer for calculating elapsed time
    private double previousDerivative = 0;  // Previous derivative value
    private double derivativeFilterCoefficient;  // Derivative filter coefficient

    // Constructor to initialize the PID controller with coefficients, output limits, and clamping flag
    public PIDController(double kP, double kI, double kD, double kF, double minOutput, double maxOutput, double derivativeFilterCoefficient, boolean outputClampingEnabled) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
        this.derivativeFilterCoefficient = derivativeFilterCoefficient;
        this.outputClampingEnabled = outputClampingEnabled;
        this.timer = new ElapsedTime();
    }

    // Method to set the desired target value (setpoint)
    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
        this.previousError = 0;
        this.integral = 0;
        this.timer.reset();  // Reset the timer when setting a new setpoint
    }

    // Method to enable or disable output clamping
    public void setOutputClampingEnabled(boolean enabled) {
        this.outputClampingEnabled = enabled;
    }

    // Method to calculate the output value based on the current position
    public double calculate(double currentPosition) {
        double deltaTime = timer.seconds();  // Get the elapsed time in seconds
        timer.reset();  // Reset the timer for the next calculation

        double error = setpoint - currentPosition;  // Calculate the error
        integral += error * deltaTime;  // Accumulate the integral of the error

        // Calculate the derivative of the error with filtering
        double rawDerivative = (error - previousError) / deltaTime;
        double derivative = previousDerivative + (derivativeFilterCoefficient * (rawDerivative - previousDerivative));
        previousDerivative = derivative;

        previousError = error;  // Update the previous error

        // Calculate the PID output with feedforward term
        double output = kP * error + kI * integral + kD * derivative + kF * setpoint;

        // Clamp the output to the specified limits if clamping is enabled
        if (outputClampingEnabled) {
             return Math.max(minOutput, Math.min(maxOutput, output));
        }

        return output;
    }
}
