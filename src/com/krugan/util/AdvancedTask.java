package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.dreambot.api.methods.MethodProvider.sleep;

public abstract class AdvancedTask implements Task {
    protected Main main;

    public AdvancedTask(Main main) {
        this.main = main;
    }

    public void TravelTo(Area destination) throws InterruptedException {
        if (!destination.contains(main.getLocalPlayer()) && !destination.contains(Walking.getDestination())) {
            Walking.walk(destination.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
    }

    public boolean ClimbClosest(int floors, boolean isUp, boolean isStairs) throws InterruptedException {
        int zStart = main.getLocalPlayer().getZ();
        int attempts = 0;
        int zGoal = main.getLocalPlayer().getZ() + floors;
        while ((main.getLocalPlayer().getZ() != zGoal) || attempts > ((floors >= 5) ? (10) : (5))) {
            GameObject climbable = GameObjects.closest((isStairs) ? ("Stairs") : ("Ladder"));
            if (climbable != null) {
                climbable.interact((isUp) ? ("Climb-up") : ("Climb-down"));
                MethodProvider.log((isUp) ? "Climb-up":"Climb-down");
                attempts++;
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
            }
        }
        return true;
    }

    public void talkToNpc(NPC npc, String talkAction, Integer option) {
        if (!Dialogues.inDialogue()) {
            npc.interact(talkAction);
            sleep(Calculations.random(1500, 2750));
        }
        while (Dialogues.canContinue()) {
            Dialogues.spaceToContinue();
            sleep(Calculations.random(2000, 2500));
        }
        if (Dialogues.getOptions().length > 0) {
            Dialogues.chooseOption(option);
            sleep(Calculations.random(1750, 3250));
        }
    }

    public boolean ClosestSpecifiedGameObjectInteract(String gameObjectName, String interactOption) {
        GameObject closestSpecifiedGameObject = GameObjects.closest(gameObjectName);
        if (closestSpecifiedGameObject != null) {
            closestSpecifiedGameObject.interact(interactOption);
            MethodProvider.sleep(Calculations.random(2000, 3000));
            return true;
        } else {
            return false;
        }
    }
}
