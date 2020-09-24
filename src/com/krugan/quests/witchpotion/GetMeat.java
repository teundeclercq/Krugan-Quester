package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class GetMeat extends AdvancedTask {
    public GetMeat(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Raw beef");
    }

    @Override
    public void onFinish() {

    }

    @Override
    public int execute() {
        main.setStateClient("Getting the meat.");

        Area area = AreaProvider.WitchPotion.cowArea;
        try {
            TravelTo(area);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KillNpcAndPickUpItem("Cow", area, "Raw beef");
        return Calculations.random(1500,2250);
    }
}
