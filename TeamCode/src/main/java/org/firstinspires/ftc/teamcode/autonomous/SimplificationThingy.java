package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "StrafeToLine")
public class SimplificationThingy extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor armBase;
    private DcMotor intakeSliderBase;
    private Servo bucket;
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        armBase = hardwareMap.get(DcMotor.class, "armBase");
        intakeSliderBase = hardwareMap.get(DcMotor.class, "intakeSliderBase");
        bucket = hardwareMap.get(Servo.class, "bucket");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        armBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        // initial strafe
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        backLeft.setPower(-0.5);
        sleep(1200);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(500);
        // starting turn
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(750);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(100);
        // loop #1
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        backLeft.setPower(-0.5);
        sleep(2300);
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
        sleep(200);
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backRight.setPower(-0.5);
        backLeft.setPower(0.5);
        sleep(2600);
        // loop #2
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        backLeft.setPower(-0.5);
        sleep(2600);
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
        sleep(250);
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backRight.setPower(-0.5);
        backLeft.setPower(0.5);
        sleep(2600);
        // loop #3
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        backLeft.setPower(-0.5);
        sleep(2600);
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
        sleep(250);
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backRight.setPower(-0.5);
        backLeft.setPower(0.5);
        sleep(2600);
        // back to start (?)
        frontLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);
        sleep(3250);
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backRight.setPower(-0.5);
        backLeft.setPower(0.5);
        sleep(550);
    }
}
