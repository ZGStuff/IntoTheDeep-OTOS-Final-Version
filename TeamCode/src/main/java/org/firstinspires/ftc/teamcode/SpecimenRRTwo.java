package org.firstinspires.ftc.teamcode;


// RR-specific imports

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "AutoRR-TripleSpecimenB-NorthMap-Blue", group = "Autonomous")


public class SpecimenRRTwo extends LinearOpMode {
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
                specimenEater.setPosition(0);
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
        Pose2d initialPose = new Pose2d(-21, 61, Math.toRadians(90));
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
//                .setTangent(Math.toRadians(0))
//                .afterTime(0, drive.SetLiftTarget(-1968))
//                .afterTime(0, claw.closeClaw())
//                .splineToLinearHeading(new Pose2d(3.9, 30, Math.toRadians(90)), Math.toRadians(270))
//                .afterTime(0.5, drive.SetLiftTarget(-1328))
//                .afterTime(1, claw.openClaw())
//                .afterTime(2, drive.SetLiftTarget(0))
//                .waitSeconds(2.5)
//                .setTangent(Math.toRadians(180))
//                .splineTo(new Vector2d(-18, 40), Math.toRadians(180))
//                .splineTo(new Vector2d(-32, 30), Math.toRadians(270))
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(new Pose2d(-42.1, 23, Math.toRadians(180)), Math.toRadians(180))
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-55, 61, Math.toRadians(180)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-34, 56, Math.toRadians(180)), Math.toRadians(0))
//                .setTangent(Math.toRadians(270))
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-45, 61, Math.toRadians(270)), Math.toRadians(90))
//                .afterTime(0.5, claw.closeClaw())
//                .afterTime(1, drive.SetLiftTarget(-1968))
//                .waitSeconds(2)
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(0, 29.5, Math.toRadians(90)), Math.toRadians(270))
//                .waitSeconds(2)
//                .splineToSplineHeading(new Pose2d(-45, 61, Math.toRadians(270)), Math.toRadians(180))
//                .waitSeconds(2)
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(-4, 29.5, Math.toRadians(90)), Math.toRadians(270))
//
//                .afterTime(0.5, drive.SetLiftTarget(-1328))
//                .afterTime(1, claw.openClaw())
//                .afterTime(2, drive.SetLiftTarget(0));
                // raises arm, moves to bar and closes claw
                .setTangent(Math.toRadians(0))
                .afterTime(0, drive.SetLiftTarget(-1968))
                .afterTime(0, claw.closeClaw())
                .splineToLinearHeading(new Pose2d(5, 32, Math.toRadians(90)), Math.toRadians(270))
                // hangs specimen and opens claw
                .afterTime(0.5, drive.SetLiftTarget(-1328))
                .afterTime(0.75, claw.openClaw())
                .waitSeconds(1.25)
                .afterTime(0, drive.SetLiftTarget(0))
                .setTangent(Math.toRadians(180))
                // splines to samples and pushes one
                .splineTo(new Vector2d(-18, 40), Math.toRadians(180))
                .splineTo(new Vector2d(-32, 30), Math.toRadians(270))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-42.1, 23, Math.toRadians(180)), Math.toRadians(180))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-55, 54, Math.toRadians(180)), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-30, 52, Math.toRadians(180)), Math.toRadians(0))
                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-52, 23, Math.toRadians(180)), Math.toRadians(180))
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-55, 59, Math.toRadians(180)), Math.toRadians(90))
                // pushes sample number 2 into position
//                .splineToLinearHeading(new Pose2d(-34, 56, Math.toRadians(180)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-45, 58.5, Math.toRadians(270)), Math.toRadians(90))
                .waitSeconds(0.5)
                .afterTime(0.25, claw.closeClaw())
                .afterTime(0.75, drive.SetLiftTarget(-1968))
                .waitSeconds(1.25)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(2, 32, Math.toRadians(90)), Math.toRadians(270))
                .afterTime(0.5, drive.SetLiftTarget(-1328))
                .afterTime(1, claw.openClaw())
                .waitSeconds(1.75)
                .afterTime(0, drive.SetLiftTarget(0))
                .splineToSplineHeading(new Pose2d(-45, 59.5, Math.toRadians(270)), Math.toRadians(180))
                .waitSeconds(0.5)
                .afterTime(0.25, claw.closeClaw())
                .afterTime(0.75, drive.SetLiftTarget(-1968))
                .waitSeconds(1.25)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(-2, 32, Math.toRadians(90)), Math.toRadians(270))
                .afterTime(0.5, drive.SetLiftTarget(-1328))
                .afterTime(1, claw.openClaw())
                .waitSeconds(1.75)
                .afterTime(0, drive.SetLiftTarget(0))
                .splineToSplineHeading(new Pose2d(-45, 59, Math.toRadians(270)), Math.toRadians(180));



        Action tab1Action = tab1.build();
        TrajectoryActionBuilder traj2 = tab1.endTrajectory().fresh()
                .strafeTo(new Vector2d(51.5, 51));
        Action trajectory2 = traj2.build();
        TrajectoryActionBuilder traj3 = traj2.endTrajectory().fresh();
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
                        tab1Action
                        //lift.liftUp()
                )
        )



        );
    }
    
}
