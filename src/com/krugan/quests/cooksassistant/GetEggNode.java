package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetEggNode extends AdvancedTask {
    public GetEggNode(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().contains("Egg");
    }

    @Override
    public void onFinish() {
        log("Quest task complete: GetEgg");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting Egg");
        if (main.getInventory().contains("Egg")) {
            return Calculations.random(3000, 6000);
        }
        Utility.TravelTo(AreaProvider.CooksAssistant.chickenArea);
        Utility.GetGroundItemIfNeeded("Egg");
        MethodProvider.sleepUntil(()-> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
        return Calculations.random(3000, 6000);
    }
}
