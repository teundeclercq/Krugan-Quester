package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GetOil extends AdvancedTask {
    private Area leverA = AreaProvider.ErnestTheChick.leverA;
    private Area leverB = AreaProvider.ErnestTheChick.leverB;
    private Area leverC = AreaProvider.ErnestTheChick.leverC;
    private Area leverD = AreaProvider.ErnestTheChick.leverD;
    private Area leverE = AreaProvider.ErnestTheChick.leverE;
    private Area leverF = AreaProvider.ErnestTheChick.leverF;

    private Area door1 = AreaProvider.ErnestTheChick.door1;
    private Area door2 = AreaProvider.ErnestTheChick.door2;
    private Area door3 = AreaProvider.ErnestTheChick.door3;
    private Area door4 = AreaProvider.ErnestTheChick.door4;
    private Area door5 = AreaProvider.ErnestTheChick.door5;
    private Area door6 = AreaProvider.ErnestTheChick.door6;
    private Area door7 = AreaProvider.ErnestTheChick.door7;
    private Area door8 = AreaProvider.ErnestTheChick.door7;
    private Area door9 = AreaProvider.ErnestTheChick.door9;

    public GetOil(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains("Oil");
    }

    @Override
    public void onFinish() {
        log("Got the oil");
    }

    @Override
    public int execute() {
        TravelToNearest(leverB);
        ClosestSpecifiedGameObjectInteract("Lever B", "pull");
        TravelToNearest(leverA);
        ClosestSpecifiedGameObjectInteract("Lever A", "pull");

        TravelToNearest(door1);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(leverD);
        ClosestSpecifiedGameObjectInteract("Lever D", "pull");

        TravelToNearest(door1);

        sleep(Calculations.random(3000, 4000));

        TravelToNearest(leverB);
        ClosestSpecifiedGameObjectInteract("Lever B", "pull");

        TravelToNearest(leverA);
        ClosestSpecifiedGameObjectInteract("Lever A", "pull");

        TravelToNearest(door3);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door4);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door5);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(leverE);
        ClosestSpecifiedGameObjectInteract("Lever E", "Pull");

        TravelToNearest(leverF);
        ClosestSpecifiedGameObjectInteract("Lever F", "Pull");

        TravelToNearest(door6);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door7);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(leverC);
        ClosestSpecifiedGameObjectInteract("Lever C", "Pull");

        TravelToNearest(door7);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door6);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(leverE);
        ClosestSpecifiedGameObjectInteract("Lever E", "Pull");

        TravelToNearest(door6);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door8);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door3);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelToNearest(door9);
        ClosestSpecifiedGameObjectInteract("Door", "Open");

        TravelTo(AreaProvider.ErnestTheChick.oilCan);

        return Calculations.random(1250, 1650);
    }
}
