package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.walking.WalkToArea;
import com.krugan.util.walking.WalkToObject;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;

public class GetKey extends AdvancedTask {
    public GetKey(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.compostArea));
        this.tasks.add(new WalkToObject(main, GameObjects.closest("Compost heap")));
        this.tasks.add(new InteractWithObject(main, "Compost heap", "Search", "...and find a small key."));
    }


    @Override
    public String toString() {
        return "Getting the key";
    }

    public boolean isFinished() {
        return Inventory.contains("Key");
    }
}
