package org.jurassicraft.server.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;
import org.jurassicraft.server.entity.DinosaurEntity;

public class MoveUnderwaterEntityAI extends EntityAIBase {
    private DinosaurEntity swimmingEntity;
    private double xPosition;
    private double yPosition;
    private double zPosition;

    public MoveUnderwaterEntityAI(DinosaurEntity entity) {
        this.swimmingEntity = entity;
        this.setMutexBits(Mutex.MOVEMENT);
    }

    @Override
    public boolean shouldExecute() {
        if (this.swimmingEntity.getRNG().nextFloat() < 0.50) {
            return false;
        }

        Vec3d randomTarget = RandomPositionGenerator.findRandomTarget(this.swimmingEntity, 6, 2);

        if (randomTarget == null) {
            return false;
        } else {
            this.xPosition = randomTarget.xCoord;
            this.yPosition = randomTarget.yCoord;
            this.zPosition = randomTarget.zCoord;
            return true;
        }
    }

    @Override
    public boolean continueExecuting() {
        return !this.swimmingEntity.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        this.swimmingEntity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1.0D);
    }
}
