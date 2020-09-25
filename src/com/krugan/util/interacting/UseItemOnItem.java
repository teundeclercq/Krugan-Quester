package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class UseItemOnItem extends AdvancedTask {
    protected String item1;
    protected String item2;
    protected boolean usedItemOnItem = false;
    public UseItemOnItem(Main main, String item1, String item2) {
        super(main);
        this.item1 = item1;
        this.item2 = item2;

    }

    public boolean isFinished() {
        return usedItemOnItem;
    }

    public int execute() {
        Item item2 = Inventory.get(this.item2);
        Item item1 = Inventory.get(this.item1);

        if (item2 != null && item1 != null) {
            item2.useOn(item1);
            sleep(Calculations.random(1000, 2000));
            this.usedItemOnItem = true;
        }
        return Calculations.random(1500, 2200);
    }
}
