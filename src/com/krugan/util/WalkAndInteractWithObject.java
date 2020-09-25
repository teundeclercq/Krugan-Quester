package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class WalkAndInteractWithObject extends AdvancedTask {
    protected boolean WalkAndInteractingWithObj = false;
    public WalkAndInteractWithObject(Main main, GameObject gameObject, String action) {
        super(main);
        if (gameObject != null) {
            this.tasks.add(new WalkToObject(main, gameObject));
            this.tasks.add(new InteractWithObject(main, gameObject, action));
            WalkAndInteractingWithObj = true;
            sleepUntil(() -> main.getLocalPlayer().isStandingStill() || !main.getLocalPlayer().isAnimating(), Calculations.random(2500, 3600));
        }
        super.execute();
    }

    public boolean isFinished() {
        return WalkAndInteractingWithObj;
    }

    public void onFinish() { log("Done with interacting with object"); }
}
