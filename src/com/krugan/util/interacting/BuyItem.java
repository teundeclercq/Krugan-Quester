package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.Task;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;

import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class BuyItem extends AdvancedTask {
    protected String item;
    protected String action;
    public BuyItem(Main main, String item, String action) {
        super(main);
        this.item = item;
        this.action = action;
    }

    @Override
    public int execute() {
        if (Shop.isOpen()) {
            Shop.get(item).interact(action);
            sleepUntil(() -> Inventory.contains(item), Calculations.random(3000, 4000));
        }

        return Calculations.random(600, 900);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains(item);
    }
}
