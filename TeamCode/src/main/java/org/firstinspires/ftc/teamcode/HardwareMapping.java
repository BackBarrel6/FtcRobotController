package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class HardwareMapping {
    public static final String FRONT_LEFT_MOTOR = "motorFL";
    public static final String FRONT_RIGHT_MOTOR = "motorFR";
    public static final String BACK_LEFT_MOTOR = "motorBL";
    public static final String BACK_RIGHT_MOTOR = "motorBR";
    public static final String INTAKE_MOTOR = "motorIntake";
    public static final String LIFT_MOTOR = "motorLift";
    public static final String IMU = "imu";

    public final DcMotor motorLift, frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, intakeMotor;
    public final IMU imu;

    public HardwareMapping(DcMotor motorLift, DcMotor frontLeftMotor, DcMotor backLeftMotor, DcMotor frontRightMotor, DcMotor backRightMotor, DcMotor intakeMotor, IMU imu) {
        this.frontLeftMotor = frontLeftMotor;
        this.backLeftMotor = backLeftMotor;
        this.frontRightMotor = frontRightMotor;
        this.backRightMotor = backRightMotor;
        this.intakeMotor = intakeMotor;
        this.imu = imu;
        this.motorLift = motorLift;

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static HardwareMapping from(HardwareMap map) {
        return new HardwareMapping(
                map.dcMotor.get(LIFT_MOTOR),
                map.dcMotor.get(FRONT_LEFT_MOTOR),
                map.dcMotor.get(BACK_LEFT_MOTOR),
                map.dcMotor.get(FRONT_RIGHT_MOTOR),
                map.dcMotor.get(BACK_RIGHT_MOTOR),
                map.dcMotor.get(INTAKE_MOTOR),
                map.get(IMU.class, IMU) // Corrected IMU retrieval
        );
    }
}
