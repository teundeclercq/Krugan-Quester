package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.Task;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;

public class UseItemOnItem extends AdvancedTask {
    protected String fishFood;
    protected String poison;
    protected boolean usedItemOnItem = false;
    public UseItemOnItem(Main main, String fishFood, String poison) {
        super(main);
        this.fishFood = fishFood;
        this.poison = poison;
    }

    public boolean isFinished() {
        return usedItemOnItem;
    }

    public int execute() {
        Item poison = Inventory.get(this.poison);
        Item fishFood = Inventory.get(this.fishFood);

        if (poison != null && fishFood != null) {
            poison.useOn(fishFood);
        }
        return Calculations.random(1500, 2200);
    }
}
