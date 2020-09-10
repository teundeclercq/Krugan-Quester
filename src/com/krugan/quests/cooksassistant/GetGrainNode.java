package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

public class GetGrainNode extends CookAssistant {
    public GetGrainNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean validate() {
        return !getItems() && isPot() && !isGrain() && !isFlour() && isEgg() && isBucket() &&
                isMilk();
    }

    @Override
    public int execute() {
        main.setStateClient("Getting grain for flour");
        Area area = AreaProvider.CooksAssistant.grainArea;
        if (main.getInventory().contains("Grain")) {
            setMilk(true);
            return (int) Calculations.nextGaussianRandom(400, 200);
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
        return (int) Calculations.nextGaussianRandom(400, 200);
    }
}
