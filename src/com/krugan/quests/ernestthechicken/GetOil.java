package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.*;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.walking.WalkToObject;
import com.krugan.util.walking.WalkToTile;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;

import static org.dreambot.api.methods.MethodProvider.*;

public class GetOil extends AdvancedTask {
    public GetOil(Main main) {
        super(main);
        this.tasks.add(new InteractWithObject(main, "Lever B", "Pull"));

        this.tasks.add(new InteractWithObject(main, "Lever A", "Pull"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile1));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToObject(main, AreaProvider.ErnestTheChick.leverD));
        this.tasks.add(new InteractWithObject(main, "Lever D", "Pull"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile2));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToObject(main, AreaProvider.ErnestTheChick.leverB));
        this.tasks.add(new InteractWithObject(main, "Lever B", "Pull"));

        this.tasks.add(new WalkToObject(main, AreaProvider.ErnestTheChick.leverA));
        this.tasks.add(new InteractWithObject(main, "Lever A", "Pull"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile3));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile5));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile7));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new InteractWithObject(main, "Lever E", "Pull"));

        this.tasks.add(new InteractWithObject(main, "Lever F", "Pull"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile9));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile11));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToObject(main, AreaProvider.ErnestTheChick.leverC));
        this.tasks.add(new InteractWithObject(main, "Lever C", "Pull"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile12));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile10));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToObject(main, AreaProvider.ErnestTheChick.leverE));
        this.tasks.add(new InteractWithObject(main, "Lever E", "Pull"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile9));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile13));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile4));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open"));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile15));
        this.tasks.add(new InteractWithObject(main, "Door", "Open"));

//        this.tasks.add(new WalkToTile(main, GroundItems.closest("Oil can").getTile()));
        this.tasks.add(new GetGroundItem(main, "Oil can"));

    }

    public void taskState() {
        main.setStateClient("getting oil");
    }

    public boolean isFinished() {
        return Inventory.contains("Oil can");
    }

    public void onFinish() {
        log("Got the oil");
    }

//    public int execute() {
//        Iterator tileToGo = tiles.iterator();
//        Iterator leverToGo = levers.iterator();
//
//        GameObject lever;
//
//        TravelToGameObject(leverB);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever B", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverA);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever A", "Pull");
//        sleep(Calculations.random(2500, 3500));


//        TravelToTile(tile1);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverD);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever D", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToTile(tile2);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//        TravelToGameObject(leverB);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever B", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverA);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever A", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//        TravelToTile(tile3);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//
//        TravelToTile(tile5);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//
//        TravelToTile(tile7);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//        ClosestSpecifiedGameObjectInteract("Lever E", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//        ClosestSpecifiedGameObjectInteract("Lever F", "Pull");
//        sleep(Calculations.random(3000, 3500));
//
//        TravelToTile(tile9);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//
//        TravelToTile(tile11);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverC);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Lever C", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//        TravelToTile(tile12);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//        TravelToTile(tile10);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverE);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Lever E", "Pull");
//        sleep(Calculations.random(2500, 3500));
//
//        TravelToTile(tile9);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//        TravelToTile(tile13);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//        TravelToTile(tile4);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//
//        TravelToTile(tile15);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 7000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelTo(AreaProvider.ErnestTheChick.oilCan);
//
//        GroundItem oil = GroundItems.closest("Oil can");
//
//        if (oil != null) {
//            oil.interact("Take");
//            sleep(Calculations.random(1250, 1650));
//        }

//        return Calculations.random(1250, 1650);
//    }
}
