package com.krugan.quests.witchpotion;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class GetEyeOfNewt extends AdvancedTask {
    public GetEyeOfNewt(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Eye of newt");
    }

    @Override
    public void onFinish() {
        log("Got the eye of newt.");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting eye of newt.");

        Area area = AreaProvider.WitchPotion.eyeofNewtShop;
        Area bankArea = AreaProvider.WitchPotion.draynorBank;

        if (!Inventory.contains(coins -> coins.getName().equals("Coins") && coins.getAmount() >= 3)) {
                TravelTo(bankArea);
            GameObject bankBooth = GameObjects.closest(bb -> bb != null && bb.getName().equals("Bank booth"));
            bankBooth.interact("Bank");
            sleepUntil(Bank::isOpen, Calculations.random(3000, 4000));

            if (Bank.isOpen()) {
                if (!Inventory.contains("Coins")) {
                    Bank.withdraw("Coins", 3);
                    sleepUntil(() -> !Inventory.contains("Coins"), Calculations.random(3000, 6000));
                }
                Bank.close();
            }
        }


            TravelTo(area);

        NPC betty = NPCs.closest("Betty");

        if (betty != null) {
            betty.interact("Trade");
            if (Shop.isOpen()) {
                Shop.get("Eye of newt").interact("Buy 1");
                sleepUntil(() -> Inventory.contains("Eye of newt"), Calculations.random(3000, 5000));
            }
        }

        return Calculations.random(2000, 3000);
    }
}
