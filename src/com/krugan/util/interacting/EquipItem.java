package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.Task;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class EquipItem extends AdvancedTask {
    protected String item;
    protected String action;
    public EquipItem(Main main, String item, String action) {
        super(main);

        this.item = item;
        this.action = action;
    }

    @Override
    public int execute() {
        if (Inventory.contains(this.item)) {
            Inventory.interact(this.item, this.action);
            sleepUntil(() -> Equipment.contains(this.item), Calculations.random(800, 1250));
        }
        return Calculations.random(800, 1250);
    }

    @Override
    public boolean isFinished() {
        return Equipment.contains(this.item);
    }
}
