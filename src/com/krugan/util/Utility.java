package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

public class Utility {
    private static Utility instance = null;
    private static Main main;
    public static Utility getInstance(Main mainIn) {
        if (instance == null) {
            instance = new Utility();
            main = mainIn;
        }
        return instance;
    }
    private Utility() {
    }

    public static void GetGroundItemIfNeeded(String itemToGet) {
        while (!main.getInventory().contains(itemToGet)) {
            GroundItem item = main.getGroundItems().closest(itemToGet);
            if (item != null) {
                item.interact("Take");
                MethodProvider.sleepWhile(() -> !main.getInventory().contains(itemToGet), Calculations.random(35000, 40000));
            }
        }
    }
    public static void TravelTo(Area destination) {
        while (!destination.contains(main.getLocalPlayer()) && !destination.contains(main.getWalking().getDestination())) {
            main.getWalking().walk(destination.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
    }

    public static boolean ClimbClosest(int floors, boolean isUp, boolean isStairs) {
        int zStart = main.getLocalPlayer().getZ();
        int attempts = 0;
        int zGoal = main.getLocalPlayer().getZ() + floors;
        while ((main.getLocalPlayer().getZ() != zGoal) || attempts > ((floors >= 5) ? (10) : (5))) {
            GameObject climbable = main.getGameObjects().closest((isStairs == true) ? ("Stairs") : ("Ladder"));
            if (climbable != null) {
                climbable.interact((isUp == true) ? ("Climb-up") : ("Climb-down"));
                MethodProvider.log((isUp) ? "Climb-up":"Climb-down");
                attempts++;
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
            }
        }        return true;
    }
    public static boolean ClosestSpecifiedGameObjectInteract(String gameObjectName, String interactOption) {
        GameObject closestSpecifiedGameObject = main.getGameObjects().closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            closestSpecifiedGameObject.interact(interactOption);
            MethodProvider.sleep(Calculations.random(2000, 3000));
            return true;
        } else {
            return false;
        }
    }

    public static boolean ClosestSpecifiedGameObjectInteractConditional(String gameObjectName, String interactOption, String interactConditionalOption, boolean hasOption) {
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
