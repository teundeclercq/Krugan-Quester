package com.krugan.quests.restlessghost;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.log;

public class GetGhostSpeak extends AdvancedTask {
    private boolean b = false;

    public GetGhostSpeak(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getPlayerSettings().getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 2;
    }

    @Override
    public void onFinish() {
        log("Got the Ghostspeak amulet");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting the ghostspeak amulet");
        Area area = AreaProvider.RestlessGhost.fatherUrayArea;
        try {
            TravelTo(area);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(300, 400));
        NPC father = main.getNpcs().closest("Father Urhney");
        Integer[] options = new Integer[] {2,1};
        if (father != null) {
            for (Integer opt: options) {
                TalkToNpc(father, "Talk-to", opt);
            }
        }
        if (main.getInventory().contains("Ghostspeak amulet")) {
            Item item = main.getInventory().get("Ghostspeak amulet");
            item.interact("Wear");
            sleep(Calculations.random(3000, 4000));
            b = true;
        }
        return Calculations.random(1500, 1750);
    }
}
