package com.krugan.quests.cooksassistant;


import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetGrainNode extends AdvancedTask {
    public GetGrainNode(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().contains("Grain");
    }

    @Override
    public void onFinish() {
        log("Quest task complete: GetGrain");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting grain for flour");
        Area area = AreaProvider.CooksAssistant.grainArea;
        if (main.getInventory().contains("Grain")) {
            return Calculations.random(3000, 6000);
        }
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        } else {
            GameObject Wheat = main.getGameObjects().closest("Wheat");
            if (Wheat!= null) {
                Wheat.interact("Pick");
                MethodProvider.sleepUntil(()-> main.getInventory().contains("Grain"), Calculations.random(3000, 5500));
            }
        }
        return Calculations.random(3000,6000);
    }
}
