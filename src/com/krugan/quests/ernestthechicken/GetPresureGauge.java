package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.InteractWithObject;
import com.krugan.util.interacting.UseItemOnGameObject;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.container.impl.Inventory;

public class GetPresureGauge extends AdvancedTask {
    public GetPresureGauge(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.fountainArea));
        this.tasks.add(new UseItemOnGameObject(main, "Fountain", "Poisoned fish food"));
        this.tasks.add(new InteractWithObject(main, "Fountain", "Search"));
    }


    @Override
    public boolean isFinished() {
        return Inventory.contains("Pressure gauge");
    }
}
