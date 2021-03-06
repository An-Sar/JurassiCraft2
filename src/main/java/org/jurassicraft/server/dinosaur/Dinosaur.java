package org.jurassicraft.server.dinosaur;

import net.ilexiconn.llibrary.client.model.tabula.container.TabulaCubeContainer;
import net.ilexiconn.llibrary.client.model.tabula.container.TabulaModelContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.animation.PoseHandler;
import org.jurassicraft.server.api.GrowthStageGenderContainer;
import org.jurassicraft.server.api.Hybrid;
import org.jurassicraft.server.entity.Diet;
import org.jurassicraft.server.entity.DinosaurEntity;
import org.jurassicraft.server.entity.GrowthStage;
import org.jurassicraft.server.entity.SleepingSchedule;
import org.jurassicraft.server.period.TimePeriod;
import org.jurassicraft.server.tabula.TabulaModelHelper;

import javax.vecmathimpl.Matrix4d;
import javax.vecmathimpl.Vector3d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Dinosaur implements Comparable<Dinosaur> {
    private final Map<GrowthStage, List<ResourceLocation>> overlays = new HashMap<>();
    private final Map<GrowthStage, ResourceLocation> maleTextures = new HashMap<>();
    private final Map<GrowthStage, ResourceLocation> femaleTextures = new HashMap<>();
    private final Map<GrowthStageGenderContainer, ResourceLocation> eyelidTextures = new HashMap<>();

    private String name;
    private Class<? extends DinosaurEntity> dinoClazz;
    private int primaryEggColorMale, primaryEggColorFemale;
    private int secondaryEggColorMale, secondaryEggColorFemale;
    private TimePeriod timePeriod;
    private double babyHealth, adultHealth;
    private double babyStrength, adultStrength;
    private double babySpeed, adultSpeed;
    private float babySizeX, adultSizeX;
    private float babySizeY, adultSizeY;
    private float babyEyeHeight, adultEyeHeight;
    private double attackSpeed = 1.0;
    private boolean shouldRegister = true;
    private boolean isMarineAnimal;
    private boolean isMammal;
    private int storage;
    private int overlayCount;
    private Diet diet;
    private SleepingSchedule sleepingSchedule = SleepingSchedule.DIURNAL;
    private String[] bones;
    private int maximumAge;
    private String headCubeName;
    private boolean isImprintable;

    private float scaleInfant;
    private float scaleAdult;

    private float offsetX;
    private float offsetY;
    private float offsetZ;

    private TabulaModelContainer modelAdult;
    private TabulaModelContainer modelInfant;
    private TabulaModelContainer modelJuvenile;
    private TabulaModelContainer modelAdolescent;

    private PoseHandler poseHandler;

    private boolean defendOwner;

    private boolean flee;
    private double flockSpeed = 0.8;

    private double attackBias = 200.0;
    private int maxHerdSize = 32;

    private int spawnChance;
    private Biome[] spawnBiomes;
    private DinosaurType dinosaurType;

    public static Matrix4d getParentRotationMatrix(TabulaModelContainer model, TabulaCubeContainer cube, boolean includeParents, boolean ignoreSelf, float rot) {
        List<TabulaCubeContainer> parentCubes = new ArrayList<>();

        do {
            if (ignoreSelf) {
                ignoreSelf = false;
            } else {
                parentCubes.add(cube);
            }
        }
        while (includeParents && cube.getParentIdentifier() != null && (cube = TabulaModelHelper.getCubeByIdentifier(cube.getParentIdentifier(), model)) != null);

        Matrix4d mat = new Matrix4d();
        mat.setIdentity();
        Matrix4d transform = new Matrix4d();

        transform.rotY(rot / 180 * Math.PI);
        mat.mul(transform);

        for (int i = parentCubes.size() - 1; i >= 0; i--) {
            cube = parentCubes.get(i);
            transform.setIdentity();
            transform.setTranslation(new Vector3d(cube.getPosition()));
            mat.mul(transform);

            double rotX = cube.getRotation()[0];
            double rotY = cube.getRotation()[1];
            double rotZ = cube.getRotation()[2];

            transform.rotZ(rotZ / 180 * Math.PI);
            mat.mul(transform);
            transform.rotY(rotY / 180 * Math.PI);
            mat.mul(transform);
            transform.rotX(rotX / 180 * Math.PI);
            mat.mul(transform);
        }

        return mat;
    }

    private static double[][] getTransformation(Matrix4d matrix) {
        double sinRotationAngleY, cosRotationAngleY, sinRotationAngleX, cosRotationAngleX, sinRotationAngleZ, cosRotationAngleZ;

        sinRotationAngleY = -matrix.m20;
        cosRotationAngleY = Math.sqrt(1 - sinRotationAngleY * sinRotationAngleY);

        if (Math.abs(cosRotationAngleY) > 0.0001) {
            sinRotationAngleX = matrix.m21 / cosRotationAngleY;
            cosRotationAngleX = matrix.m22 / cosRotationAngleY;
            sinRotationAngleZ = matrix.m10 / cosRotationAngleY;
            cosRotationAngleZ = matrix.m00 / cosRotationAngleY;
        } else {
            sinRotationAngleX = -matrix.m12;
            cosRotationAngleX = matrix.m11;
            sinRotationAngleZ = 0;
            cosRotationAngleZ = 1;
        }

        double rotationAngleX = epsilon(Math.atan2(sinRotationAngleX, cosRotationAngleX)) / Math.PI * 180;
        double rotationAngleY = epsilon(Math.atan2(sinRotationAngleY, cosRotationAngleY)) / Math.PI * 180;
        double rotationAngleZ = epsilon(Math.atan2(sinRotationAngleZ, cosRotationAngleZ)) / Math.PI * 180;
        return new double[][] { { epsilon(matrix.m03), epsilon(matrix.m13), epsilon(matrix.m23) }, { rotationAngleX, rotationAngleY, rotationAngleZ } };
    }

    private static double epsilon(double x) {
        return x < 0 ? x > -0.0001 ? 0 : x : x < 0.0001 ? 0 : x;
    }

    public void init() {
        String formattedName = this.getName().toLowerCase(Locale.ENGLISH).replaceAll(" ", "_");

        this.modelAdult = this.parseModel("adult");

        for (GrowthStage stage : GrowthStage.values()) {
            if (stage != GrowthStage.ADULT) {
                if (this.doesSupportGrowthStage(stage)) {
                    this.setModelContainer(stage, this.parseModel(stage.name().toLowerCase(Locale.ENGLISH)));
                } else {
                    this.setModelContainer(stage, this.modelAdult);
                }
            }
        }

        String baseTextures = "textures/entities/" + formattedName + "/";

        for (GrowthStage growthStage : GrowthStage.values()) {
            String growthStageName = growthStage.name().toLowerCase(Locale.ENGLISH);

            if (!this.doesSupportGrowthStage(growthStage)) {
                growthStageName = GrowthStage.ADULT.name().toLowerCase(Locale.ENGLISH);
            }

            if (this instanceof Hybrid) {
                String baseName = baseTextures + formattedName + "_" + growthStageName;

                ResourceLocation hybridTexture = new ResourceLocation(JurassiCraft.MODID, baseName + ".png");

                this.maleTextures.put(growthStage, hybridTexture);
                this.femaleTextures.put(growthStage, hybridTexture);

                ResourceLocation eyelidTexture = new ResourceLocation(JurassiCraft.MODID, baseName + "_eyelid.png");
                this.eyelidTextures.put(new GrowthStageGenderContainer(growthStage, false), eyelidTexture);
                this.eyelidTextures.put(new GrowthStageGenderContainer(growthStage, true), eyelidTexture);
            } else {
                this.maleTextures.put(growthStage, new ResourceLocation(JurassiCraft.MODID, baseTextures + formattedName + "_male_" + growthStageName + ".png"));
                this.femaleTextures.put(growthStage, new ResourceLocation(JurassiCraft.MODID, baseTextures + formattedName + "_female_" + growthStageName + ".png"));
                this.eyelidTextures.put(new GrowthStageGenderContainer(growthStage, true), new ResourceLocation(JurassiCraft.MODID, baseTextures + formattedName + "_male_" + growthStageName + "_eyelid.png"));
                this.eyelidTextures.put(new GrowthStageGenderContainer(growthStage, false), new ResourceLocation(JurassiCraft.MODID, baseTextures + formattedName + "_female_" + growthStageName + "_eyelid.png"));
            }

            List<ResourceLocation> overlaysForGrowthStage = new ArrayList<>();

            for (int i = 1; i <= this.getOverlayCount(); i++) {
                overlaysForGrowthStage.add(new ResourceLocation(JurassiCraft.MODID, baseTextures + formattedName + "_overlay_" + growthStageName + "_" + i + ".png"));
            }

            this.overlays.put(growthStage, overlaysForGrowthStage);
        }

        this.poseHandler = new PoseHandler(this);
    }

    protected void setDinosaurType(DinosaurType type) {
        this.dinosaurType = type;
    }

    protected TabulaModelContainer parseModel(String growthStage) {
        String formattedName = this.getName().toLowerCase(Locale.ENGLISH).replaceAll(" ", "_");
        String modelPath = "/assets/jurassicraft/models/entities/" + formattedName + "/" + growthStage + "/" + formattedName + "_" + growthStage + "_idle";

        try {
            return TabulaModelHelper.loadTabulaModel(modelPath);
        } catch (Exception e) {
            JurassiCraft.INSTANCE.getLogger().fatal("Couldn't load model " + modelPath, e);
        }

        return null;
    }

    public void setEggColorMale(int primary, int secondary) {
        this.primaryEggColorMale = primary;
        this.secondaryEggColorMale = secondary;
    }

    public void setEggColorFemale(int primary, int secondary) {
        this.primaryEggColorFemale = primary;
        this.secondaryEggColorFemale = secondary;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setHealth(double baby, double adult) {
        this.babyHealth = baby;
        this.adultHealth = adult;
    }

    public void setStrength(double baby, double adult) {
        this.babyStrength = baby;
        this.adultStrength = adult;
    }

    public void setSpeed(double baby, double adult) {
        this.babySpeed = baby;
        this.adultSpeed = adult;
    }

    public void setSizeX(float baby, float adult) {
        this.babySizeX = baby;
        this.adultSizeX = adult;
    }

    public void setSizeY(float baby, float adult) {
        this.babySizeY = baby;
        this.adultSizeY = adult;
    }

    public void setEyeHeight(float baby, float adult) {
        this.babyEyeHeight = baby;
        this.adultEyeHeight = adult;
    }

    public void setFlockSpeed(float speed) {
        this.flockSpeed = speed;
    }

    public void setAttackBias(double bias) {
        this.attackBias = bias;
    }

    public void setMaxHerdSize(int herdSize) {
        this.maxHerdSize = herdSize;
    }

    public void disableRegistry() {
        this.shouldRegister = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends DinosaurEntity> getDinosaurClass() {
        return this.dinoClazz;
    }

    public void setDinosaurClass(Class<? extends DinosaurEntity> clazz) {
        this.dinoClazz = clazz;
    }

    public int getEggPrimaryColorMale() {
        return this.primaryEggColorMale;
    }

    public int getEggSecondaryColorMale() {
        return this.secondaryEggColorMale;
    }

    public int getEggPrimaryColorFemale() {
        return this.primaryEggColorFemale;
    }

    public int getEggSecondaryColorFemale() {
        return this.secondaryEggColorFemale;
    }

    public TimePeriod getPeriod() {
        return this.timePeriod;
    }

    public double getBabyHealth() {
        return this.babyHealth;
    }

    public double getAdultHealth() {
        return this.adultHealth;
    }

    public double getBabySpeed() {
        return this.babySpeed;
    }

    public double getAdultSpeed() {
        return this.adultSpeed;
    }

    public double getBabyStrength() {
        return this.babyStrength;
    }

    public double getAdultStrength() {
        return this.adultStrength;
    }

    public float getBabySizeX() {
        return this.babySizeX;
    }

    public float getBabySizeY() {
        return this.babySizeY;
    }

    public float getAdultSizeX() {
        return this.adultSizeX;
    }

    public float getAdultSizeY() {
        return this.adultSizeY;
    }

    public float getBabyEyeHeight() {
        return this.babyEyeHeight;
    }

    public float getAdultEyeHeight() {
        return this.adultEyeHeight;
    }

    public int getMaximumAge() {
        return this.maximumAge;
    }

    public void setMaximumAge(int age) {
        this.maximumAge = age;
    }

    public ResourceLocation getMaleTexture(GrowthStage stage) {
        return this.maleTextures.get(stage);
    }

    public ResourceLocation getFemaleTexture(GrowthStage stage) {
        return this.femaleTextures.get(stage);
    }

    public double getAttackSpeed() {
        return this.attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public boolean shouldRegister() {
        return this.shouldRegister;
    }

    protected String getDinosaurTexture(String subtype) {
        String dinosaurName = this.getName().toLowerCase(Locale.ENGLISH).replaceAll(" ", "_");

        String texture = "jurassicraft:textures/entities/" + dinosaurName + "/" + dinosaurName;

        if (subtype.length() > 0) {
            texture += "_" + subtype;
        }

        return texture + ".png";
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    protected int fromDays(int days) {
        return (days * 24000) / 8;
    }

    @Override
    public int compareTo(Dinosaur dinosaur) {
        return this.getName().compareTo(dinosaur.getName());
    }

    public boolean isMarineAnimal() {
        return this.isMarineAnimal;
    }

    public void setMarineAnimal(boolean marineAnimal) {
        this.isMarineAnimal = marineAnimal;
    }

    public boolean isMammal() {
        return this.isMammal;
    }

    public void setMammal(boolean isMammal) {
        this.isMammal = isMammal;
    }

    public int getLipids() {
        return 1500;
    }

    public int getMinerals() {
        return 1500;
    }

    public int getVitamins() {
        return 1500;
    }

    public int getProximates() // TODO
    {
        return 1500;
    }

    public int getStorage() {
        return this.storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public ResourceLocation getOverlayTexture(GrowthStage stage, int overlay) {
        return this.overlays.containsKey(stage) ? this.overlays.get(stage).get(overlay) : null;
    }

    public int getOverlayCount() {
        return this.overlayCount;
    }

    public void setOverlayCount(int count) {
        this.overlayCount = count;
    }

    public ResourceLocation getEyelidTexture(DinosaurEntity entity) {
        return this.eyelidTextures.get(new GrowthStageGenderContainer(entity.getGrowthStage(), entity.isMale()));
    }

    public Diet getDiet() {
        return this.diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public SleepingSchedule getSleepingSchedule() {
        return this.sleepingSchedule;
    }

    public void setSleepingSchedule(SleepingSchedule sleepingSchedule) {
        this.sleepingSchedule = sleepingSchedule;
    }

    public String[] getBones() {
        return this.bones;
    }

    public void setBones(String... bones) {
        this.bones = bones;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Dinosaur && ((Dinosaur) object).getName().equalsIgnoreCase(this.getName());
    }

    public String getHeadCubeName() {
        return this.headCubeName;
    }

    public void setHeadCubeName(String headCubeName) {
        this.headCubeName = headCubeName;
    }

    public double[] getCubePosition(String cubeName, GrowthStage stage) {
        TabulaModelContainer model = this.getModelContainer(stage);

        TabulaCubeContainer cube = TabulaModelHelper.getCubeByName(cubeName, model);

        if (cube != null) {
            return cube.getPosition();
        }

        return new double[] { 0.0, 0.0, 0.0 };
    }

    public double[] getParentedCubePosition(String cubeName, GrowthStage stage, float rot) {
        TabulaModelContainer model = this.getModelContainer(stage);

        TabulaCubeContainer cube = TabulaModelHelper.getCubeByName(cubeName, model);

        if (cube != null) {
            return getTransformation(getParentRotationMatrix(model, cube, true, false, rot))[0];
        }

        return new double[] { 0.0, 0.0, 0.0 };
    }

    public double[] getHeadPosition(GrowthStage stage, float rot) {
        return this.getParentedCubePosition(this.getHeadCubeName(), stage, rot);
    }

    public TabulaModelContainer getModelContainer(GrowthStage stage) {
        switch (stage) {
            case INFANT:
                return this.modelInfant;
            case JUVENILE:
                return this.modelJuvenile;
            case ADOLESCENT:
                return this.modelAdolescent;
            default:
                return this.modelAdult;
        }
    }

    private void setModelContainer(GrowthStage stage, TabulaModelContainer model) {
        switch (stage) {
            case INFANT:
                this.modelInfant = model;
            case JUVENILE:
                this.modelJuvenile = model;
            case ADOLESCENT:
                this.modelAdolescent = model;
            default:
                this.modelAdult = model;
        }
    }

    public void setScale(float scaleAdult, float scaleInfant) {
        this.scaleInfant = scaleInfant;
        this.scaleAdult = scaleAdult;
    }

    public void setOffset(float x, float y, float z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
    }

    public void setDefendOwner(boolean defendOwner) {
        this.defendOwner = defendOwner;
    }

    public void setFlee(boolean flee) {
        this.flee = flee;
    }

    public double getScaleInfant() {
        return this.scaleInfant;
    }

    public double getScaleAdult() {
        return this.scaleAdult;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public PoseHandler getPoseHandler() {
        return this.poseHandler;
    }

    public boolean doesSupportGrowthStage(GrowthStage stage) {
        return stage == GrowthStage.ADULT;
    }

    public boolean isImprintable() {
        return this.isImprintable;
    }

    public void setImprintable(boolean imprintable) {
        this.isImprintable = imprintable;
    }

    public boolean shouldDefendOwner() {
        return this.defendOwner;
    }

    public boolean shouldFlee() {
        return this.flee;
    }

    public double getFlockSpeed() {
        return this.flockSpeed;
    }

    public double getAttackBias() {
        return this.attackBias;
    }

    public int getMaxHerdSize() {
        return this.maxHerdSize;
    }

    public void setSpawn(int chance, Biome[]... allBiomes) {
        this.spawnChance = chance;
        List<Biome> spawnBiomes = new LinkedList<>();
        for (Biome[] biomes : allBiomes) {
            for (Biome biome : biomes) {
                if (!spawnBiomes.contains(biome)) {
                    spawnBiomes.add(biome);
                }
            }
        }
        this.spawnBiomes = spawnBiomes.toArray(new Biome[0]);
    }

    public int getSpawnChance() {
        return this.spawnChance;
    }

    public Biome[] getSpawnBiomes() {
        return this.spawnBiomes;
    }

    public String getLocalizationName() {
        return "entity.jurassicraft." + this.getName().toLowerCase(Locale.ENGLISH).replaceAll(" ", "_") + ".name";
    }

    public DinosaurType getDinosaurType() {
        return this.dinosaurType;
    }

    public enum DinosaurType {
        AGGRESSIVE,
        NEUTRAL,
        PASSIVE,
        SCARED
    }
}
