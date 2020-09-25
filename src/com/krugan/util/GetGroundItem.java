package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.wrappers.items.GroundItem;

public class GetGroundItem extends AdvancedTask {
    protected String groundItem;
    public GetGroundItem(Main main, String groundItem) {
        super(main);
        this.groundItem = groundItem;
    }

    public int execute() {
        while (!Inventory.contains(groundItem)) {
            GroundItem item = GroundItems.closest(groundItem);
            if (item != null) {
                item.interact("Take");
                MethodProvider.sleepWhile(() -> !Inventory.contains(groundItem), Calculations.random(4000, 6000));
            }
        }
        return Calculations.random(300, 500);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains(groundItem);
    }
}
