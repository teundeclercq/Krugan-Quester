package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.interacting.UseItemOnItem;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.container.impl.Inventory;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetFishFood extends AdvancedTask {
    public GetFishFood(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.fishFoodArea));
        this.tasks.add(new GetGroundItem(main, "Fish food"));
        this.tasks.add(new UseItemOnItem(main, "Fish food", "Poison", "You poison the fish food."));
    }

    @Override
    public String toString() {
        return "Getting fish food";
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Poisoned fish food");
    }

    @Override
    public void onFinish() {
        log("Got the poisoned fish food");
    }
}
