package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.interactive.interact.Interactable;

import java.util.Arrays;
import java.util.List;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class EndWitchPotion extends AdvancedTask {
    public EndWitchPotion(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) >= 3;
    }

    @Override
    public void onFinish() {
        log("Drinked from the cauldron");
    }

    @Override
    public int execute() {
        main.setStateClient("Ending witch potion.");
        Area area = AreaProvider.WitchPotion.hetty;
        try {
            TravelTo(area);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NPC hetty = NPCs.closest("Hetty");
        sleep(Calculations.random(500, 750));
        if (hetty != null) {
            TalkToNpc(hetty, "Talk-to", 0);
        }
        sleep(Calculations.random(5000, 10000));
        GameObject cauldron = GameObjects.closest("Cauldron");
        sleep(Calculations.random(500, 750));
        if (cauldron!=null) {
            log("Cauldron aanwezig");

//            log("Drinking from cauldron" + cauldron);
//            log("Cauldron actions " + Arrays.toString(cauldron.getActions()));
            cauldron.interact("Drink From");
            sleep(Calculations.random(3000, 5000));
            if (Dialogues.canContinue()) {
                Dialogues.spaceToContinue();
            }
            sleep(Calculations.random(3000, 5000));
        }
        return Calculations.random(500, 750);
    }
}
