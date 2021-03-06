package org.jurassicraft.server.dinosaur;

import net.minecraftforge.common.BiomeDictionary;
import org.jurassicraft.server.entity.Diet;
import org.jurassicraft.server.entity.dinosaur.TyrannosaurusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class TyrannosaurusDinosaur extends Dinosaur {
    public TyrannosaurusDinosaur() {
        super();

        this.setName("Tyrannosaurus");
        this.setDinosaurClass(TyrannosaurusEntity.class);
        this.setDinosaurType(DinosaurType.AGGRESSIVE);
        this.setTimePeriod(TimePeriod.CRETACEOUS);
        this.setEggColorMale(0x4E502C, 0x353731);
        this.setEggColorFemale(0xBA997E, 0x7D5D48);
        this.setHealth(10, 80);
        this.setSpeed(0.35, 0.42);
        this.setAttackSpeed(1.1);
        this.setStrength(5, 20);
        this.setMaximumAge(this.fromDays(60));
        this.setEyeHeight(0.6F, 3.8F);
        this.setSizeX(0.45F, 3.0F);
        this.setSizeY(0.8F, 4.0F);
        this.setStorage(54);
        this.setDiet(Diet.CARNIVORE);
        this.setBones("arm_bones", "foot_bones", "leg_bones", "neck_vertebrae", "pelvis", "ribcage", "shoulder_bone", "skull", "tail_vertebrae", "tooth");
        this.setHeadCubeName("Head");
        this.setScale(2.4F, 0.35F);
        this.setMaxHerdSize(2);
        this.setAttackBias(1000.0);
        this.setSpawn(5, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS), BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
    }
}
