package com.krugan.quests.restlessghost;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;


public class WalkToGraveyard extends AdvancedTask {
    public WalkToGraveyard(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getPlayerSettings().getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 3;
    }

    @Override
    public void onFinish() {
        log("Talked to the ghost");
    }

    @Override
    public int execute() {
        main.setStateClient("Walking to graveyard.");

        Area area = AreaProvider.RestlessGhost.ghostGraveYard;
        try {
            TravelTo(area);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(300, 400));
        //todo if coffin is open use option search else use option Open.
        ClosestSpecifiedGameObjectInteract("Coffin", "Open");
        sleep(Calculations.random(3500, 5500));
        NPC npc = main.getNpcs().closest("Restless ghost");
        if (npc != null) {
            TalkToNpc(npc, "Talk-to", 1);
        }
        return Calculations.random(3500, 4450);
    }
}
