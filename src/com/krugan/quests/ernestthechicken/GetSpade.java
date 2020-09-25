package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.walking.WalkToArea;
import com.krugan.util.walking.WalkToObject;
import com.krugan.util.walking.WalkToTile;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.item.GroundItems;

public class GetSpade extends AdvancedTask {
    public GetSpade(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.spadeRoom));
        this.tasks.add(new WalkToTile(main, GroundItems.closest("Spade").getTile()));
        this.tasks.add(new GetGroundItem(main, "Spade"));
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Spade");
    }
}
