package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

public class Utility {
    private static Main main;
    private Utility() {
    }

    public void GetGroundItemIfNeeded(String itemToGet) {
        while (!main.getInventory().contains(itemToGet)) {
            GroundItem item = main.getGroundItems().closest(itemToGet);
            if (item != null) {
                item.interact("Take");
                MethodProvider.sleepWhile(() -> !main.getInventory().contains(itemToGet), Calculations.random(35000, 40000));
            }
        }
    }



    public boolean ClosestSpecifiedGameObjectInteract(String gameObjectName, String interactOption) {
        GameObject closestSpecifiedGameObject = main.getGameObjects().closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            closestSpecifiedGameObject.interact(interactOption);
            MethodProvider.sleep(Calculations.random(2000, 3000));
            return true;
        } else {
            return false;
        }
    }

    public boolean ClosestSpecifiedGameObjectInteractConditional(String gameObjectName, String interactOption, String interactConditionalOption, boolean hasOption) {
        GameObject closestSpecifiedGameObject = main.getGameObjects().closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            if (hasOption) {
                if (closestSpecifiedGameObject.hasAction(interactConditionalOption)) {
                    closestSpecifiedGameObject.interact(interactOption);
                    MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
                    return true;
                }
            } else {
                if (!closestSpecifiedGameObject.hasAction(interactConditionalOption)) {
                    closestSpecifiedGameObject.interact(interactOption);
                    MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
                    return true;
                }
            }
        }
        return false;
    }


}
