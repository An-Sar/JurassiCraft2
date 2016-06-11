package org.jurassicraft.server.dinosaur.disabled;

import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.entity.dinosaur.disabled.ApatosaurusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class ApatosaurusDinosaur extends Dinosaur
{
    public ApatosaurusDinosaur()
    {
        super();

        this.setName("Apatosaurus");
        this.setDinosaurClass(ApatosaurusEntity.class);
        this.setTimePeriod(TimePeriod.JURASSIC);
        this.setEggColorMale(0xA79F93, 0x987664);
        this.setEggColorFemale(0x7E7D70, 0x30343E);
        this.setHealth(10, 60);
        this.setStrength(5, 20);
        this.setSpeed(0.32, 0.25);
        this.setMaximumAge(fromDays(80));
        this.setEyeHeight(0.9F, 6.8F);
        this.setSizeX(0.9F, 6.5F);
        this.setSizeY(1.0F, 6.8F);
        this.setStorage(54);
        this.setDiet(Diet.HERBIVORE);
        this.setBones("skull", "tooth");
        this.setHeadCubeName("Head");
        this.setScale(2.0F, 0.25F);
        this.setOffset(0.0F, 0.0F, 0.1F);
        this.disableRegistry();
    }
}
