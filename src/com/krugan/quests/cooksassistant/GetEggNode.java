package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;

public class GetEggNode extends CookAssistant {
    public GetEggNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public boolean validate() {
        return !getItems() && isPot() && !isGrain() && !isFlour() && !isEgg() && isBucket() &&
                !isMilk();
    }

    @Override
    public int execute() {
        main.setStateClient("Getting Egg");
        if (main.getInventory().contains("Egg")) {
            setEgg(true);
            return (int) Calculations.nextGaussianRandom(400, 200);

        }
        Utility.TravelTo(AreaProvider.CooksAssistant.chickenArea);
        Utility.GetGroundItemIfNeeded("Egg");
        MethodProvider.sleepUntil(()-> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
        return (int) Calculations.nextGaussianRandom(400, 200);
    }
}
