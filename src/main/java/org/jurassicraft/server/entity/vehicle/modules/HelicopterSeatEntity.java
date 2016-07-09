package org.jurassicraft.server.entity.vehicle.modules;

import com.google.common.base.Predicate;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import org.jurassicraft.server.entity.vehicle.HelicopterBaseEntity;

import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Entity representing a seat inside the helicopter. Should NOT be spawned inside the world, the {@link HelicopterBaseEntity Helicopter Entity} handles that for you.
 */
public class HelicopterSeatEntity extends Entity implements IEntityAdditionalSpawnData
{
    public HelicopterBaseEntity parent;
    private UUID parentID;
    private float dist;
    private int index;

    public HelicopterSeatEntity(World worldIn)
    {
        super(worldIn);
        setEntityBoundingBox(createBoundingBox());
        noClip = true;
        parentID = UUID.randomUUID();
    }

    public HelicopterSeatEntity(float dist, int index, HelicopterBaseEntity parent)
    {
        super(parent.getEntityWorld());
        setEntityBoundingBox(createBoundingBox());
        this.dist = dist;
        this.index = index;
        this.parent = checkNotNull(parent, "parent");
        parentID = parent.getHeliID();
        noClip = true;
    }

    public static HelicopterBaseEntity getParentFromID(World worldObj, final UUID id)
    {
        List<HelicopterBaseEntity> list = worldObj.getEntities(HelicopterBaseEntity.class, new Predicate<Entity>()
        {
            @Override
            public boolean apply(Entity input)
            {
                if (input instanceof HelicopterBaseEntity)
                {
                    HelicopterBaseEntity helicopterBase = (HelicopterBaseEntity) input;
                    return helicopterBase.getHeliID().equals(id);
                }
                return false;
            }
        });
        if (list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    private AxisAlignedBB createBoundingBox()
    {
        return new AxisAlignedBB(posX, posY, posZ, posX, posY, posZ);
    }

    @Override
    protected void entityInit()
    {
        width = 0f;
        height = 0f;
    }

    @Override
    public void onUpdate()
    {
        update();
    }

    public void update()
    {
        motionX = 0f;
        motionY = 0f;
        motionZ = 0f;
        if (parent == null) // we are in this state right after reloading a map
        {
            parent = getParentFromID(worldObj, parentID);
        }
        if (parent != null)
        {
            float angle = parent.rotationYaw;

            resetPos();
            if (parent.getSeat(index) == null)
            {
                parent.setSeat(index, this);
            }
            if (parent.isDead && !worldObj.isRemote)
            {
                System.out.println("KILLED");
                worldObj.removeEntity(this);
            }
        }
        else
        {
            System.out.println("no parent :c " + parentID);
        }
        setEntityBoundingBox(createBoundingBox());
    }

    public void resetPos()
    {
        float nx = -MathHelper.sin(parent.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(parent.rotationPitch / 180.0F * (float) Math.PI) * dist;
        float nz = MathHelper.cos(parent.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(parent.rotationPitch / 180.0F * (float) Math.PI) * dist;
        float ny = MathHelper.sin((parent.rotationPitch) / 180.0F * (float) Math.PI) * dist;

        this.posX = parent.posX + nx - (parent.lastTickPosX - parent.posX);
        this.posY = parent.posY + ny + 0.4f - (parent.lastTickPosY - parent.posY);
        this.posZ = parent.posZ + nz - (parent.lastTickPosZ - parent.posZ);
        if (Double.isNaN(posX) || Double.isNaN(posY) || Double.isNaN(posZ))
        {
            posX = lastTickPosX;
            posY = lastTickPosY;
            posZ = lastTickPosZ;
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        dist = tagCompound.getFloat("dist");
        index = tagCompound.getInteger("index");
        parentID = UUID.fromString(tagCompound.getString("heliID"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setFloat("dist", dist);
        tagCompound.setInteger("index", index);
        tagCompound.setString("heliID", parentID.toString());
    }

    public HelicopterBaseEntity getParent()
    {
        return parent;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public double getMountedYOffset()
    {
        return 0f;
    }

    public void setParentID(UUID parentID)
    {
        this.parentID = parentID;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, parentID.toString());
        buffer.writeFloat(dist);
        buffer.writeInt(index);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        parentID = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));
        dist = additionalData.readFloat();
        index = additionalData.readInt();
    }
}
