package org.jurassicraft.server.dinosaur.disabled;

import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.Diet;
import org.jurassicraft.server.entity.base.SleepingSchedule;
import org.jurassicraft.server.entity.dinosaur.disabled.TherizinosaurusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class TherizinosaurusDinosaur extends Dinosaur
{
    public TherizinosaurusDinosaur()
    {
        super();

        this.setName("Therizinosaurus");
        this.setDinosaurClass(TherizinosaurusEntity.class);
        this.setTimePeriod(TimePeriod.CRETACEOUS);
        this.setEggColorMale(0x787878, 0x2B2B2B);
        this.setEggColorFemale(0x7F7F7F, 0x272727);
        this.setHealth(10, 40);
        this.setSpeed(0.45, 0.40);
        this.setStrength(20, 35);
        this.setMaximumAge(fromDays(65));
        this.setEyeHeight(0.95F, 5.85F);
        this.setSizeX(0.65F, 2.25F);
        this.setSizeY(1.0F, 5.95F);
        this.setStorage(36);
        this.setDiet(Diet.HERBIVORE);
        this.setSleepingSchedule(SleepingSchedule.DIURNAL);
        this.setBones("skull", "teeth");
        this.setHeadCubeName("Head");
        this.setUsePosesForWalkingAnim(false);
        this.setScale(3.5F, 0.55F);
        this.setOffset(0.0F, 1.0F, 0.0F);
        this.disableRegistry();
    }
}
