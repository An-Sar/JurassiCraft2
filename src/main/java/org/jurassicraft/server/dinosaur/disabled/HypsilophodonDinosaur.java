package org.jurassicraft.server.dinosaur.disabled;

import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.entity.dinosaur.disabled.HypsilophodonEntity;
import org.jurassicraft.server.period.TimePeriod;

public class HypsilophodonDinosaur extends Dinosaur
{
    public HypsilophodonDinosaur()
    {
        super();

        this.setName("Hypsilophodon");
        this.setDinosaurClass(HypsilophodonEntity.class);
        this.setTimePeriod(TimePeriod.CRETACEOUS);
        this.setEggColorMale(0x7DAC78, 0x3E6226);
        this.setEggColorFemale(0x799073, 0x33432F);
        this.setHealth(3, 10);
        this.setSpeed(0.35, 0.30);
        this.setStrength(1, 5);
        this.setMaximumAge(fromDays(35));
        this.setEyeHeight(0.2F, 0.7F);
        this.setSizeX(0.2F, 0.6F);
        this.setSizeY(0.25F, 0.85F);
        this.setStorage(9);
        this.setDiet(Diet.HERBIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "arm_bones");
        this.setHeadCubeName("Head ");
        this.setScale(0.65F, 0.2F);
        this.disableRegistry();
    }
}
