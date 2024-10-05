package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Beeline Drive Train")
public class BeelineDriveTrain extends LinearOpMode {
    private DcMotor flMotor, frMotor;
    private DcMotor armMotor;
//    private Servo wristServo, grabServo;
    private float yForce, yaw, divisor;
    private float armForce;
//    private float wristForce, grabForce;

    // Drive code
    public void drive() {
        yForce = -gamepad1.left_stick_y;
        yaw = gamepad1.right_stick_x;

        divisor = Math.max(Math.abs(yForce) + Math.abs(yaw), 1);

        // forward/backward
        flMotor.setPower((yForce + yaw) / divisor);
        frMotor.setPower((-yForce + yaw) / divisor);
    }

    public void arm() {
        armForce = gamepad2.left_stick_y;
//        grabForce = -gamepad2.left_trigger + gamepad2.right_trigger;

        armMotor.setPower(armForce);
//        wristServo.setPower(wristForce);
//        grabServo.setPower(grabForce);
    }

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Waiting");
        telemetry.update();

        // Initialization
        flMotor = hardwareMap.get(DcMotor.class, "fl");
        frMotor = hardwareMap.get(DcMotor.class, "fr");
        armMotor = hardwareMap.get(DcMotor.class, "arm");
//        wristServo = hardwareMap.get(Servo.class, "main");
//        grabServo = hardwareMap.get(Servo.class, "small");

        waitForStart();

        while (opModeIsActive()) {
            this.drive();
            this.arm();
        }
    }
}
