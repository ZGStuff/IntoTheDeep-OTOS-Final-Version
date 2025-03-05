package dev.rrbrott.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 65, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, 61, Math.toRadians(90)))
                .setTangent(Math.toRadians(90))
//                .afterTime(0, drive.SetLiftTarget(-1968))
//                .afterTime(0, claw.closeClaw())
                .lineToY(30)
//                .afterTime(0.5, drive.SetLiftTarget(-1328))
//                .afterTime(1, claw.openClaw())
//                .afterTime(2, drive.SetLiftTarget(0))
                .waitSeconds(1.25)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-28, 40, Math.toRadians(270)), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-45, 15), Math.toRadians(180))
                //.setTangent(Math.toRadians(270))
                //.lineToX(-42)
                //.splineToLinearHeading(new Pose2d(-42.1, 23, Math.toRadians(180)), Math.toRadians(180))
                        .setTangent(Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(-45.1, 61), Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(-45, 15), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-55, 16), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-55.1, 61), Math.toRadians(90))
                                .setTangent(Math.toRadians(270))
                // robot has collected blocks and is @ wall
                        //.splineToConstantHeading(new Vector2d(-36, 45), Math.toRadians(90))
                                .lineToY(30)
//                .splineToLinearHeading(new Pose2d(-52, 23, Math.toRadians(180)), Math.toRadians(180))
//                                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-55, 61, Math.toRadians(180)), Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-34, 56, Math.toRadians(180)), Math.toRadians(0))
//                                .splineToLinearHeading(new Pose2d(-45, 61, Math.toRadians(270)), Math.toRadians(90))
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
//                .splineToLinearHeading(new Pose2d(0, 29.5, Math.toRadians(90)), Math.toRadians(270))

                                //.splineToConstantHeading(new Vector2d(-38, 61), Math.toRadians(90))
                        .lineToY(61)
                .waitSeconds(1.25)
                        .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(5, 29.5, Math.toRadians(90)), Math.toRadians(270))
                        .waitSeconds(1.25)
                        .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-38, 61, Math.toRadians(270)), Math.toRadians(90))

                        .waitSeconds(1.25)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(2, 29.5, Math.toRadians(90)), Math.toRadians(270))
                                .waitSeconds(1.25)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-38, 61, Math.toRadians(270)), Math.toRadians(90))
                .waitSeconds(1.25)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-1, 29.5, Math.toRadians(90)), Math.toRadians(270))
                .waitSeconds(1.25)
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