package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.server.animation.Animation;

public enum DinosaurAnimation {
    IDLE(false, false, false),
    ATTACKING(false, false),
    INJURED(false, false),
    HEAD_COCKING,
    CALLING,
    HISSING,
    POUNCING(false, false),
    SNIFFING,
    EATING,
    DRINKING,
    MATING(false, false),
    SLEEPING(true, false),
    RESTING(true, true),
    ROARING,
    SPEAK(false, false),
    LOOKING_LEFT,
    LOOKING_RIGHT,
    BEGGING,
    SNAP,
    DYING(true, false, false),
    SCRATCHING,
    SPITTING,
    PECKING,
    WALKING(false, false, false), RUNNING(false, false, false), SWIMMING(false, false, false), FLYING(false, false, false),
    VELOCIRAPTOR_PREPARE_POUNCE(false, false), VELOCIRAPTOR_LEAP(true, false), VELOCIRAPTOR_LAND(true, false, false),
    DILOPHOSAURUS_SPIT(false, false);

    private Animation animation;
    private boolean hold;
    private boolean doesBlockMovement;
    private boolean useInertia;

    DinosaurAnimation(boolean hold, boolean blockMovement) {
        this(hold, blockMovement, true);
    }

    DinosaurAnimation(boolean hold, boolean blockMovement, boolean useInertia) {
        this.hold = hold;
        this.doesBlockMovement = blockMovement;
        this.useInertia = useInertia;
    }

    DinosaurAnimation() {
        this(false, true);
    }

    public static Animation[] getAnimations() {
        Animation[] animations = new Animation[values().length];

        for (int i = 0; i < animations.length; i++) {
            animations[i] = values()[i].get();
        }

        return animations;
    }

    public static DinosaurAnimation getAnimation(Animation animation) {
        for (DinosaurAnimation animations : values()) {
            if (animation.equals(animations.animation)) {
                return animations;
            }
        }

        return DinosaurAnimation.IDLE;
    }

    public Animation get() {
        if (this.animation == null) {
            this.animation = Animation.create(-1);
        }

        return this.animation;
    }

    public boolean shouldHold() {
        return this.hold;
    }

    public boolean doesBlockMovement() {
        return this.doesBlockMovement;
    }

    public boolean useInertia() {
        return this.useInertia;
    }
}
