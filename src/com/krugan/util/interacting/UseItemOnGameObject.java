package com.krugan.util.interacting;

import c_utilities.CTime;
import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class UseItemOnGameObject extends AdvancedTask implements ChatListener {
    protected String gameObject1;
    protected String item1;
    protected String gameMessage;
    protected boolean usedItemOnGameObject = false;
    public UseItemOnGameObject(Main main, String gameObject1, String item1, String gameMessage) {
        super(main);
        this.gameObject1 = gameObject1;
        this.item1 = item1;
        this.gameMessage = gameMessage;

    }

    @Override
    public boolean isFinished() {
        return usedItemOnGameObject;
    }

    @Override
    public int execute() {
        GameObject gameObject1 = GameObjects.closest(this.gameObject1);
        Item item1 = Inventory.get(this.item1);

        if (gameObject1 != null && item1 != null) {
            item1.useOn(gameObject1);
            sleep(Calculations.random(1000, 2000));
        }
        return Calculations.random(1500, 2200);

    }

    @Override
    public void onGameMessage(Message message) {
        if (this.gameMessage != null) {
            if (message.getMessage().equals(this.gameMessage)) {
                sleep(Calculations.random(3000, 4000));
                this.usedItemOnGameObject = true;
            }
        }
    }
}
