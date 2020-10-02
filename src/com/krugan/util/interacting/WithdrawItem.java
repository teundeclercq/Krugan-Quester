package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class WithdrawItem extends AdvancedTask {
    protected String item;
    protected Integer amount;
    public WithdrawItem(Main main, String item, Integer amount) {
        super(main);
        this.amount = amount;
        this.item = item;
    }

    @Override
    public int execute() {
        if (Bank.isOpen()) {
            sleepUntil(Bank::isOpen, Calculations.random(800, 1250));

            if (Bank.contains(item)) {
                Bank.withdraw(item, amount);
                sleepUntil(() -> Inventory.contains(item), Calculations.random(880, 1450));
            } else {
                log("Bank does not contain item: "+ item);
                main.stop();
            }
        }

        return Calculations.random(800, 1250);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains(item);
    }
}
