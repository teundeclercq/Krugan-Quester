package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class InteractWithObject extends AdvancedTask {
    protected GameObject object;
    protected String action;
    protected boolean clickedOnObject;
    public InteractWithObject(Main main, GameObject gameObject, String action) {
        super(main);
        this.action = action;
        this.object = gameObject;
        this.execute();
    }

    public boolean isFinished() {
        return clickedOnObject;
    }

    public int execute() {
        main.setStateClient("Interacting with object.");
        if (object != null && action != null) {
            object.interact(action);
            sleep(Calculations.random(1000, 2000));
            clickedOnObject = true;
        }
        return Calculations.random(1000, 2000);
    }
}
