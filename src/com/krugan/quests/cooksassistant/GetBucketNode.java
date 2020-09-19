package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;


public class GetBucketNode extends AdvancedTask {
    public GetBucketNode(Main main) {
        super(main);
        execute();
    }

    @Override
    public int execute() {
        main.setStateClient("Getting bucket");
        Area basement = AreaProvider.CooksAssistant.basementArea;
        Area cookArea = AreaProvider.CooksAssistant.cookArea;
        if (!cookArea.contains(main.getLocalPlayer())) {
            main.getWalking().walk(cookArea.getCenter());
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2000, 4000));
        }
        if (!basement.contains(main.getLocalPlayer())) {
            GameObject trapdoor = main.getGameObjects().closest("Trapdoor");
            if (trapdoor != null) {
                trapdoor.interact("Climb-down");
            } else {
                main.getWalking().walk(basement.getRandomTile());
            }
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2000, 4000));
        } else {
            GroundItem bucket = main.getGroundItems().closest("Bucket");
            if (bucket != null) {
                bucket.interact("Take");
            }
            sleepUntil(() -> main.getLocalPlayer().isStandingStill() && main.getInventory().contains("Bucket"), Calculations.random(3000, 4000));
        }
        if (main.getInventory().contains("Bucket")) {
            GameObject trap = main.getGameObjects().closest("Ladder");
            if (trap != null) {
                trap.interact("Climb-up");
            }
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
        return Calculations.random(3000, 4000);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().contains("Bucket");
    }

    @Override
    public void onFinish() {
        log("Task done: Getting Bucket.");
    }
}
