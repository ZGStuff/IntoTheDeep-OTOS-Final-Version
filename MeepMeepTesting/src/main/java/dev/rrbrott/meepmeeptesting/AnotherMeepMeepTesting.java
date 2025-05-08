package dev.rrbrott.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class AnotherMeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32.75, 63.25, Math.toRadians(0)))
                //.setTangent(Math.toRadians(0))
//                .afterTime(0, drive.SetLiftTarget(-1968))
//                .afterTime(0, claw.closeClaw())
                //.splineToLinearHeading(new Pose2d(3.9, 30, Math.toRadians(90)), Math.toRadians(270))
//                .afterTime(0.5, drive.SetLiftTarget(-1328))
//                .afterTime(1, claw.openClaw())
//                .afterTime(2, drive.SetLiftTarget(0))
                //.afterTime(0, drive.SetBucketPos(0.5))
                .splineTo(new Vector2d(52,57), Math.toRadians(-45))
                //.afterTime(0, drive.SetLiftTarget(-4385))
                .waitSeconds(2)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(59.5,63, Math.toRadians(-67.5)), Math.toRadians(67.5))
                //.afterTime(1, drive.SetBucketPos(1))
                //.afterTime(1.5, drive.SetBucketPos(0.5))
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(51.5, 51))
                //.afterTime(0.5, drive.SetLiftTarget(0))
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(36, 24, Math.toRadians(0)), Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(46, 10, Math.toRadians(0)), Math.toRadians(90))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(46, 61))
                .strafeTo(new Vector2d(46, 10))
                .setTangent(0)
                .splineTo(new Vector2d(55, 10), Math.toRadians(0))
                .strafeTo(new Vector2d(55, 60))
                .strafeTo(new Vector2d(55, 10))
                .setTangent(0)
                .splineTo(new Vector2d(65, 10), Math.toRadians(0))
                .strafeTo(new Vector2d(65, 60))
                .setTangent(0)
                .lineToX(-52)
                //.strafeTo(new Vector2d(-42, 63))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-32)
//                .strafeTo(new Vector2d(-32, 24))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-51)
//                .strafeTo(new Vector2d(-51, 63))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-45)
//                .strafeTo(new Vector2d(-45, 59))
//                .turn(Math.toRadians(90))
                //.lineToY(63)
//                .afterTime(0.5, claw.closeClaw())
//                .afterTime(1, drive.SetLiftTarget(-1968))
//                .waitSeconds(2)
//                .setTangent(0)
//                .splineToLinearHeading(new Pose2d(0, 29.5, Math.toRadians(90)), Math.toRadians(270))
//                                .waitSeconds(2)
//                                .splineToSplineHeading(new Pose2d(-45, 61, Math.toRadians(270)), Math.toRadians(180))
//                                .waitSeconds(2)
//                        .setTangent(0)
//                .splineToLinearHeading(new Pose2d(-4, 29.5, Math.toRadians(90)), Math.toRadians(270))
//
//                .afterTime(0.5, drive.SetLiftTarget(-1328))
//                .afterTime(1, claw.openClaw())
//                .afterTime(2, drive.SetLiftTarget(0));

//                                .strafeTo(new Vector2d(-52, 60))
//                .strafeTo(new Vector2d(0, 34))
//                .strafeTo(new Vector2d(-52, 60))
//                .strafeTo(new Vector2d(0, 34))
//                .strafeTo(new Vector2d(-52, 60))
//                .strafeTo(new Vector2d(0, 34))

//                        .waitSeconds(5)





                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}