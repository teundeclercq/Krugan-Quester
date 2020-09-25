package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.WalkAndInteractWithObject;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.dreambot.api.methods.MethodProvider.*;

public class GetOil extends AdvancedTask {
    private final GameObject leverA = AreaProvider.ErnestTheChick.leverA;
    private final GameObject leverB = AreaProvider.ErnestTheChick.leverB;
    private final GameObject leverC = AreaProvider.ErnestTheChick.leverC;
    private final GameObject leverD = AreaProvider.ErnestTheChick.leverD;
    private final GameObject leverE = AreaProvider.ErnestTheChick.leverE;
    private final GameObject leverF = AreaProvider.ErnestTheChick.leverF;

    private final Area door1 = AreaProvider.ErnestTheChick.door1;
    private final Area door2 = AreaProvider.ErnestTheChick.door2;
    private final Area door3 = AreaProvider.ErnestTheChick.door3;
    private final Area door4 = AreaProvider.ErnestTheChick.door4;
    private final Area door5 = AreaProvider.ErnestTheChick.door5;
    private final Area door6 = AreaProvider.ErnestTheChick.door6;
    private final Area door7 = AreaProvider.ErnestTheChick.door7;
    private final Area door8 = AreaProvider.ErnestTheChick.door8;
    private final Area door9 = AreaProvider.ErnestTheChick.door9;

    private final Area area1 = AreaProvider.ErnestTheChick.area1;
    private final Area area2 = AreaProvider.ErnestTheChick.area2;
    private final Area area3 = AreaProvider.ErnestTheChick.area3;
    private final Area area4 = AreaProvider.ErnestTheChick.area4;
    private final Area area5 = AreaProvider.ErnestTheChick.area5;
    private final Area area6 = AreaProvider.ErnestTheChick.area6;
    private final Area area7 = AreaProvider.ErnestTheChick.area7;
    private final Area area8 = AreaProvider.ErnestTheChick.area8;

    private final Tile tile1 = AreaProvider.ErnestTheChick.tile1;
    private final Tile tile2 = AreaProvider.ErnestTheChick.tile2;
    private final Tile tile3 = AreaProvider.ErnestTheChick.tile3;
    private final Tile tile4 = AreaProvider.ErnestTheChick.tile4;
    private final Tile tile5 = AreaProvider.ErnestTheChick.tile5;
    private final Tile tile6 = AreaProvider.ErnestTheChick.tile6;
    private final Tile tile7 = AreaProvider.ErnestTheChick.tile7;
    private final Tile tile8 = AreaProvider.ErnestTheChick.tile8;
    private final Tile tile9 = AreaProvider.ErnestTheChick.tile9;
    private final Tile tile10 = AreaProvider.ErnestTheChick.tile10;
    private final Tile tile11 = AreaProvider.ErnestTheChick.tile11;
    private final Tile tile12 = AreaProvider.ErnestTheChick.tile12;
    private final Tile tile13 = AreaProvider.ErnestTheChick.tile13;
    private final Tile tile14 = AreaProvider.ErnestTheChick.tile14;
    private final Tile tile15 = AreaProvider.ErnestTheChick.tile15;

    List<Tile> tiles = new LinkedList<>();
    List<GameObject> levers = new LinkedList<>();
    public GetOil(Main main) {
        super(main);

        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile5);
        tiles.add(tile7);
        tiles.add(tile9);
        tiles.add(tile11);
        tiles.add(tile12);
        tiles.add(tile10);
        tiles.add(tile9);
        tiles.add(tile13);
        tiles.add(tile4);
        tiles.add(tile15);

        levers.add(leverB);
        levers.add(leverA);
        levers.add(leverD);
        levers.add(leverB);
        levers.add(leverA);
        levers.add(leverE);
        levers.add(leverF);
        levers.add(leverC);
        levers.add(leverE);
        this.tasks.add(new WalkAndInteractWithObject(main, leverA, "pull"));
        this.tasks.add(new WalkAndInteractWithObject(main, leverB, "pull"));
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Oil");
    }

    @Override
    public void onFinish() {
        log("Got the oil");
    }

    public int execute() {
        Iterator tileToGo = tiles.iterator();
        Iterator leverToGo = levers.iterator();

        GameObject lever;

        TravelToGameObject(leverB);
        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
        ClosestSpecifiedGameObjectInteract("Lever B", "pull");
        sleep(Calculations.random(2500, 3500));


        TravelToGameObject(leverA);
        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
        ClosestSpecifiedGameObjectInteract("Lever A", "pull");
        sleep(Calculations.random(2500, 3500));


//        TravelToTile(tile1);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Door", "Open");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverD);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever D", "pull");
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
//        ClosestSpecifiedGameObjectInteract("Lever B", "pull");
//        sleep(Calculations.random(2500, 3500));
//
//
//        TravelToGameObject(leverA);
//        sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 5000));
//        ClosestSpecifiedGameObjectInteract("Lever A", "pull");
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

        return Calculations.random(1250, 1650);
    }
}
