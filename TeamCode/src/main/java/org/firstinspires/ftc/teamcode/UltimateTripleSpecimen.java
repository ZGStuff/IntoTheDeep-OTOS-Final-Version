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
@Autonomous(name = "TheUltimateTripleSpecimen-AutoRR", group = "Autonomous")


public class UltimateTripleSpecimen extends LinearOpMode {
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
                specimenEater.setPosition(1);
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
                .afterTime(0, drive.SetBucketPos(0.5))
                // initial hanging of specimen
                .setTangent(Math.toRadians(0))
                .afterTime(0, drive.SetLiftTarget(-1968))
                .afterTime(0, claw.closeClaw())
                .splineToLinearHeading(new Pose2d(3.9, 31.5, Math.toRadians(90)), Math.toRadians(270))
                .afterTime(0.25, drive.SetLiftTarget(-1328))
                .afterTime(0.5, claw.openClaw())
                .waitSeconds(0.75)

                // obtain sample and move into observation zone
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-32, 36, Math.toRadians(180)), Math.toRadians(180))
                .afterTime(0, drive.SetLiftTarget(0))
                .splineToLinearHeading(new Pose2d(-33, 12, Math.toRadians(180)), Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(-46, 13, Math.toRadians(180)), Math.toRadians(180))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-55, 50, Math.toRadians(180)), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-40, 49.5), Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(-35, 46, Math.toRadians(270)), Math.toRadians(0))
                .waitSeconds(1)
                .setTangent(Math.toRadians(0))
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(-38.1, 55.25), Math.toRadians(90))
                .waitSeconds(1.25)
                .afterTime(0.25, claw.closeClaw())
                .waitSeconds(0.5)
//                .splineToConstantHeading(new Vector2d(-36, 54.5), Math.toRadians(90))
                .afterTime(0, drive.SetLiftTarget(-1968))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(0, 31.5, Math.toRadians(90)), Math.toRadians(270))
                .afterTime(0.25, drive.SetLiftTarget(-1328))
                .afterTime(0.5, claw.openClaw())
                .waitSeconds(0.75)
                .afterTime(0, drive.SetLiftTarget(0))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-35, 46, Math.toRadians(270)), Math.toRadians(90))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-38.1, 55.25), Math.toRadians(90))
                .waitSeconds(1.25)
                .afterTime(0.25, claw.closeClaw())
                .waitSeconds(0.5)
//                .splineToConstantHeading(new Vector2d(-36, 54.5), Math.toRadians(90))
                .afterTime(0, drive.SetLiftTarget(-1968))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-4, 31.5, Math.toRadians(90)), Math.toRadians(270))
                .afterTime(0.25, drive.SetLiftTarget(-1328))
                .afterTime(0.5, claw.openClaw())
                .waitSeconds(0.75)
                .afterTime(0, drive.SetLiftTarget(0));
        Action tab1Action = tab1.build();
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