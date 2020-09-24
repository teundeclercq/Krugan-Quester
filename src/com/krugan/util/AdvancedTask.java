package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

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

    public void TalkToNpc(NPC npc, String talkAction, Integer option) {
        if (!Dialogues.inDialogue()) {
            npc.interact(talkAction);
            sleep(Calculations.random(1500, 2750));
        }
        while (Dialogues.canContinue()) {
            Dialogues.spaceToContinue();
            sleep(Calculations.random(2000, 2500));
        }
        if (option != 0) {
            if (Dialogues.getOptions().length > 0) {
                Dialogues.chooseOption(option);
                sleep(Calculations.random(1750, 3250));
            }
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

    public int KillNpcAndPickUpItem(String npcToKill, Area areaToKillNpc, String... itemToPickUp) {

        NPC _npcToKill = NPCs.closest(npc  -> npc.getName().equals(npcToKill) && !npc.isInCombat() && npc.isOnScreen() && Map.canReach(npc));

        GroundItem groundItem = GroundItems.closest(itemToPickUp);
        if (!Inventory.contains(itemToPickUp)) {
            if (groundItem != null) {
                Walking.walk(groundItem);
                sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(5000, 8000));
                groundItem.interact("Take");
                sleepUntil(() -> Inventory.contains(groundItem) && main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 3000));
            }
        }
        if (_npcToKill != null) {
            if (!main.getLocalPlayer().isInCombat() && main.getLocalPlayer().getInteractingCharacter() == null) {
                Walking.walk(_npcToKill);
                sleep(Calculations.random(300, 400));
                Camera.mouseRotateToEntity(_npcToKill);

                if (_npcToKill.interact("Attack")) {
                    sleepUntil(() -> main.getLocalPlayer().isInCombat() || main.getLocalPlayer().getInteractingCharacter() != null, Calculations.random(2000, 3500));
                    sleep(Calculations.random(2000, 4000));
                }
            }
            return Calculations.random(2000, 3500);
        } else {
            Walking.walk(areaToKillNpc.getRandomTile());
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
        }

        if (Dialogues.canContinue()) {
            Dialogues.spaceToContinue();
        }

        return Calculations.random(1500,2250);

    }
}
