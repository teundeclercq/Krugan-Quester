package com.krugan.util;

import com.krugan.quester.Main;
import com.krugan.quests.CooksAssistant;
import com.krugan.quests.ernestthechicken.ErnestTheChicken;
import com.krugan.quests.ImpCatcher;
import com.krugan.quests.RestlessGhost;
import com.krugan.quests.RuneMysteries;
import com.krugan.quests.VampireSlayer;
import com.krugan.quests.WitchPotion;

public class QuestFactory {
    public static Task createQuest(QuestType type, Main ctx) {
        switch (type) {
            case COOKS_ASSISTANT:
                return new CooksAssistant(ctx);
            case SHEEP_SHEARER:
                return null;
            case RESTLESS_GHOST:
                return new RestlessGhost(ctx);
            case IMP_CATCHER:
                return new ImpCatcher(ctx);
            case WITCH_POTION:
                return new WitchPotion(ctx);
            case ERNEST_THE_CHICKEN:
                return new ErnestTheChicken(ctx);
            case RUNE_MYSTERIES:
                return new RuneMysteries(ctx);
            case VAMPIRE_SLAYER:
                return new VampireSlayer(ctx);
            default:
                return null;
        }

    }
}
