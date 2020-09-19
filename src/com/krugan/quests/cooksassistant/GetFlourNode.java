package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GetFlourNode extends AdvancedTask {

    public GetFlourNode(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().contains("Pot of flour");
    }

    @Override
    public void onFinish() {
        log("Quest task complete: GetFlour");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting the flour");
        if (main.getInventory().contains("Pot of flour")) {
            return Calculations.random(3000, 6000);
        }
        log("GetFlour: Start");
        // Walking to the windmill
        Area windMillArea = AreaProvider.CooksAssistant.windMillArea;
        if (!windMillArea.contains(main.getLocalPlayer())) {
            main.getWalking().walk(windMillArea.getRandomTile());
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 4000));
        }
        sleep(Calculations.random(1000, 2000));

        // Open door
        GameObject largeDoor = main.getGameObjects().closest("Large door");
        if (largeDoor.hasAction("Open")) {
            largeDoor.interact("Open");
        }

        sleep(Calculations.random(1000, 3000));
        // Climb stairs
        try {
            ClimbClosest(2, true, false);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(1000, 3000));

        // Fill hopper
        GameObject hopper = main.getGameObjects().closest("Hopper");
        if (hopper != null) {
            hopper.interact("Fill");
        }
        sleep(Calculations.random(3000, 4000));
        // Operate controls
        GameObject hopperControls = main.getGameObjects().closest("Hopper controls");
        if (hopperControls != null) {
            hopperControls.interact("Operate");
        }
        sleep(Calculations.random(3000, 4000));

        // climb down
        try {
            ClimbClosest(-2, false, false);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
        sleep(Calculations.random(4000, 5000));

        // get Flour
        GameObject flourBin = main.getGameObjects().closest("Flour bin");
        if (flourBin != null) {
            flourBin.interact("Empty");
            sleep(Calculations.random(4000, 5000));
        }
        log("GetFlour: End");
        return Calculations.random(3000, 6000);
    }

//    public boolean ClimbClosest(int floors, boolean isUp, boolean isStairs) {
//        int attempts = 0;
//        int zGoal = main.getLocalPlayer().getZ() + floors;
//        while ((main.getLocalPlayer().getZ() != zGoal) || attempts > ((floors >= 5) ? (10) : (5))) {
//            GameObject climbable = main.getGameObjects().closest((isStairs) ? ("Stairs") : ("Ladder"));
//            if (climbable != null) {
//                climbable.interact((isUp) ? ("Climb-up") : ("Climb-down"));
//                MethodProvider.log((isUp) ? "Climb-up":"Climb-down");
//                attempts++;
//                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
//            }
//        }
//        return true;
//    }
}
