package org.firstinspires.ftc.teamcode;


// RR-specific imports
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;

// Non-RR imports
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@Autonomous(name = "AutoRR", group = "Autonomous")


public class AutoRR extends LinearOpMode {
    // armBase class
    public class Lift {
        private DcMotorEx armBase;

        public Lift(HardwareMap hardwareMap) {
            armBase = hardwareMap.get(DcMotorEx.class, "armBase");
            armBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armBase.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        public class LiftUp implements Action {
            // checks if the lift motor has been powered on
            private boolean initialized = false;

            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on
                if (!initialized) {
                    armBase.setPower(0.8);
                    initialized = true;
                }

                // checks lift's current position
                double pos = armBase.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 3000.0) {
                    // true causes the action to rerun
                    return true;
                } else {
                    // false stops action rerun
                    armBase.setPower(0);
                    return false;
                }
                // overall, the action powers the lift until it surpasses
                // 3000 encoder ticks, then powers it off
            }
        }
        public Action liftUp() {
            return new LiftUp();
        }
        // within the Lift class
        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    armBase.setPower(-0.8);
                    initialized = true;
                }

                double pos = armBase.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > 100.0) {
                    return true;
                } else {
                    armBase.setPower(0);
                    return false;
                }
            }
        }

        public Action liftDown() {
            return new LiftDown();
        }
    }
    // claw class
    public class Claw {
        private Servo specimenEater;

        public Claw(HardwareMap hardwareMap) {
            specimenEater = hardwareMap.get(Servo.class, "specimenEater");
        }
        // within the Claw class
        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                specimenEater.setPosition(0.55);
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                specimenEater.setPosition(1.0);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }
    }
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-34, 56, Math.toRadians(0));
        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, initialPose);

        // make a Claw instance
        Claw claw = new Claw(hardwareMap);
        // make a Lift instance
        Lift lift = new Lift(hardwareMap);
        // vision here that outputs position
        int visionOutputPosition = 1;
        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
//                .waitSeconds(5)
                .strafeTo(new Vector2d(-34, 54));
//        .lineToX(-32);
        Action tab1Action = tab1.build();
//        TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
////                .lineToY(37)
////                .setTangent(Math.toRadians(0))
////                .lineToX(18)
////                .waitSeconds(3)
////                .setTangent(Math.toRadians(0))
////                .lineToXSplineHeading(46, Math.toRadians(180))
//                .waitSeconds(3);
//        TrajectoryActionBuilder tab3 = drive.actionBuilder(initialPose)
////                .lineToYSplineHeading(33, Math.toRadians(180))
////                .waitSeconds(2)
////                .strafeTo(new Vector2d(46, 30))
//                .waitSeconds(3);
//        Action trajectoryActionCloseOut = tab1.endTrajectory().fresh()
//                .build();
//        // actions that need to happen on init; for instance, a claw tightening.
//        Actions.runBlocking(claw.closeClaw());
        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Position during Init", position);
            telemetry.update();
        }
        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();
        if (isStopRequested()) return;
        Action trajectoryActionChosen;
        if (startPosition == 1) {
            trajectoryActionChosen = tab1.build(); }
//        } else if (startPosition == 2) {
//            trajectoryActionChosen = tab2.build();
//        } else {
//            trajectoryActionChosen = tab3.build();
//        }
        Actions.runBlocking(
//                new SequentialAction(
//                        trajectoryActionChosen,
//                        trajectoryActionCloseOut
//                )
                tab1Action
        );
    }
    
}
