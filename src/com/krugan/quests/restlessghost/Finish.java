package com.krugan.quests.restlessghost;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class Finish extends AdvancedTask {

    public Finish(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getPlayerSettings().getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 5;
    }

    @Override
    public void onFinish() {
        log("Finished the quest");
    }

    @Override
    public int execute() {
        main.setStateClient("Finishing restless ghost.");
//        Area basementLadder = AreaProvider.RestlessGhost.wizardTowerBasementLadderDownStairs;
//
//        try {
//            main.getWalking().toggleRun();
//            TravelTo(basementLadder);
//        } catch (InterruptedException e) {
//            log(e.getMessage());
//        }
//        sleep(Calculations.random(300, 400));
//        ClosestSpecifiedGameObjectInteract("Ladder", "Climb-up");
//        sleep(Calculations.random(300, 400));

        Area area = AreaProvider.RestlessGhost.ghostGraveYard;
        try {
            TravelTo(area);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(300, 400));
        ClosestSpecifiedGameObjectInteract("Coffin", "Open");
        NPC ghost = main.getNpcs().closest("Restless Ghost");
        if (ghost != null) {
           Item item = main.getInventory().get("Ghost's skull");
           GameObject gameObject = main.getGameObjects().closest("Coffin");
            if (gameObject != null) {
                item.useOn(gameObject);
            }
        }
        return Calculations.random(450, 675);
    }
}
