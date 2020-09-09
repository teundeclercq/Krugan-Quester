package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

public class GetMilkNode extends Node {
    public GetMilkNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public boolean validate() {
        return !main.getInventory().contains("Bucket of milk") &&
                main.getInventory().contains("Bucket");
    }

    @Override
    public void execute() {
        main.setStateClient("Getting bucket of milk");
        Area area = AreaProvider.CooksAssistant.cowArea;
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getCenter());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        } else {
            GameObject diaryCow = main.getGameObjects().closest("Dairy cow");
            if (diaryCow != null) {
                diaryCow.interact("Milk");
                MethodProvider.sleepUntil(() -> main.getInventory().contains("Bucket of milk"), Calculations.random(3000, 6000));
            }
        }
    }
}
