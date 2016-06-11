package org.jurassicraft.server.dinosaur.disabled;

import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.entity.dinosaur.disabled.MetriacanthosaurusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class MetriacanthosaurusDinosaur extends Dinosaur
{
    public MetriacanthosaurusDinosaur()
    {
        super();

        this.setName("Metriacanthosaurus");
        this.setDinosaurClass(MetriacanthosaurusEntity.class);
        this.setTimePeriod(TimePeriod.JURASSIC);
        this.setEggColorMale(0xB05E1C, 0xE7DB27);
        this.setEggColorFemale(0xB5985E, 0x60451C);
        this.setHealth(10, 40);
        this.setStrength(5, 20);
        this.setSpeed(0.44, 0.4);
        this.setMaximumAge(fromDays(40));
        this.setEyeHeight(0.35F, 1.75F);
        this.setSizeX(0.25F, 1.15F);
        this.setSizeY(0.35F, 1.95F);
        this.setStorage(27);
        this.setDiet(Diet.CARNIVORE);
        this.setBones("skull", "tooth");
        this.setHeadCubeName("Head");
        this.setScale(1.0F, 0.25F);
        this.disableRegistry();
    }
}
