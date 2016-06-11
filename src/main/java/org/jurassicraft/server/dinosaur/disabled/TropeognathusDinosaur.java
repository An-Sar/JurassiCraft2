package org.jurassicraft.server.dinosaur.disabled;

import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.entity.dinosaur.disabled.TropeognathusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class TropeognathusDinosaur extends Dinosaur
{
    public TropeognathusDinosaur()
    {
        super();

        this.setName("Tropeognathus");
        this.setDinosaurClass(TropeognathusEntity.class);
        this.setTimePeriod(TimePeriod.CRETACEOUS);
        this.setEggColorMale(0x4E646B, 0x483141);
        this.setEggColorFemale(0x5C6C71, 0x4D3E4D);
        this.setHealth(10, 20);
        this.setSpeed(0.46, 0.30);
        this.setStrength(5, 20);
        this.setMaximumAge(fromDays(50));
        this.setEyeHeight(0.45F, 1.45F);
        this.setSizeX(0.35F, 1.5F);
        this.setSizeY(0.45F, 1.55F);
        this.setStorage(27);
        this.setDiet(Diet.CARNIVORE);
        this.setBones("leg_bones", "pelvis", "skull", "tail_vertebrae", "teeth", "wing_bones");
        this.setHeadCubeName("Head");
        this.setScale(1.15F, 0.3F);
        this.disableRegistry();
    }
}
