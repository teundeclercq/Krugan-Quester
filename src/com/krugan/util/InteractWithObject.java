package com.krugan.util;

import c_utilities.CTime;
import com.krugan.quester.Main;
import org.dreambot.api.input.event.impl.InteractionEvent;
import org.dreambot.api.input.event.impl.InteractionSetting;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.interact.Interactable;

import static org.dreambot.api.methods.MethodProvider.*;

public class InteractWithObject extends AdvancedTask {
    protected String obj;
    protected GameObject gameObject;
    protected String action;
    protected boolean interactedWithObject = false;
    public InteractWithObject(Main main, String gameObject, String action) {
        super(main);
        this.action = action;
        this.obj = gameObject;
    }

    public boolean isFinished() {
        return interactedWithObject;
    }

    public int execute() {
        main.setStateClient("Interacting with object.");
        while (!main.getLocalPlayer().isAnimating() || main.getLocalPlayer().isMoving()) {
            if (InteractingWithObject(obj, action)) {
                sleep(Calculations.random(3000, 6000));
                break;
            }
        }
        interactedWithObject = true;

        return Calculations.random(2000, 3000);
    }

    private boolean InteractingWithObject(String gameObjectName, String interactOption) {
        GameObject closestSpecifiedGameObject = GameObjects.closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            if (closestSpecifiedGameObject.hasAction(interactOption)) {
                Camera.rotateToEntity(closestSpecifiedGameObject);
                sleep(Calculations.random(400, 600));
                closestSpecifiedGameObject.interact(interactOption);
                sleep(Calculations.random(400, 600));
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }
}
