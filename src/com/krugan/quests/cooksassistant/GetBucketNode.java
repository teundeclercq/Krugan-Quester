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

public class GetBucketNode extends CookAssistant {
    public GetBucketNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public int priority() {
        return 7;
    }

    @Override
    public boolean validate() {
        return !getItems() && !isPot() && !isGrain() && !isFlour() && !isEgg() && !isBucket() &&
                !isMilk();
    }

    @Override
    public int execute() {
        main.setStateClient("Getting bucket");
        if (main.getInventory().contains("Bucket")) {
            setBucket(true);
            return (int) Calculations.nextGaussianRandom(400, 200);
        }
        Area basement = AreaProvider.CooksAssistant.basementArea;
        Area cookArea = AreaProvider.CooksAssistant.cookArea;
        if (!cookArea.contains(main.getLocalPlayer())) {
            main.getWalking().walk(cookArea.getCenter());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(2000, 4000));
        }
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
        return (int) Calculations.nextGaussianRandom(400, 200);
    }
}
