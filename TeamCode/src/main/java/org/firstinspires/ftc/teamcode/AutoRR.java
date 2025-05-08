package org.firstinspires.ftc.teamcode;


// RR-specific imports
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;

// Non-RR imports
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
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
@Autonomous(name = "AutoRR-HB-SR", group = "Autonomous")


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
                    armBase.setTargetPosition(-4370);
                    armBase.setPower(0.8);
                    armBase.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    initialized = true;
                }

                // checks lift's current position
                double pos = armBase.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > -4370.0) {
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
                    armBase.setTargetPosition(0);
                    armBase.setPower(0.8);
                    armBase.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    initialized = true;
                }

                double pos = armBase.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 100.0) {
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
    // bucket class
    public class Bucket {
        private Servo bucket;

        public Bucket(HardwareMap hardwareMap) {
            bucket = hardwareMap.get(Servo.class, "bucket");
        }
        // within the Bucket class
        public class RaiseBucket implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bucket.setPosition(0.55);
                return false;
            }
        }
        public Action raiseBucket() {
            return new RaiseBucket();
        }

        public class DumpBucket implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bucket.setPosition(1.0);
                return false;
            }
        }
        public Action dumpBucket() {
            return new DumpBucket();
        }
    }
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(32.75, 63.25, Math.toRadians(0));
        SparkFunOTOSDrive drive = new SparkFunOTOSDrive(hardwareMap, initialPose);

        // make a Claw instance
        Claw claw = new Claw(hardwareMap);
        // make a Lift instance
        Lift lift = new Lift(hardwareMap);
        Bucket bucket = new Bucket(hardwareMap);
        // vision here that outputs position
        int visionOutputPosition = 1;
        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
//                .waitSeconds(5)
//                .strafeTo(new Vector2d(-34, 50));
//                .turn(Math.toRadians(180))
//        .lineToX(-24)
//                .turn(Math.toRadians(180));
                .afterTime(0, drive.SetBucketPos(0.5))
                .splineTo(new Vector2d(52,57), Math.toRadians(-45))
                .afterTime(0, drive.SetLiftTarget(-4385))
                .waitSeconds(2)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(59.5,63, Math.toRadians(-67.5)), Math.toRadians(67.5))
                .afterTime(1, drive.SetBucketPos(1))
                .afterTime(1.5, drive.SetBucketPos(0.5))
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(51.5, 51))
                .afterTime(0.5, drive.SetLiftTarget(0))
                .afterTime(0.5, drive.LiftStop())
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(36, 24, Math.toRadians(0)), Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(46, 10, Math.toRadians(0)), Math.toRadians(90))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(46, 63))
                .strafeTo(new Vector2d(46, 10))
                .setTangent(0)
                .splineTo(new Vector2d(55, 10), Math.toRadians(0))
                .strafeTo(new Vector2d(55, 60))
                .strafeTo(new Vector2d(55, 10))
                .setTangent(0)
                .splineTo(new Vector2d(65, 10), Math.toRadians(0))
                .strafeTo(new Vector2d(65, 60))
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(0, 38, Math.toRadians(270)), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-52, 56, Math.toRadians(180)), Math.toRadians(180));



//
//
//                .setTangent(0)
//
//                .splineToLinearHeading(new Pose2d(59.5, 63, Math.toRadians(-67.5)), Math.toRadians(67.5));
        //.setTangent(0)
//                .splineToSplineHeading(new Pose2d(37, 28, Math.toRadians(0)), Math.toRadians(270))
//                .splineToSplineHeading(new Pose2d(41, 10, Math.toRadians(0)), Math.toRadians(90))
//                .waitSeconds(0.5)
//                .strafeTo(new Vector2d(41, 62.5))
//                .strafeTo(new Vector2d(41, 10))
//                .setTangent(0)
//                .splineTo(new Vector2d(55, 10), Math.toRadians(0))
//                .strafeTo(new Vector2d(55, 62.5))
//                .strafeTo(new Vector2d(55, 10))
//                .setTangent(0)
//                .splineTo(new Vector2d(65, 10), Math.toRadians(0))
//                .strafeTo(new Vector2d(65, 58))
//                .setTangent(Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(0, 38, Math.toRadians(270)), Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(-42, 56, Math.toRadians(180)), Math.toRadians(180));
        Action tab1Action = tab1.build();
        TrajectoryActionBuilder traj2 = tab1.endTrajectory().fresh()
                .strafeTo(new Vector2d(51.5, 51));
        Action trajectory2 = traj2.build();
        TrajectoryActionBuilder traj3 = traj2.endTrajectory().fresh()
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(36, 24, Math.toRadians(0)), Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(46, 10, Math.toRadians(0)), Math.toRadians(90))
                .strafeTo(new Vector2d(46, 56))
                .strafeTo(new Vector2d(46, 10))
                .setTangent(0)
                .splineTo(new Vector2d(55, 10), Math.toRadians(0))
                .strafeTo(new Vector2d(55, 56))
                .strafeTo(new Vector2d(55, 10))
                .setTangent(0)
                .splineTo(new Vector2d(62, 10), Math.toRadians(0))
                .strafeTo(new Vector2d(62, 56))
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(0, 38, Math.toRadians(270)), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-52, 56, Math.toRadians(180)), Math.toRadians(180));
        Action trajectory3 = traj3.build();
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
        new SequentialAction(
                new ParallelAction(
                        drive.LiftLoop(),
                        drive.BucketLoopAction(),
                        tab1Action
                        //lift.liftUp()
                )

//                trajectory2,
////                drive.SetLiftTarget(0),
//                        new ParallelAction(
//                                drive.LiftLoop(),
//                                trajectory3
//
//                )
        )



        );
        // bucket.bucket.setPosition(1);
//        Actions.runBlocking(
//                new SleepAction(2)
//        );
//        bucket.bucket.setPosition(0.5);
//        Actions.runBlocking(
//                new SequentialAction(
//                        trajectory2,
//                        drive.SetLiftTarget(0),
//                        new ParallelAction(
//                            drive.LiftLoop(),
//                            trajectory3
//                        )
//                )
//        );
    }
    
}
