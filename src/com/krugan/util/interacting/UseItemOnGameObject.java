package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class UseItemOnGameObject extends AdvancedTask {
    protected String gameObject1;
    protected String item1;
    protected boolean usedItemOnGameObject = false;
    public UseItemOnGameObject(Main main, String gameObject1, String item1) {
        super(main);
        this.gameObject1 = gameObject1;
        this.item1 = item1;
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public int execute() {
        GameObject gameObject1 = GameObjects.closest(this.gameObject1);
        Item item1 = Inventory.get(this.item1);

        if (gameObject1 != null && item1 != null) {
            item1.useOn(gameObject1);
            sleep(Calculations.random(1000, 2000));
            this.usedItemOnGameObject = true;
        }
        return Calculations.random(1500, 2200);
    }
}
