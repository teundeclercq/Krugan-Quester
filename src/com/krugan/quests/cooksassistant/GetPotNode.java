package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.items.GroundItem;

public class GetPotNode extends CookAssistant {
    public GetPotNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public int priority() {
        return 6;
    }

    @Override
    public boolean validate() {
        return !getItems() && isBucket() && !isPot() && !isEgg() && !isMilk() && !isGrain() && !isFlour();
    }

    @Override
    public int execute() {
        main.setStateClient("Getting pot");
        Area area = AreaProvider.CooksAssistant.cookArea;
        if (main.getInventory().contains("Pot")) {
            setPot(true);
            return (int) Calculations.nextGaussianRandom(400, 200);

        }
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getRandomTile());
        } else {
            GroundItem pot = main.getGroundItems().closest("Pot");
            if (pot != null) {
                pot.interact("Take");
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
            }
        }
        return (int) Calculations.nextGaussianRandom(400, 200);
    }
}
