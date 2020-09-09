package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

public class GetGrainNode extends Node {
    public GetGrainNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public boolean validate() {
        return !main.getInventory().contains("Grain") &&
                !main.getInventory().contains("Pot of Flour");
    }

    @Override
    public void execute() {
        main.setStateClient("Getting grain for flour");
        Area area = AreaProvider.CooksAssistant.grainArea;

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
    }
}
