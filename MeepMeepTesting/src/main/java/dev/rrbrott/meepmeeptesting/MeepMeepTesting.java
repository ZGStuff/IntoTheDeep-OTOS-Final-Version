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
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32.75, 63.25, 0))
                        .splineTo(new Vector2d(52,57), Math.toRadians(-45))
                        .setTangent(0)
                .splineToLinearHeading(new Pose2d(59.5,63, Math.toRadians(-67.5)), Math.toRadians(67.5))
                .strafeTo(new Vector2d(51.5, 51))

//                        .waitSeconds(5)
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
                .splineToSplineHeading(new Pose2d(-52, 56, Math.toRadians(180)), Math.toRadians(180))





                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}