package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetPotNode extends AdvancedTask {
    public GetPotNode(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().contains("Pot");
    }

    @Override
    public void onFinish() {
        log("Quest task complete: GetPot");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting pot");
        Area area = AreaProvider.CooksAssistant.cookArea;
        if (main.getInventory().contains("Pot")) {

            return Calculations.random(3000, 6000);

        }
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getRandomTile());
        } else {
            GroundItem pot = main.getGroundItems().closest("Pot");
            if (pot != null) {
                pot.interact("Take");
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
            }
        }
        return Calculations.random(3000, 6000);
    }

}
