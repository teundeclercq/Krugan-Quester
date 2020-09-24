package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class GetOnion extends AdvancedTask {
    public GetOnion(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Onion");
    }

    @Override
    public void onFinish() {
        log("Got the onion");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting the onion.");

        Area area = AreaProvider.WitchPotion.onionField;
            TravelTo(area);
        GameObject onion = GameObjects.closest(_onion -> _onion.getName().equals("Onion") && _onion.isOnScreen() && Map.canReach(_onion));

        if (onion != null) {
            onion.interact("Pick");
            sleepUntil(() -> Inventory.contains("Onion"), Calculations.random(1500, 2200));
        }

        return Calculations.random(2500, 3200);
    }
}
