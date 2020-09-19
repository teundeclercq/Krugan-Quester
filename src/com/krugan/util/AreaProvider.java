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
}
