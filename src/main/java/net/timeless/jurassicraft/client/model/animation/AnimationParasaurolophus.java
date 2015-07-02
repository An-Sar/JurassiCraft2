package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationParasaurolophus implements IModelAnimator
{
    private final Animator animator;

    public AnimationParasaurolophus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        float globalSpeed = 1.0F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;
        
        MowzieModelRenderer head = model.getCube("Head");
        
        MowzieModelRenderer neck1 = model.getCube("Neck");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        
        model.faceTarget(head, 3, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 3, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 3, rotationYaw, rotationPitch);
    }
}