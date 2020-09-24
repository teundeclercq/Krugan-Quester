package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.log;

public class StartWitchPotion extends AdvancedTask {
    public StartWitchPotion(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) >= 1;
    }

    @Override
    public void onFinish() {
        log("Started the quest and cooked the meat.");
    }

    @Override
    public int execute() {
        main.setStateClient("starting witch potion.");

        Area hettyArea = AreaProvider.WitchPotion.hetty;
        try {
            TravelTo(hettyArea);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NPC hetty = NPCs.closest("Hetty");
        TalkToNpc(hetty, "Talk-to", 1);

        return Calculations.random(500, 1000);
    }

}
