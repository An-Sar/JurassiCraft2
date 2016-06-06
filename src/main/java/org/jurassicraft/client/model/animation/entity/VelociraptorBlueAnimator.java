package org.jurassicraft.client.model.animation.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import org.jurassicraft.client.model.DinosaurModel;
import org.jurassicraft.client.model.animation.DinosaurAnimator;
import org.jurassicraft.server.entity.dinosaur.VelociraptorBlueEntity;

public class VelociraptorBlueAnimator extends DinosaurAnimator<VelociraptorBlueEntity>
{
    @Override
    protected void performMowzieLandAnimations(DinosaurModel parModel, VelociraptorBlueEntity entity, float f, float f1, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
        AdvancedModelRenderer waist = parModel.getCube("body3");
        AdvancedModelRenderer chest = parModel.getCube("body2");
        AdvancedModelRenderer shoulders = parModel.getCube("body1");
        AdvancedModelRenderer leftThigh = parModel.getCube("Left thigh");
        AdvancedModelRenderer rightThigh = parModel.getCube("Right thigh");
        AdvancedModelRenderer neck1 = parModel.getCube("neck1");
        AdvancedModelRenderer neck2 = parModel.getCube("neck2");
        AdvancedModelRenderer neck3 = parModel.getCube("neck3");
        AdvancedModelRenderer neck4 = parModel.getCube("neck4");
        AdvancedModelRenderer head = parModel.getCube("Head");
        AdvancedModelRenderer jaw = parModel.getCube("down_jaw");
        AdvancedModelRenderer leftShin = parModel.getCube("Left shin");
        AdvancedModelRenderer rightShin = parModel.getCube("Right shin");
        AdvancedModelRenderer leftUpperFoot = parModel.getCube("Left upper foot");
        AdvancedModelRenderer leftFoot = parModel.getCube("Left foot");
        AdvancedModelRenderer rightUpperFoot = parModel.getCube("Right upper foot");
        AdvancedModelRenderer rightFoot = parModel.getCube("Right foot");
        AdvancedModelRenderer upperArmRight = parModel.getCube("Right arm");
        AdvancedModelRenderer upperArmLeft = parModel.getCube("Left arm");
        AdvancedModelRenderer tail1 = parModel.getCube("tail1");
        AdvancedModelRenderer tail2 = parModel.getCube("tail2");
        AdvancedModelRenderer tail3 = parModel.getCube("tail3");
        AdvancedModelRenderer tail4 = parModel.getCube("tail4");
        AdvancedModelRenderer tail5 = parModel.getCube("tail5");
        AdvancedModelRenderer tail6 = parModel.getCube("tail6");
        AdvancedModelRenderer rightToe = parModel.getCube("Right toe");
        AdvancedModelRenderer leftToe = parModel.getCube("Left toe");

        AdvancedModelRenderer lowerArmRight = parModel.getCube("Right forearm");
        AdvancedModelRenderer lowerArmLeft = parModel.getCube("Left forearm");
        AdvancedModelRenderer Hand_Right = parModel.getCube("Right hand");
        AdvancedModelRenderer Hand_Left = parModel.getCube("Left hand");

        AdvancedModelRenderer[] rightArmParts = new AdvancedModelRenderer[] { Hand_Right, lowerArmRight, upperArmRight };
        AdvancedModelRenderer[] leftArmParts = new AdvancedModelRenderer[] { Hand_Left, lowerArmLeft, upperArmLeft };
        AdvancedModelRenderer[] tailParts = new AdvancedModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };
        AdvancedModelRenderer[] bodyParts = new AdvancedModelRenderer[] { waist, chest, shoulders, neck4, neck3, neck2, neck1, head };

        // if (velociraptor.isCarcass()) //Death Animation
        // {
        // model.walk(head, 1.0F, 0.5F, false, 0, 0, velociraptor.hurtTime, 1.0F);
        // }
        // else
        // {

        // f = ticks;
        // f1 = 1F;
        // f1 = (float) (Math.sin(velociraptor.ticks * 0.01) *
        // Math.sin(velociraptor.ticks * 0.01));

        // if (raptor.leaping)
        // limbSwingAmount = 0;
        // if (raptor.getAnimationId() == JurassiCraftAnimationIDs.LEAP.animID()
        // && raptor.getAnimationTick() >= 6)
        // limbSwingAmount = 0;
//        float speed = 0.75F;
//        float height = 2F * f1;
//
//        float dontLeanProgress = entity.dontLean.getAnimationProgressSinSqrt();
//
//        parModel.bob(waist, 1F * speed, height, false, f, f1);
//        parModel.bob(leftThigh, 1F * speed, height, false, f, f1);
//        parModel.bob(rightThigh, 1F * speed, height, false, f, f1);
//        parModel.walk(shoulders, 1F * speed, 0.2F, true, 1, 0, f, f1);
//        parModel.walk(chest, 1F * speed, 0.2F, false, 0.5F, 0, f, f1);
//
//        parModel.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, f, f1);
//        parModel.walk(leftShin, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, f, f1);
//        parModel.walk(leftUpperFoot, 0.5F * speed, 0.8F, false, -1F, -0.1F, f, f1);
//        parModel.walk(leftFoot, 0.5F * speed, 1.5F, true, -1F, 1F, f, f1);
//
//        parModel.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, f, f1);
//        parModel.walk(rightShin, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, f, f1);
//        parModel.walk(rightUpperFoot, 0.5F * speed, 0.8F, true, -1F, -0.1F, f, f1);
//        parModel.walk(rightFoot, 0.5F * speed, 1.5F, false, -1F, 1F, f, f1);
//
//        shoulders.rotationPointY -= 0.5 * f1 * dontLeanProgress;
//        shoulders.rotationPointZ -= 0.5 * f1 * dontLeanProgress;
//        shoulders.rotateAngleX += 0.6 * f1 * dontLeanProgress;
//        chest.rotateAngleX += 0.1 * f1 * dontLeanProgress;
//        neck1.rotateAngleX += 0.1 * f1 * dontLeanProgress;
//        neck2.rotateAngleX += 0.1 * f1 * dontLeanProgress;
//        neck3.rotateAngleX -= 0.2 * f1 * dontLeanProgress;
//        neck4.rotateAngleX -= 0.2 * f1 * dontLeanProgress;
//        head.rotateAngleX -= 0.3 * f1 * dontLeanProgress;
//
//        parModel.chainSwing(tailParts, 0.5F * speed, -0.1F, 2, f, f1);
//        parModel.chainWave(tailParts, 1F * speed, -0.1F, 2.5F, f, f1);
//        parModel.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);
//
//        parModel.chainWave(rightArmParts, 1F * speed, -0.3F, 4, f, f1);
//        parModel.chainWave(leftArmParts, 1F * speed, -0.3F, 4, f, f1);

        // Idling
        parModel.chainWave(tailParts, 0.1F, 0.05F, 2, ticks, 0.25F);
        parModel.chainWave(bodyParts, 0.1F, -0.03F, 5, ticks, 0.25F);
        parModel.chainWave(rightArmParts, 0.1F, -0.1F, 4, ticks, 0.25F);
        parModel.chainWave(leftArmParts, 0.1F, -0.1F, 4, ticks, 0.25F);

        // float sittingProgress =
        // raptor.sittingProgress.getAnimationProgressSin();
        //
        // if (sittingProgress > 0.001F)
        // {
        // //Sitting Pose
        // float sittingProgressTemporary =
        // raptor.sittingProgress.getAnimationProgressTemporaryFS();
        //
        // model.faceTarget(Head, 5, rotationYaw, rotationPitch);
        // model.faceTarget(Neck, 4, rotationYaw, rotationPitch);
        //
        // Body_1.rotationPointY += 13.25F * sittingProgress;
        // Right_Thigh.rotationPointY += 14.25F * sittingProgress;
        // Left_Thigh.rotationPointY += 14.25F * sittingProgress;
        // Right_Thigh.rotationPointZ += 0.5F * sittingProgress;
        // Left_Thigh.rotationPointZ += 0.5F * sittingProgress;
        //
        // if (sittingProgressTemporary > 0.001F)
        // {
        // Body_1.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Neck.rotateAngleX += 0.4F * sittingProgressTemporary;
        // Head.rotateAngleX += 0.2F * sittingProgressTemporary;
        // Upper_Arm_Right.rotateAngleX += 0.5F * sittingProgressTemporary;
        // Upper_Arm_Left.rotateAngleX += 0.5F * sittingProgressTemporary;
        //
        // if (raptor.isSitting())
        // {
        // Tail_1.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_2.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_3.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_4.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_5.rotateAngleX += 0.1F * sittingProgressTemporary;
        // }
        // else
        // {
        // Tail_1.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_2.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_3.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_4.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_5.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // }
        // }
        //
        // Body_1.rotateAngleX -= 0.075F * sittingProgress;
        //
        // Upper_Arm_Right.rotateAngleX -= 0.8F * sittingProgress;
        // Upper_Arm_Left.rotateAngleX -= 0.8F * sittingProgress;
        //
        // Right_Thigh.rotateAngleX -= 0.75F * sittingProgress;
        // Left_Thigh.rotateAngleX -= 0.75F * sittingProgress;
        //
        // Right_Calf_1.rotationPointZ += 0.5F * sittingProgress;
        // Left_Calf_1.rotationPointZ += 0.5F * sittingProgress;
        // Right_Calf_1.rotationPointY += 1.5F * sittingProgress;
        // Left_Calf_1.rotationPointY += 1.5F * sittingProgress;
        // Right_Calf_1.rotateAngleX += 1.2F * sittingProgress;
        // Left_Calf_1.rotateAngleX += 1.2F * sittingProgress;
        //
        // Right_Upper_Foot.rotationPointZ -= 0.6F * sittingProgress;
        // Left_Upper_Foot.rotationPointZ -= 0.6F * sittingProgress;
        // Right_Upper_Foot.rotateAngleX -= 1.45F * sittingProgress;
        // Left_Upper_Foot.rotateAngleX -= 1.45F * sittingProgress;
        //
        // Foot_Right.rotationPointZ -= 0.5F * sittingProgress;
        // Foot_Left.rotationPointZ -= 0.5F * sittingProgress;
        // Foot_Right.rotateAngleX += 1.0F * sittingProgress;
        // Foot_Left.rotateAngleX += 1.0F * sittingProgress;
        //
        // Right_Claw_1.rotateAngleX += 0.7F * sittingProgress;
        // Left_Claw_1.rotateAngleX += 0.7F * sittingProgress;
        //
        // //Idling
        // model.chainWave(tailParts, 0.1F, -0.05F, 2, ticks, 1.0F
        // - 0.5F * sittingProgress);
        // model.walk(Neck, 0.1F, 0.07F, false, -1F, 0F, ticks, 1F
        // - 0.3F * sittingProgress);
        // model.walk(Head, 0.1F, 0.07F, true, 0F, 0F, ticks, 1F -
        // 0.3F * sittingProgress);
        // model.walk(Body_1, 0.1F, 0.05F, false, 0F, 0F, ticks,
        // 1.0F - 0.7F * sittingProgress);
        // model.chainWave(rightArmParts, 0.1F, -0.1F, 4, ticks,
        // 1.0F - 0.5F * sittingProgress);
        // model.chainWave(leftArmParts, 0.1F, -0.1F, 4, ticks,
        // 1.0F - 0.5F * sittingProgress);
        // }
        // else
        // {

        // parModel.faceTarget(head, 2, rotationYaw, rotationPitch);
        // parModel.faceTarget(neck1, 2, rotationYaw, rotationPitch);
        //
        // velociraptor.tailBuffer.applyChainSwingBuffer(tailParts);
        //
        // // Call
        // animator.setAnim(Animations.ATTACKING);
        // animator.startPhase(2);
        // animator.rotate(shoulders, -0.3f, 0, 0);
        // animator.move(shoulders, 0, 0.5f, 0.2f);
        // animator.rotate(neck1, -0.2f, 0, 0);
        // animator.move(neck1, 0, 0.5f, -0.2f);
        // animator.rotate(neck2, -0.2f, 0, 0);
        // animator.move(neck2, 0, 0, -0.3f);
        // animator.rotate(neck3, 0.1f, 0, 0);
        // animator.move(neck3, 0, 0, -0.2f);
        // animator.rotate(neck4, 0.2f, 0, 0);
        // animator.move(neck4, 0, 0, -0.2f);
        // animator.rotate(head, 0.4f, 0, 0);
        // animator.move(head, 0, 0, -0.2f);
        // animator.endPhase();
        // animator.startPhase(5);
        // animator.rotate(shoulders, 0.4f, 0, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.3f, 0, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.2f, 0, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.1f, 0, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.1f, 0, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.7f, 0, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.rotate(jaw, 0.4f, 0, 0);
        // animator.endPhase();
        // animator.setStationaryPhase(10);
        // animator.resetPhase(8);
        //
        // // Twitch right
        // animator.setAnim(Animations.MATING);
        // animator.startPhase(3);
        // animator.rotate(head, 0, 0, 0.3f);
        // animator.move(head, 1, 0, 0);
        // animator.endPhase();
        // animator.setStationaryPhase(19);
        // animator.resetPhase(3);
        //
        // // Twitch left
        // animator.setAnim(Animations.SLEEPING);
        // animator.startPhase(3);
        // animator.rotate(head, 0, 0, -0.3f);
        // animator.move(head, -1, 0, 0);
        // animator.endPhase();
        // animator.setStationaryPhase(19);
        // animator.resetPhase(3);
        //
        // // Look and sniff
        // animator.setAnim(Animations.RESTING);
        // animator.startPhase(8);
        // animator.rotate(shoulders, 0.5f, 0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, 0.2f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, 0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, 0.3f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.3f, 0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.6f, 0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        //
        // animator.startPhase(1);
        // animator.rotate(shoulders, 0.5f, 0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, 0.2f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, 0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, 0.3f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.35f, 0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.65f, 0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        // animator.setStationaryPhase(1);
        // animator.startPhase(1);
        // animator.rotate(shoulders, 0.5f, 0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, 0.2f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, 0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, 0.3f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.3f, 0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.6f, 0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        // animator.setStationaryPhase(1);
        // animator.startPhase(1);
        // animator.rotate(shoulders, 0.5f, 0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, 0.2f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, 0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, 0.3f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.35f, 0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.65f, 0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        // animator.setStationaryPhase(1);
        // animator.startPhase(2);
        // animator.rotate(shoulders, 0.5f, 0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, 0.2f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, 0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, 0.3f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.3f, 0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.6f, 0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        //
        // animator.setStationaryPhase(3);
        //
        // animator.startPhase(8);
        // animator.rotate(shoulders, 0.5f, -0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, -0.1f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, -0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, -0.2f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.3f, -0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.6f, -0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        //
        // animator.startPhase(2);
        // animator.rotate(shoulders, 0.5f, -0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, -0.1f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, -0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, -0.2f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.35f, -0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.65f, -0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        // animator.setStationaryPhase(1);
        // animator.startPhase(2);
        // animator.rotate(shoulders, 0.5f, -0.1f, 0);
        // animator.move(shoulders, 0, -1, -0.5f);
        // animator.rotate(neck1, 0.4f, -0.1f, 0);
        // animator.move(neck1, 0, -1, 0.5f);
        // animator.rotate(neck2, 0.3f, -0.2f, 0);
        // animator.move(neck2, 0, 0, 0.5f);
        // animator.rotate(neck3, -0.2f, -0.2f, 0);
        // animator.move(neck3, 0, 0, 0.5f);
        // animator.rotate(neck4, -0.3f, -0.3f, 0);
        // animator.move(neck4, 0, 0, 0.5f);
        // animator.rotate(head, -0.6f, -0.3f, 0);
        // animator.move(head, 0, 0, 0.5f);
        // animator.endPhase();
        //
        // animator.setStationaryPhase(5);
        //
        // animator.resetPhase(8);
    }
}
