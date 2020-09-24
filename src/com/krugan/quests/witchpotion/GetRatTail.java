package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;

public class GetRatTail extends AdvancedTask {
    public GetRatTail(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Rat's tail");
    }

    @Override
    public void onFinish() {

    }

    @Override
    public int execute() {
        main.setStateClient("Getting the rat tail.");
        Area ratArea = AreaProvider.WitchPotion.ratArea;
            TravelTo(ratArea);
        KillNpcAndPickUpItem("Rat", ratArea, "Rat's tail");
        return Calculations.random(2500, 3000);
    }
}
