package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;

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
    public static class ErnestTheChick {
        public final static Area veronicaArea = new Area(3106, 3331, 3114, 3327);
        public final static Area poisonArea = new Area(3097, 3365, 3098, 3364);
        public final static Area fishFoodArea = new Area(3107, 3361, 3110, 3354, 1);
        public final static Area ruberTubeArea = new Area(3108, 3368, 3112, 3366);
        public final static Area spadeRoom = new Area(3120, 3360, 3126, 3354);
        public final static Area fountainArea = new Area(3085, 3337, 3090, 3332);
        public final static Area avasRoom = new Area(3092, 3361, 3096, 3355);
        public final static Area bookCaseRoom = new Area(3097, 3362, 3101, 3355);
        public final static Area cellarTrap = new Area(3114, 9756, 3118, 9750);
        public final static GameObject leverA = GameObjects.closest("Lever A");
        public final static GameObject leverB = GameObjects.closest("Lever B");
        public final static GameObject leverC = GameObjects.closest("Lever C");
        public final static GameObject leverD = GameObjects.closest("Lever D");
        public final static GameObject leverE = GameObjects.closest("Lever E");
        public final static GameObject leverF = GameObjects.closest("Lever F");
        public final static Area oilCan = new Area(3090, 9757, 3097, 9753);
        public final static Area door1 = new Area(3108, 9756, 3108, 9759);
        public final static Area door2 = new Area(3105, 9760, 3104, 9760);
        public final static Area door3 = new Area(3102, 9758, 3102, 9757);
        public final static Area door4 = new Area(3100, 9760, 3099, 9760);
        public final static Area door5 = new Area(3097, 9762, 3097, 9763);
        public final static Area door6 = new Area(3099, 9765, 3100, 9765);
        public final static Area door7 = new Area(3104, 9765, 3105, 9765);
        public final static Area door8 = new Area(3102, 9763, 3102, 9762);
        public final static Area door9 = new Area(3100, 9755, 3099, 9755);

        public final static Area area1 = new Area(3108, 9756, 3108, 9756);
        public final static Area area2 = new Area(3108, 9759, 3108, 9759);

        public final static Area area3 = new Area(3102, 9756, 3102, 9756);

        public final static Area area4 = new Area(3102, 9759, 3102, 9759);

        public final static Area area5 = new Area(3101, 9760, 3101, 9760);

        public final static Area area6 = new Area(3098, 9760, 3098, 9760);

        public final static Area area7 = new Area(3097, 9761, 3097, 9761);

        public final static Area area8 = new Area(3091, 9756, 3096, 9754);

        public final static Tile tile1 = new Tile(3108, 9756, 0);

        public final static Tile tile2 =new Tile(3108, 9759, 0);

        public final static Tile tile3 =new Tile(3102, 9756, 0);

        public final static Tile tile4 =new Tile(3102, 9759, 0);

        public final static Tile tile5 =new Tile(3101, 9760, 0);

        public final static Tile tile6 =new Tile(3098, 9760, 0);

        public final static Tile tile7 =new Tile(3097, 9761, 0);

        public final static Tile tile8 =new Tile(3097, 9764, 0);

        public final static Tile tile9 =new Tile(3098, 9765, 0);

        public final static Tile tile10 =new Tile(3101, 9765, 0);

        public final static Tile tile11 =new Tile(3103, 9765, 0);

        public final static Tile tile12 = new Tile(3106, 9765, 0);

        public final static Tile tile13 =new Tile(3102, 9764, 0);

        public final static Tile tile14 = new Tile(3102, 9761, 0);

        public final static Tile tile15 = new Tile(3101, 9755, 0);
        public final static Area compostArea = new Area(3088, 3353, 3086, 3356);
        public final static Area professorArea = new Area(3108, 3369, 3112, 3364, 2);;
    }

    public static class RuneMysteries {
        public final static Area dukeArea = new Area(3208, 3224, 3213, 3218, 1);
        public final static Area seridorArea = new Area(3096, 9574, 3107, 9569);
        public final static Area auburyShop = new Area(3255, 3400, 3252, 3403);
    }

    public static class VampireSlayer {
        public final static Area draynorBank = new Area(3092, 3246, 3095, 3240, 0);
        public final static Area upstairsMorgan = new Area(3096, 3270, 3098, 3266, 1);
        public final static Area MorganArea = new Area(3096, 3270, 3102, 3266);
        public final static Area varrockInn = new Area(3218, 3402, 3229, 3394);
        public final static Area countDraynor = new Area(3080, 9768, 3075, 9778);
        public final static Area varrockGeneralStore =new Area(3214, 3419, 3220, 3411);
    }
}
