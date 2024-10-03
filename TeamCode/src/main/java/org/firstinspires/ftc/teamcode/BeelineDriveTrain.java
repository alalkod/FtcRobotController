package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Beeline Drive Train")
public class BeelineDriveTrain extends LinearOpMode {
    private DcMotor flMotor, frMotor;
    private float yForce, yaw = 0;

    // Drive code
    public void drive() {
        yForce = -gamepad1.left_stick_y;
        yaw = gamepad1.right_stick_x;

        flMotor.setPower((yForce));
        frMotor.setPower((-yForce));
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Waiting");
        telemetry.update();

        // Initialization
        flMotor = hardwareMap.get(DcMotor.class, "fl");
        frMotor = hardwareMap.get(DcMotor.class, "fr");

        waitForStart();

        while (opModeIsActive()) {
            this.drive();
        }
    }
}
