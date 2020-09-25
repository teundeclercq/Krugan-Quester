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
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.dreambot.api.methods.MethodProvider.*;

public abstract class AdvancedTask implements Task {
    protected Main main;
    protected List<Task> tasks;

    public AdvancedTask(Main main) {
        this.main = main;
        this.tasks = new LinkedList<>();
    }

    public void AddTask(Task task) {
        this.tasks.add(task);
    }

    public int execute() {
        Iterator taskIterator = this.tasks.iterator();

        while(taskIterator.hasNext()) {
            Task task = (Task)taskIterator.next();
            if (!task.isFinished()) {
                task.execute();
            }

            task.onFinish();
            log("Removing " + task.getClass().toString());
            taskIterator.remove();
        }
        return Calculations.random(200,400);
    }
    public boolean isFinished() {
        return this.tasks.isEmpty();
    }

    public void onFinish() {

    }

    public void GetGroundItemIfNeeded(String itemToGet) {
        while (!Inventory.contains(itemToGet)) {
            GroundItem item = GroundItems.closest(itemToGet);
            if (item != null) {
                item.interact("Take");
                MethodProvider.sleepWhile(() -> !Inventory.contains(itemToGet), Calculations.random(35000, 40000));
            }
        }
    }

    public void TravelToTile(Tile tile) {
        if (!main.getLocalPlayer().getTile().equals(tile)) {
            Walking.walk(tile);
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
    }


    public void TravelTo(Area destination) {
        if (!destination.contains(main.getLocalPlayer()) && !destination.contains(Walking.getDestination())) {
            Walking.clickTileOnMinimap(destination.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
    }
    public void TravelToNearest(Area destination) {
        if (!destination.contains(main.getLocalPlayer()) && !destination.contains(Walking.getDestination())) {
            Walking.walk(destination.getNearestTile(main.getLocalPlayer()));
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
    }

    public void TravelToGameObject(GameObject obj) {
        if (obj.exists()) {
            Walking.walk(obj.getTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 3600));
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
                log((isUp) ? "Climb-up":"Climb-down");
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
            Camera.mouseRotateToEntity(closestSpecifiedGameObject);
            sleep(Calculations.random(400,600));
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
