package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class CookingMeat extends AdvancedTask {
    public CookingMeat(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Burnt meat");
    }

    @Override
    public void onFinish() {
        log("Got the burnt meat");
    }

    @Override
    public int execute() {
        main.setStateClient("Cooking until burnt meat");
        Area cookArea = AreaProvider.WitchPotion.cookArea;
        try {
            TravelTo(cookArea);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Item rawMeat = Inventory.get("Raw beef");
        Item cookedMeat = Inventory.get("Cooked meat");
        GameObject stove = GameObjects.closest(range -> range.isOnScreen() && range.getName().equals("Range"));
        if (stove != null) {
            if (rawMeat != null) {
                rawMeat.useOn(stove);
                sleepUntil(() -> !main.getLocalPlayer().isAnimating() && main.getLocalPlayer().isStandingStill(), Calculations.random(5000, 8000));
                return Calculations.random(400, 600);
            }

            if (cookedMeat != null) {
                cookedMeat.useOn(stove);
                sleepUntil(() -> !main.getLocalPlayer().isAnimating() && main.getLocalPlayer().isStandingStill(), Calculations.random(5000, 8000));
                return Calculations.random(400, 600);
            }
        }
        return Calculations.random(400, 600);


    }
}
