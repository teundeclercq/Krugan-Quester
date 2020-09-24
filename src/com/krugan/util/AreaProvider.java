package com.krugan.util;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class AreaProvider {
    public static class CooksAssistant {
        public final static Area cookArea = new Area(3205, 3217, 3212, 3212);
        public final static Area basementArea = new Area(3211, 9625, 3216, 9622);
        public final static Area chickenArea = new Area(3227, 3301, 3233, 3297);
        public final static Area cowArea = new Area(3251, 3278, 3256, 3273);
        public final static Area grainArea = new Area(3159, 3296, 3163, 3292);
        public final static Area windMillArea = new Area(3164, 3302, 3168, 3300);
    }
    public static class RestlessGhost {
        public final static Area start = new Area(3240, 3215, 3247, 3204);
        public final static Area ghostGraveYard = new Area(3247, 3195, 3252, 3190);
        public final static Area fatherUrayArea = new Area(3144, 3177, 3151, 3173);
        public final static Area wizardTowerBasementLadderDownStairs = new Area(3103, 9577, 3110, 9575);
        public final static Area wizardTowerBasementLadder = new Area(3106, 3159, 3103, 3161);
        public final static Area wizardTowerBasementSkull = new Area(3114, 9569, 3121, 9564);;
    }
    public static class ImpCatcher {
        public final static Area impKillingArea = new Area(
                new Tile(3036, 3314, 0),
                new Tile(3036, 3328, 0),
                new Tile(3025, 3328, 0),
                new Tile(3020, 3325, 0),
                new Tile(3017, 3325, 0),
                new Tile(3014, 3325, 0),
                new Tile(3012, 3322, 0),
                new Tile(3008, 3322, 0),
                new Tile(3003, 3322, 0),
                new Tile(2999, 3319, 0),
                new Tile(2998, 3313, 0),
                new Tile(2995, 3303, 0),
                new Tile(2991, 3294, 0),
                new Tile(3001, 3293, 0),
                new Tile(3011, 3293, 0),
                new Tile(3012, 3308, 0),
                new Tile(3013, 3310, 0),
                new Tile(3014, 3312, 0),
                new Tile(3018, 3312, 0),
                new Tile(3020, 3312, 0),
                new Tile(3023, 3314, 0));
        public final static Area startQuest = new Area(3102, 3165, 3105, 3162, 2);
    }

    public static class WitchPotion {
        public final static Area cowArea = new Area(3022, 3312, 3041, 3298);
        public final static Area draynorBank = new Area(3092, 3246, 3095, 3240);
        public final static Area eyeofNewtShop = new Area(3011, 3261, 3016, 3256);
        public final static Area onionField = new Area(2945, 3260, 2955, 3248);
        public final static Area hetty = new Area(2965, 3208, 2970, 3203);
        public final static Area ratArea = new Area(2965, 3208, 2970, 3203);
        public final static Area cookArea = new Area(2963, 3216, 2970, 3209);
    }

}
