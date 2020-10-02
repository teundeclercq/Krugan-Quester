package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class sleepTask extends AdvancedTask {

    protected int shortTime;
    protected int longTime;
    protected boolean doneSleeping = false;


    public sleepTask(Main main, int shortTime, int longTime) {
        super(main);
        this.shortTime = shortTime;
        this.longTime = longTime;
    }
    @Override
    public int execute() {
        sleep(Calculations.random(shortTime, longTime));
        doneSleeping = true;
        return Calculations.random(1750, 2250);
    }

    @Override
    public boolean isFinished() {
        return doneSleeping;
    }
}
