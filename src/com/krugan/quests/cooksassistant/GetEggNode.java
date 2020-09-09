package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;

public class GetEggNode extends Node {
    public GetEggNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public boolean validate() {
        return !main.getInventory().contains("Egg");
    }

    @Override
    public void execute() {
        main.setStateClient("Getting Egg");
        Utility.TravelTo(AreaProvider.CooksAssistant.chickenArea);
        Utility.GetGroundItemIfNeeded("Egg");
        MethodProvider.sleepUntil(()-> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
    }
}
