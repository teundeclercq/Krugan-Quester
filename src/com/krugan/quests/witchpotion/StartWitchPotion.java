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
        NPC hetty = NPCs.closest("Hetty");
        TalkToNpc(hetty, "Talk-to", 1);
        Area cookArea = AreaProvider.WitchPotion.cookArea;
        try {
            TravelTo(cookArea);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Item rawMeat = Inventory.get("Raw meat");
        Item cookedMeat = Inventory.get("Cooked meat");
        GameObject stove = GameObjects.closest("Range");
        if (stove != null) {
            if (rawMeat != null) {
                rawMeat.useOn(stove);
                return Calculations.random(500, 1000);
            }

            if (cookedMeat != null) {
                cookedMeat.useOn(stove);
                return Calculations.random(500, 1000);
            }
        }
        return Calculations.random(500, 1000);
    }

}
