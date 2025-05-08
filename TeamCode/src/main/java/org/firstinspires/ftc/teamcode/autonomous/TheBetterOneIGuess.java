package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "BetterAutoBeta")
public class TheBetterOneIGuess extends LinearOpMode {
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
        sleep(270);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(950);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(337);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backLeft.setPower(0.75);
        backRight.setPower(-0.75);
        sleep(200);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(337);
        armBase.setPower(-0.5);
        sleep(6500);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(135);
        bucket.setPosition(1);
        sleep(850);
        bucket.setPosition(0.5);
        sleep(400);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(120);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        armBase.setPower(0.75);
        sleep(4050);
        armBase.setPower(0);
        sleep(50);
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(600);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(10);
        // starting turn
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(-0.75);
        backRight.setPower(0.75);
        sleep(425);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(67);
        // loop #1
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(1585);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(135);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(1788);
        // loop #2
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(1788);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(168);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(1788);
        // loop #3
        frontLeft.setPower(0.75);
        frontRight.setPower(-0.75);
        backRight.setPower(0.75);
        backLeft.setPower(-0.75);
        sleep(1788);
        frontLeft.setPower(0.75);
        frontRight.setPower(0.75);
        backLeft.setPower(0.75);
        backRight.setPower(0.75);
        sleep(400);
        frontLeft.setPower(-0.75);
        frontRight.setPower(0.75);
        backRight.setPower(-0.75);
        backLeft.setPower(0.75);
        sleep(1500);
        // back to start (?)
        frontLeft.setPower(-0.75);
        frontRight.setPower(-0.75);
        backLeft.setPower(-0.75);
        backRight.setPower(-0.75);
        sleep(2300);
        // DA- (aka revenge of zero power on motors)4=


    }
}
