package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.Node;
import org.dreambot.api.methods.Calculations;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class ErnestTheChicken extends Node {
    public ErnestTheChicken(Main main) {
        super(main);
    }

    @Override
    public void execute() {
        this.main.addTasks(new GetOil(main));
        sleep(Calculations.random(1000, 4000));
    }

    @Override
    public boolean validate() {
        return this.main.tasks.isEmpty();
    }
}
