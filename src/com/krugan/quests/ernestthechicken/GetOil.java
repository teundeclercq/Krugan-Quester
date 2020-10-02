package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.*;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.walking.WalkToTile;
import org.dreambot.api.methods.container.impl.Inventory;

import static org.dreambot.api.methods.MethodProvider.*;

public class GetOil extends AdvancedTask {
    public GetOil(Main main) {
        super(main);
        this.tasks.add(new InteractWithObject(main, "Lever B", "Pull", "You pull lever B down."));
        this.tasks.add(new sleepTask(main, 2000, 3000));
        this.tasks.add(new InteractWithObject(main, "Lever A", "Pull", "You pull lever A down."));
        this.tasks.add(new sleepTask(main, 4000, 5000));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile1));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));
        this.tasks.add(new sleepTask(main, 2000, 3000));


        this.tasks.add(new InteractWithObject(main, "Lever D", "Pull", "You pull lever D down."));
        this.tasks.add(new sleepTask(main, 4000, 5000));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile2));
        this.tasks.add(new sleepTask(main, 2000, 3000));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));
        this.tasks.add(new sleepTask(main, 2000, 3000));


        this.tasks.add(new InteractWithObject(main, "Lever B", "Pull", "You pull lever B up."));
        this.tasks.add(new sleepTask(main, 2000, 3000));


        this.tasks.add(new InteractWithObject(main, "Lever A", "Pull", "You pull lever A up."));
        this.tasks.add(new sleepTask(main, 4000, 5000));


        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile3));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile5));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile7));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new InteractWithObject(main, "Lever E", "Pull", "You pull lever E down."));
        this.tasks.add(new sleepTask(main, 4000, 5000));


        this.tasks.add(new InteractWithObject(main, "Lever F", "Pull", "You pull lever F down."));
        this.tasks.add(new sleepTask(main, 4000, 5000));


        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile9));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile11));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new InteractWithObject(main, "Lever C", "Pull", "You pull lever C down."));
        this.tasks.add(new sleepTask(main, 4000, 5000));


        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile12));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile10));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new InteractWithObject(main, "Lever E", "Pull", "You pull lever E up."));
        this.tasks.add(new sleepTask(main, 4000, 5000));


        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile9));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile13));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile4));
        this.tasks.add(new InteractWithObject(main,  "Door", "Open", null));

        this.tasks.add(new WalkToTile(main, AreaProvider.ErnestTheChick.tile15));
        this.tasks.add(new InteractWithObject(main, "Door", "Open",  null));

        this.tasks.add(new GetGroundItem(main, "Oil can"));

    }

    @Override
    public String toString() {
        return "getting the oil";
    }

    public boolean isFinished() {
        return Inventory.contains("Oil can");
    }

    public void onFinish() {
        log("Got the oil");
    }

}
