package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GetFishFood extends AdvancedTask {
    public GetFishFood(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Poisoned fish food");
    }

    @Override
    public void onFinish() {
        log("Got the poisoned fish food");
    }

    @Override
    public int execute() {
        Area area = AreaProvider.ErnestTheChick.fishFoodArea;
            TravelTo(area);

        GetGroundItemIfNeeded("Fish food");

        if (Inventory.contains("Fish food") && Inventory.contains("Poison")) {
            Item fishFood = Inventory.get("Fish food");
            Item poison = Inventory.get("Poison");
            fishFood.useOn(poison);
            sleep(Calculations.random(1500, 1750));
        }
        return Calculations.random(1500, 1750);
    }
}
