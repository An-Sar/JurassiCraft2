package org.jurassicraft.client.model.animation.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.model.DinosaurModel;
import org.jurassicraft.client.model.animation.DinosaurAnimator;
import org.jurassicraft.server.entity.dinosaur.disabled.LambeosaurusEntity;

@SideOnly(Side.CLIENT)
public class LambeosaurusAnimator extends DinosaurAnimator<LambeosaurusEntity>
{
    @Override
    protected void performMowzieLandAnimations(DinosaurModel model, LambeosaurusEntity entity, float f, float f1, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
        AdvancedModelRenderer head = model.getCube("Head");

        AdvancedModelRenderer neck1 = model.getCube("Neck");
        AdvancedModelRenderer neck2 = model.getCube("Neck 2");

        // body parts
        AdvancedModelRenderer stomach = model.getCube("Body 1");
        AdvancedModelRenderer shoulders = model.getCube("Body 2");
        AdvancedModelRenderer waist = model.getCube("Body 3");

        // tail parts
        AdvancedModelRenderer tail1 = model.getCube("Tail 1");
        AdvancedModelRenderer tail2 = model.getCube("Tail 2");
        AdvancedModelRenderer tail3 = model.getCube("Tail 3");
        AdvancedModelRenderer tail4 = model.getCube("Tail 4");
        AdvancedModelRenderer tail5 = model.getCube("Tail 5");
        AdvancedModelRenderer tail6 = model.getCube("Tail 6");

        // left foot
        AdvancedModelRenderer leftThigh = model.getCube("Left Thigh");
        AdvancedModelRenderer leftCalf = model.getCube("Left Calf 1");
        AdvancedModelRenderer leftUpperFoot = model.getCube("Left Upper Foot");
        AdvancedModelRenderer leftFoot = model.getCube("Foot Left");

        // right foot
        AdvancedModelRenderer rightThigh = model.getCube("Right Thigh");
        AdvancedModelRenderer rightCalf = model.getCube("Right Calf 1");
        AdvancedModelRenderer rightUpperFoot = model.getCube("Right Upper Foot");
        AdvancedModelRenderer rightFoot = model.getCube("Foot Right");

        // right arm
        AdvancedModelRenderer upperArmRight = model.getCube("Upper Arm Right");
        AdvancedModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        AdvancedModelRenderer rightHand = model.getCube("Right Hand");
        AdvancedModelRenderer rightFingers = model.getCube("Right Fingers");

        // left arm
        AdvancedModelRenderer upperArmLeft = model.getCube("Upper Arm Left");
        AdvancedModelRenderer lowerArmLeft = model.getCube("Lower Arm Left");
        AdvancedModelRenderer leftHand = model.getCube("Left Hand");
        AdvancedModelRenderer leftFingers = model.getCube("Left Fingers");

        AdvancedModelRenderer jaw = model.getCube("Jaw");

        AdvancedModelRenderer[] tail = new AdvancedModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        float scaleFactor = 0.6F;
        float height = 2F;
        float allFoursLean = (float) (Math.pow(f1, 1 / (f1 * 10)) / 4);

        if (allFoursLean > 0.15F)
        {
            allFoursLean = 0.15F;
        }

        // All fours behavior
        waist.rotateAngleX += allFoursLean * 1.5;
        shoulders.rotateAngleX -= allFoursLean / 5;
        neck1.rotateAngleX -= 2 * allFoursLean / 5 * 1.5;
        head.rotateAngleX -= 2 * allFoursLean / 5 * 1.5;
        tail1.rotateAngleX -= 2 * allFoursLean / 7;
        tail2.rotateAngleX -= 1 * allFoursLean / 7;
        tail3.rotateAngleX -= 1 * allFoursLean / 7;
        tail4.rotateAngleX -= 1 * allFoursLean / 7;
        tail5.rotateAngleX -= 1 * allFoursLean / 7;
        tail6.rotateAngleX -= 1 * allFoursLean / 7;
        upperArmLeft.rotateAngleX -= allFoursLean * 4;
        upperArmRight.rotateAngleX -= allFoursLean * 4;
        lowerArmLeft.rotateAngleX += allFoursLean * 4;
        lowerArmRight.rotateAngleX += allFoursLean * 4;
        leftHand.rotateAngleX -= allFoursLean * 12;
        rightHand.rotateAngleX -= allFoursLean * 12;

        model.bob(waist, 1F * scaleFactor, 1F * height, false, f, f1);
        model.bob(leftThigh, 1F * scaleFactor, 1F * height, false, f, f1);
        model.bob(rightThigh, 1F * scaleFactor, 1F * height, false, f, f1);

        model.walk(neck1, 1F * scaleFactor, 0.15F * height, false, 1F, 0F, f, f1);
        model.walk(head, 1F * scaleFactor, 0.15F * height, true, 1F, 0F, f, f1);

        model.walk(leftThigh, 0.5F * scaleFactor, 0.5F, false, 0F, 0.3F, f, f1);
        model.walk(leftCalf, 0.5F * scaleFactor, 0.5F, true, 2F, 0F, f, f1);
        model.walk(leftUpperFoot, 0.5F * scaleFactor, 0.7F, false, 0F, -0.4F, f, f1);
        model.walk(leftFoot, 0.5F * scaleFactor, 1F, true, 0.5F, 1F, f, f1);

        model.walk(rightThigh, 0.5F * scaleFactor, 0.5F, true, 0F, 0.3F, f, f1);
        model.walk(rightCalf, 0.5F * scaleFactor, 0.5F, false, 2F, 0F, f, f1);
        model.walk(rightUpperFoot, 0.5F * scaleFactor, 0.7F, true, 0F, -0.4F, f, f1);
        model.walk(rightFoot, 0.5F * scaleFactor, 1F, false, 0.5F, 1F, f, f1);

        float frontOffset = 1.3F;
        model.walk(upperArmLeft, 0.5F * scaleFactor, 1F, false, -0.5F - frontOffset, 0F, f, f1);
        model.walk(lowerArmLeft, 0.5F * scaleFactor, 1F, true, -1F - frontOffset, 0F, f, f1);
        model.walk(leftHand, 0.5F * scaleFactor, 0.5F, false, -1F - frontOffset, 0F, f, f1);

        model.walk(upperArmRight, 0.5F * scaleFactor, 1F, true, -0.5F - frontOffset, 0F, f, f1);
        model.walk(lowerArmRight, 0.5F * scaleFactor, 1F, false, -1F - frontOffset, 0F, f, f1);
        model.walk(rightHand, 0.5F * scaleFactor, 0.5F, true, -1F - frontOffset, 0F, f, f1);

        model.chainWave(tail, 1F * scaleFactor, -0.1F, 2, f, f1);
        model.chainSwing(tail, 0.5F * scaleFactor, 0.1F, 2, f, f1);

        model.walk(neck1, 0.1F, 0.07F, false, -1F, 0F, ticks, 0.25F);
        model.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticks, 0.25F);
        model.walk(waist, 0.1F, 0.04F, false, 0F, 0F, ticks, 0.25F);
        model.walk(upperArmRight, 0.1F, 0.1F, false, -1F, 0F, ticks, 0.25F);
        model.walk(upperArmLeft, 0.1F, 0.1F, false, -1F, 0F, ticks, 0.25F);
        model.walk(lowerArmRight, 0.1F, 0.1F, true, -1.5F, 0F, ticks, 0.25F);
        model.walk(lowerArmLeft, 0.1F, 0.1F, true, -1.5F, 0F, ticks, 0.25F);
        model.walk(rightHand, 0.1F, 0.1F, false, -2F, 0F, ticks, 0.25F);
        model.walk(leftHand, 0.1F, 0.1F, false, -2F, 0F, ticks, 0.25F);

        model.chainWave(tail, 0.1F, -0.02F, 2, ticks, 1F);

        entity.tailBuffer.applyChainSwingBuffer(tail);
    }
}
