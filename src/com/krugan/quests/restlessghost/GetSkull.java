package com.krugan.quests.restlessghost;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GetSkull extends AdvancedTask {
    public GetSkull(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getPlayerSettings().getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 4;
    }

    @Override
    public void onFinish() {
        log("Got the skull");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting the skull");

        Area area = AreaProvider.RestlessGhost.wizardTowerBasementLadder;
        Area basementLadder = AreaProvider.RestlessGhost.wizardTowerBasementLadderDownStairs;
        Area skullarea = AreaProvider.RestlessGhost.wizardTowerBasementSkull;
        try {
            TravelTo(area);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(300, 400));
        ClosestSpecifiedGameObjectInteract("Ladder", "Climb-down");
        try {
            TravelTo(skullarea);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(300, 400));
        ClosestSpecifiedGameObjectInteract("Altar", "Search");
        if (main.getDialogues().inDialogue() || main.getDialogues().canContinue()) {
            main.getDialogues().spaceToContinue();
            sleep(Calculations.random(200,300));
        }
        try {
            main.getWalking().toggleRun();
            TravelTo(basementLadder);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(300, 400));
        ClosestSpecifiedGameObjectInteract("Ladder", "Climb-up");
        sleep(Calculations.random(300, 400));
        return Calculations.random(3000,4500);
    }
}
