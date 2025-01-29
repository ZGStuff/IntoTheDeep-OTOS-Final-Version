package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;




    @TeleOp(name = "TeleOpStuff")
    public class TeleOpStuff extends LinearOpMode {

        private DcMotor frontLeft;
        private DcMotor frontRight;
        private DcMotor backLeft;
        private DcMotor backRight;
        private DcMotor armBase;
        private DcMotor intakeSliderBase;
        // private ColorSensor colSense;
        private CRServo intakeServo1;
        private CRServo intakeServo2;
        private Servo rotaenoWha;
        private DcMotor intakeServo3;
        private Servo bucket;
        private Servo specimenEater;
        private DcMotor varmClaw;

        // Init gamepad, motors + servo

        @Override
        public void runOpMode() {
            // Define all motors and servos
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            backRight = hardwareMap.get(DcMotor.class, "backRight");
            armBase = hardwareMap.get(DcMotor.class, "armBase");
            intakeSliderBase = hardwareMap.get(DcMotor.class, "intakeSliderBase");
//            colSense = hardwareMap.get(ColorSensor.class, "colSense");
            intakeServo1 = hardwareMap.get(CRServo.class, "theServo");
            intakeServo2 = hardwareMap.get(CRServo.class, "theUpAndDownServo");
            rotaenoWha = hardwareMap.get(Servo.class, "rotaenoWha");
            intakeServo3 = hardwareMap.get(DcMotor.class, "intakeMotor3");
            bucket = hardwareMap.get(Servo.class,"bucket");
            specimenEater = hardwareMap.get(Servo.class, "specimenEater");
            //varmClaw = hardwareMap.get(DcMotor.class, "varmClaw");
            intakeSliderBase.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            intakeSliderBase.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            intakeServo3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            armBase.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            // Variables
            double ticks = 	537.7;
            int currentPos = armBase.getCurrentPosition();
            int otherPos = intakeSliderBase.getCurrentPosition();


            // Put initialization blocks here.
//            frontLeft.setDirection(DcMotor.Direction.REVERSE);
//            backLeft.setDirection(DcMotor.Direction.REVERSE);
            armBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);




            // Main loop for the motors
            waitForStart();
            while (opModeIsActive()) {

                double leftFrontPower;
                double rightFrontPower;
                double leftBackPower;
                double rightBackPower;
                otherPos = intakeSliderBase.getCurrentPosition();
                telemetry.addData("encoder position of the intake!!!!", otherPos);
                telemetry.addData("intake motor 2 pos", intakeServo3.getCurrentPosition());
                telemetry.addData("LiftPos: ", armBase.getCurrentPosition());
                telemetry.update();
                // Gamepad movement code
                double drive = -gamepad1.left_stick_y;
                double strafe = gamepad1.left_stick_x;
                double turn = gamepad1.right_stick_x;
                leftFrontPower = Range.clip(drive + turn + strafe, -1, 1);
                rightFrontPower = Range.clip(drive - turn - strafe, -1, 1);
                leftBackPower = Range.clip(drive + turn - strafe, -1, 1);
                rightBackPower = Range.clip(drive - turn + strafe, -1, 1);
                if (gamepad1.left_bumper) {
                    leftFrontPower /= 2;
                    leftBackPower /= 2;
                    rightFrontPower /= 2;
                    rightBackPower /= 2 ;
                }

                if (gamepad1.right_bumper) {
                    leftFrontPower *= 1.8;
                    leftBackPower *= 1.8;
                    rightFrontPower *= 1.8;
                    rightBackPower *= 1.8;
                }

                frontLeft.setPower(leftFrontPower /= 1.8);
                frontRight.setPower(rightFrontPower /= 1.8);
                backLeft.setPower(leftBackPower /= 1.8);
                backRight.setPower(rightBackPower /= 1.8);

               //  Gamepad 2 intake servo movement code
                if (gamepad2.left_bumper) {
                    intakeServo1.setPower(0.5);
                    intakeServo2.setPower(-0.5);
                }
                else if (gamepad2.right_bumper) {
                    intakeServo1.setPower(-0.75);
                    intakeServo2.setPower(0.75);
                }
                else {
                    intakeServo1.setPower(0);
                    intakeServo2.setPower(0);
                }
               // Gamepad 2 v-arm slider movement code
                if (gamepad2.dpad_up) {
                    if (currentPos < 1) {
                        armBase.setPower(-0.85);
                    }
                } else if (gamepad2.dpad_down) {
                    if (currentPos < 1) {
                        armBase.setPower(0.85);
                    }
                } else {
                    armBase.setPower(0);
                }
               // GP2 intake servo code part 2
               if (gamepad2.x) {
                   rotaenoWha.setPosition(0.5);
               } else if (gamepad2.y) {
                   rotaenoWha.setPosition(1);
               }
               // gp2 intake servo code the third
                if (gamepad2.a) {
                    intakeServo3.setTargetPosition(306);
                    intakeServo3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    intakeServo3.setPower(0.1);
                } else if (gamepad2.b) {
                    intakeServo3.setTargetPosition(0);
                    intakeServo3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    intakeServo3.setPower(0.1);
                }
               // Gamepad 2 intake slider movement code
                if (gamepad2.dpad_right) {
                    if (otherPos > -2610) {
                        intakeSliderBase.setPower(0.5);
                    }
                } else if (gamepad2.dpad_left) {
                    if (otherPos < 1) {
                        intakeSliderBase.setPower(-0.5);
                    }
                } else {
                    intakeSliderBase.setPower(0);
                }
                
                // INTAKE ROTATE-O-TRON GOOOOOOOO
//                if (gamepad2.right_trigger > 0.5){
//                    rotaenoWha.setPosition(0);
//                } else if (gamepad2.left_trigger > 0.5){
//                    rotaenoWha.setPosition(1);
//                }

                // bucket code go brrrrrr (gamepad 1)
                if (gamepad1.a) {
                    bucket.setPosition(1);
                    
                } else if (gamepad1.b) {
                    bucket.setPosition(0.5);
                }
                // gamepad 1 specimen
                if (gamepad1.x) {
                    specimenEater.setPosition(1);
                } else if (gamepad1.y) {
                    specimenEater.setPosition(0);
                }
               // Gamepad 2 v-arm claw code
//                if (gamepad2.a) {
//                    varmClaw.setPower(1);
//
//                } else if (gamepad2.b) {
//                    varmClaw.setPower(-1);
//                } else {
//                    varmClaw.setPower(0);
//                }

                
/*
 change above code to elif?

                // Old code below
                // double max;


 Old code below
 double max;
 double axial = -gamepad1.left_stick_y;
 double lateral = gamepad1.left_stick_x;
 double yaw = gamepad1.right_stick_x;
 double frontLeftPwr = axial + lateral + yaw;
 double frontRightPwr = axial - lateral - yaw;
 double backLeftPwr = axial - lateral + yaw;
 double backRightPwr = axial + lateral - yaw;
 max = Math.max(Math.abs(frontLeftPwr), Math.abs(frontRightPwr));
 max = Math.max(max, Math.abs(backLeftPwr));
 max = Math.max(max, Math.abs(backRightPwr));
 if (max > 1.0) {
   frontLeftPwr /= max;
   frontRightPwr /= max;
   backLeftPwr /= max;
   backRightPwr /= max;
 }
 frontLeft.setPower(frontLeftPwr * 0.75);
 frontRight.setPower(frontRightPwr * 0.75);
 backLeft.setPower(backLeftPwr * 0.75);
 backRight.setPower(backRightPwr * 0.75);
      if (gamepad1.left_stick_y == -1) {
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);
      } else if (gamepad1.left_stick_y == 1) {
        frontLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(0.5);
        backRight.setPower(-0.5);
      } else if (gamepad1.left_stick_x == -1){
        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
      } else if (gamepad1.left_stick_x == 1) {
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);
      } else {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
      }
*/


            }
        }
    }
