package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class WalkToObject extends AdvancedTask {
    protected GameObject gameObject;
    protected boolean walkedToObject = false;

    public WalkToObject(Main main, GameObject gameObject) {
        super(main);
        this.gameObject = gameObject;
    }

    public boolean isFinished() {
        return walkedToObject;
    }

    public int execute() {
        if (gameObject.exists()) {
            if (Walking.canWalk(gameObject.getTile())) {
                Walking.walk(gameObject.getTile());
                walkedToObject = true;
            }
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 3600));
        }
        return Calculations.random(200, 400);
    }


}
