package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;

public class GetMilkNode extends AdvancedTask {
    public GetMilkNode(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().contains("Bucket of milk");
    }

    @Override
    public void onFinish() {
        log("Quest task complete: GetMilk");
    }

    @Override
    public int execute() {
        main.setStateClient("Getting bucket of milk");
        Area area = AreaProvider.CooksAssistant.cowArea;
        if (main.getInventory().contains("Bucket of milk")) {
            return Calculations.random(3000, 6000);
        }
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getCenter());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        } else {
            GameObject diaryCow = main.getGameObjects().closest("Dairy cow");
            if (diaryCow != null) {
                diaryCow.interact("Milk");
                MethodProvider.sleepUntil(() -> main.getInventory().contains("Bucket of milk"), Calculations.random(3000, 6000));

            }
        }
        return Calculations.random(3000, 6000);
    }
}
