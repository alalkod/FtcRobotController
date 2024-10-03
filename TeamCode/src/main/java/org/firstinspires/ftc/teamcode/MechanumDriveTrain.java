package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Mechanum Drive Train")
public class MechanumDriveTrain extends LinearOpMode {
    private DcMotor flMotor, frMotor, blMotor, brMotor;
    private float xForce, yForce, yaw, divisor;

    // Drive code
    public void drive() {
        xForce = gamepad1.left_stick_x;
        yForce = -gamepad1.left_stick_y;
        yaw = gamepad1.right_stick_x;

        divisor = Math.max(Math.abs(xForce) + Math.abs(yForce) + Math.abs(yaw), 1);

        flMotor.setPower((xForce + yForce + yaw) / divisor);
        frMotor.setPower((-xForce + yForce - yaw) / divisor);
        blMotor.setPower((-xForce + yForce + yaw) / divisor);
        brMotor.setPower((xForce + yForce - yaw) / divisor);
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Waiting");
        telemetry.update();

        // Initialization
        flMotor = hardwareMap.get(DcMotor.class, "fl");
        frMotor = hardwareMap.get(DcMotor.class, "fr");
        blMotor = hardwareMap.get(DcMotor.class, "bl");
        brMotor = hardwareMap.get(DcMotor.class, "br");

        waitForStart();

        while (opModeIsActive()) {
            this.drive();
        }
    }
}