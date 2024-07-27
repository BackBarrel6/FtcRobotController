package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="MainOP", group="Linear OpMode")
public class MainOP extends LinearOpMode {

    @Override
    public void runOpMode()
    {
        final HardwareMapping mapping= HardwareMapping.from(hardwareMap);
        Drive dt=new Drive (mapping);
        Intake intake=new Intake (mapping);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            dt.update (gamepad1);
            intake.update (gamepad2);
        }
    }
}
