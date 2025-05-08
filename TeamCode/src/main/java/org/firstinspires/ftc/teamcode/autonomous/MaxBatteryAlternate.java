package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "MaxBatteryAlt")
public class MaxBatteryAlternate extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor armBase;
    private DcMotor intakeSliderBase;
    private Servo bucket;
    private Servo specimenEater;
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        armBase = hardwareMap.get(DcMotor.class, "armBase");
        intakeSliderBase = hardwareMap.get(DcMotor.class, "intakeSliderBase");
        bucket = hardwareMap.get(Servo.class, "bucket");
        specimenEater = hardwareMap.get(Servo.class, "specimenEater");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        armBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();



        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(243);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(855);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(303);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backLeft.setPower(0.75);
        backRight.setPower(-0.75);
        sleep(180);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(303);
        armBase.setPower(-0.75);
        sleep(4775);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(121);
        bucket.setPosition(1);
        sleep(765);
        bucket.setPosition(0.5);
        sleep(360);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(108);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        armBase.setPower(0.75);
        sleep(4272);
        armBase.setPower(0);
        sleep(45);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(540);
        // starting turn
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(-0.75);
        backRight.setPower(0.75);
        sleep(382);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(60);
        // loop #1
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(1426);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(121);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(1600);
        // loop #2
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(1500);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(151);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(1500);
        // loop #3
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(1500);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(1000);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(1450);
        // around the world
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(500);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(400);
        frontLeft.setPower(-0.75);
        frontRight.setPower(-0.75);
        backLeft.setPower(-0.75);
        backRight.setPower(-0.75);
        sleep(2000);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(-0.75);
        sleep(600);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
