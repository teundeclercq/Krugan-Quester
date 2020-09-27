package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetPoison extends AdvancedTask {
    public GetPoison(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.poisonArea));
        this.tasks.add(new GetGroundItem(main, "Poison"));
    }

    @Override
    public String toString() {
        return "Getting poison";
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Poison");
    }

    public void onFinish() {
        log("Got the poison");
    }

}
