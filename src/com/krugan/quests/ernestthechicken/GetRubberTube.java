package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.walking.WalkToArea;
import com.krugan.util.walking.WalkToGroundItem;
import org.dreambot.api.methods.container.impl.Inventory;

public class GetRubberTube extends AdvancedTask {
    public GetRubberTube(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.ruberTubeArea));
        this.tasks.add(new WalkToGroundItem(main, "Rubber tube"));
        this.tasks.add(new GetGroundItem(main, "Rubber tube"));
    }


    @Override
    public String toString() {
        return "Getting rubber tube";
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Rubber tube");
    }
}
