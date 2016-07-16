package org.jurassicraft.client.model.animation.entity;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.model.DinosaurModel;
import org.jurassicraft.client.model.animation.DinosaurAnimator;
import org.jurassicraft.server.entity.dinosaur.disabled.AnkylosaurusEntity;

@SideOnly(Side.CLIENT)
public class AnkylosaurusAnimator extends DinosaurAnimator<AnkylosaurusEntity>
{
    @Override
    protected void performAnimations(DinosaurModel model, AnkylosaurusEntity entity, float f, float f1, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
        AdvancedModelRenderer head = model.getCube("head ");
        AdvancedModelRenderer headback = model.getCube("head back");

        AdvancedModelRenderer neck1 = model.getCube("neck 1");

        AdvancedModelRenderer waist = model.getCube("Body");
        AdvancedModelRenderer chest = model.getCube("body 2");

        AdvancedModelRenderer tail1 = model.getCube("tail 1");
        AdvancedModelRenderer tail2 = model.getCube("tail 2");
        AdvancedModelRenderer tail3 = model.getCube("tail 3");
        AdvancedModelRenderer tail4 = model.getCube("tail 4");
        AdvancedModelRenderer tail5 = model.getCube("tail end");

        AdvancedModelRenderer[] tail = new AdvancedModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };

        AdvancedModelRenderer legleftthigh = model.getCube("leg left 1");
        AdvancedModelRenderer legleftcalf = model.getCube("leg left 2");
        AdvancedModelRenderer legleftfoot = model.getCube("leg left back 3");

        AdvancedModelRenderer legrightthigh = model.getCube("leg right 1");
        AdvancedModelRenderer legrightcalf = model.getCube("leg right 2");
        AdvancedModelRenderer legrightfoot = model.getCube("leg right back 3");

        AdvancedModelRenderer armleftthigh = model.getCube("arm left 2");
        AdvancedModelRenderer armleftcalf = model.getCube("arm left 1");
        AdvancedModelRenderer armleftfoot = model.getCube("leg left front 3");

        AdvancedModelRenderer armrightthigh = model.getCube("arm right 2");
        AdvancedModelRenderer armrightcalf = model.getCube("arm right 1");
        AdvancedModelRenderer armrightfoot = model.getCube("leg right front 3");

        float globalSpeed = 0.3F;
        float globalDegree = 0.8F;
        float height = 1.2F;
        float frontOffset = 0.84F;

        model.bob(waist, 2 * globalSpeed, height, false, f, f1);
        // bob(legleftthigh, 2 * globalSpeed, height, false, f, f1);
        // bob(legrightthigh, 2 * globalSpeed, height, false, f, f1);
        model.walk(waist, 2 * globalSpeed, 0.1F * height, true, -1.5F, 0.05F, f, f1);
        model.walk(chest, 2 * globalSpeed, 0.07F * height, true, -2F, 0.05F, f, f1);
        // model.walk(tail1, 2 * globalSpeed, 0.1F * height, false, -1.5F, 0.05F, f, f1);
        model.walk(neck1, 2 * globalSpeed, 0.03F * height, false, 0.5F, -0.1F, f, f1);
        model.walk(headback, 2 * globalSpeed, 0.03F * height, true, 1F, 0F, f, f1);
        model.walk(head, 2 * globalSpeed, 0.15F * height, true, 1.5F, 0F, f, f1);

        model.walk(legleftthigh, 1F * globalSpeed, 0.7F * globalDegree, false, 0F, -0.4F, f, f1);
        model.walk(legleftcalf, 1F * globalSpeed, 0.6F * globalDegree, true, 1F, 0.5F, f, f1);
        model.walk(legleftfoot, 1F * globalSpeed, 0.6F * globalDegree, false, -1.5F, 0.85F, f, f1);

        model.walk(legrightthigh, 1F * globalSpeed, 0.7F * globalDegree, true, 0F, -0.4F, f, f1);
        model.walk(legrightcalf, 1F * globalSpeed, 0.6F * globalDegree, false, 1F, 0.5F, f, f1);
        model.walk(legrightfoot, 1F * globalSpeed, 0.6F * globalDegree, true, -1.5F, 0.85F, f, f1);

        model.walk(armleftthigh, 1F * globalSpeed, 0.7F * globalDegree, true, frontOffset + 0F, -0.2F, f, f1);
        model.walk(armleftcalf, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 1F, -0.2F, f, f1);
        model.walk(armleftfoot, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 2F, 0.8F, f, f1);

        model.walk(armrightthigh, 1F * globalSpeed, 0.7F * globalDegree, false, frontOffset + 0F, -0.2F, f, f1);
        model.walk(armrightcalf, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 1F, -0.2F, f, f1);
        model.walk(armrightfoot, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 2F, 0.8F, f, f1);

        model.chainWave(tail, 2 * globalSpeed, -0.12F, 2, f, f1);
        model.chainSwing(tail, 1 * globalSpeed, 0.3F, 3, f, f1);

        model.walk(neck1, 0.1F, 0.05F, false, -1F, 0F, ticks, 1F);
        model.walk(headback, 0.1F, 0.05F, true, 0F, 0F, ticks, 1F);
        model.walk(waist, 0.1F, 0.025F, false, 0F, 0F, ticks, 1F);
        model.walk(legleftthigh, 0.1F, 0.025F, true, 0F, 0F, ticks, 1F);
        model.walk(legrightthigh, 0.1F, 0.025F, true, 0F, 0F, ticks, 1F);

        float inverseKinematicsConstant = 0.4F;
        model.walk(armleftthigh, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticks, 0.25F);
        model.walk(armleftcalf, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticks, 0.25F);
        model.walk(armleftfoot, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticks, 0.25F);
        model.walk(armrightthigh, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticks, 0.25F);
        model.walk(armrightcalf, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticks, 0.25F);
        model.walk(armrightfoot, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticks, 0.25F);
        armleftthigh.rotationPointZ -= 0.5 * inverseKinematicsConstant * Math.cos(ticks * 0.1F);
        armrightthigh.rotationPointZ -= 0.5 * inverseKinematicsConstant * Math.cos(ticks * 0.1F);

        model.chainSwing(tail, 0.1F, 0.05F, 2, ticks, 1F);
        model.chainWave(tail, 0.1F, -0.05F, 1, ticks, 1F);

        entity.tailBuffer.applyChainSwingBuffer(tail);
    }
}
