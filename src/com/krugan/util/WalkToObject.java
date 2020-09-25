package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class WalkToObject extends AdvancedTask {
    protected GameObject gameObject;

    public WalkToObject(Main main, GameObject gameObject) {
        super(main);
        this.gameObject = gameObject;
        this.execute();
    }

    public int execute() {
        main.setStateClient("Walking to object.");
        Walking.walk(gameObject);
        sleep(Calculations.random(200, 400));
        if (gameObject.getTile().distance() < 1) {
            this.isFinished();
        }
        return Calculations.random(200, 400);
    }

    public boolean isFinished() {
        return main.getLocalPlayer().isStandingStill();
    }
}
