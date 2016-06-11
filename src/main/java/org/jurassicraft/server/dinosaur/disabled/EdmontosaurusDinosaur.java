package org.jurassicraft.server.dinosaur.disabled;

import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.entity.dinosaur.disabled.EdmontosaurusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class EdmontosaurusDinosaur extends Dinosaur
{
    public EdmontosaurusDinosaur()
    {
        super();

        this.setName("Edmontosaurus");
        this.setDinosaurClass(EdmontosaurusEntity.class);
        this.setTimePeriod(TimePeriod.CRETACEOUS);
        this.setEggColorMale(0xB97840, 0x644329);
        this.setEggColorFemale(0x8F8039, 0x615A30);
        this.setHealth(10, 40);
        this.setStrength(5, 20);
        this.setSpeed(0.46, 0.41);
        this.setMaximumAge(fromDays(50));
        this.setEyeHeight(0.55F, 3.45F);
        this.setSizeX(0.5F, 2.8F);
        this.setSizeY(0.8F, 4.25F);
        this.setStorage(45);
        this.setDiet(Diet.HERBIVORE);
        this.setBones("cheek_teeth");
        this.setHeadCubeName("Head");
        this.setScale(2.65F, 0.45F);
        this.setOffset(0.0F, 0.775F, 0.0F);
        this.disableRegistry();
    }
}
