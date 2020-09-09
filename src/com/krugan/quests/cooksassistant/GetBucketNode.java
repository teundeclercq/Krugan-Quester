package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

public class GetBucketNode extends Node {
    public GetBucketNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public boolean validate() {
        return !main.getInventory().contains("Bucket") &&
                !main.getInventory().contains("Bucket of Milk") &&
                !main.getInventory().contains("Egg") &&
                !main.getInventory().contains("Pot of flour");
    }

    @Override
    public void execute() {
        main.setStateClient("Getting bucket");
        Area basement = AreaProvider.CooksAssistant.basementArea;

        if (!basement.contains(main.getLocalPlayer())) {
            GameObject trapdoor = main.getGameObjects().closest("Trapdoor");
            if (trapdoor != null) {
                trapdoor.interact("Climb-down");
            } else {
                main.getWalking().walk(basement.getRandomTile());
            }
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2000, 4000));
        } else {
            GroundItem bucket = main.getGroundItems().closest("Bucket");
            if (bucket != null) {
                bucket.interact("Take");
            }
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill() && main.getInventory().contains("Bucket"), Calculations.random(3000, 4000));
        }
        if (main.getInventory().contains("Bucket")) {
            GameObject trap = main.getGameObjects().closest("Ladder");
            if (trap != null) {
                trap.interact("Climb-up");
            }
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
    }
}
