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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32.75, 63.25, Math.toRadians(0)))
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
                .turn(Math.toRadians(-28))
                                .waitSeconds(2)

                //.afterTime(0, drive.SetIntakeLiftTarget(-1300))
                //.afterTime(0, drive.SetIntakeDropdownTarget(-1406))
                //.afterTime(0, )
                //.afterTime(0, drive.SetLiftTarget(-4385))
                .turn(Math.toRadians(28))
                .strafeTo(new Vector2d(59.5, 63))
                //.afterTime(1, drive.SetBucketPos(1))
                //.afterTime(1.5, drive.SetBucketPos(0.5))
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(51.5, 51))
                //.afterTime(0.5, drive.SetLiftTarget(0))
                        .waitSeconds(0.5)
                .turn(Math.toRadians(-11))
                //.afterTime(0, drive.SetIntakeLiftTarget(-1300))
                //.afterTime(0, drive.SetIntakeDropdownTarget(-1406))
                //.afterTime(0, )
                        //.afterTime(0, drive.SetLiftTarget(-4385))
                        .waitSeconds(2)
                .turn(Math.toRadians(11))
                .strafeTo(new Vector2d(59.5, 63))
                //.afterTime(1, drive.SetBucketPos(1))
                //.afterTime(1.5, drive.SetBucketPos(0.5))
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(51.5, 51))
                //.afterTime(0.5, drive.SetLiftTarget(0));
                        .waitSeconds(0.5)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}