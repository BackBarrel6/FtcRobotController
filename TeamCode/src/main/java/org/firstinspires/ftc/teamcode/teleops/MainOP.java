package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.Drive;
import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.Intake;
import org.firstinspires.ftc.teamcode.components.Lift;
import org.firstinspires.ftc.teamcode.utils.HardwareMapping;

@TeleOp(name="MainOP", group="Linear OpMode")
public class MainOP extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException
    {
        final HardwareMapping mapping= HardwareMapping.from(hardwareMap);
        Drive dt=new Drive (mapping);
        Intake intake=new Intake (mapping);
        Lift lift=new Lift(mapping);
        Grabber grabber=new Grabber(mapping);

        grabber.onInit();
        lift.oninit();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            dt.update (gamepad1);
            intake.update (gamepad2);
            lift.update (gamepad2);

        }
    }
}
