package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;


    @Autonomous(name = "LinearAuto")
    public class LearningAutonomousJunk extends LinearOpMode{
        // call all motors
        private DcMotor frontLeft;
        private DcMotor frontRight;
        private DcMotor backLeft;
        private DcMotor backRight;
        private DcMotor armBase;
        private DcMotor intakeSliderBase;
        private CRServo theServo;
        // functions for movement
        void forwardField() {
            frontLeft.setPower(0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(0.5);
        }
        void turnRight() {
            frontLeft.setPower(0.5);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0.5);
        }
        void turnLeft() {
            frontLeft.setPower(0);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(0);
        }
        void stopAllMotors() {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
        void flightOfBee() {
            armBase.setPower(1);
        }
        void deathOfBee() {
            armBase.setPower(-1);
            sleep(800);
            armBase.setPower(0);
        }
        void aroundTheWorld() {
            forwardField();
            sleep(1500);
            turnRight();
            sleep(250);
            forwardField();
            sleep(1500);
            turnRight();
            sleep(250);
            forwardField();
            sleep(1500);
            turnRight();
            sleep(250);
            forwardField();
            sleep(1500);
            turnRight();
            sleep(250);
            stopAllMotors();
        }
        void toTheSky() {
            forwardField();
            sleep(750);
            flightOfBee();
            sleep(800);
            deathOfBee();
        }
        // the opmode code itself
        public void runOpMode() throws InterruptedException {
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            backRight = hardwareMap.get(DcMotor.class, "backRight");
            armBase = hardwareMap.get(DcMotor.class, "armBase");
            intakeSliderBase = hardwareMap.get(DcMotor.class, "intakeSliderBase");
            theServo = hardwareMap.get(CRServo.class, "theServo");
            waitForStart();
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            armBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            intakeSliderBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            // aroundTheWorldAroundTheWorld();
            toTheSky();
            aroundTheWorld();
        }
}
