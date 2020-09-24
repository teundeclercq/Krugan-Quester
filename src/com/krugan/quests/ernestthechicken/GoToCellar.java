package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GoToCellar extends AdvancedTask {
    public GoToCellar(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return AreaProvider.ErnestTheChick.cellarTrap.contains(main.getLocalPlayer());
    }

    @Override
    public void onFinish() {
        log("In the cellar");
    }

    @Override
    public int execute() {
        // Walk to the bookcase
        Area bookCase = AreaProvider.ErnestTheChick.bookCaseRoom;
            TravelTo(bookCase);
        // use bookcase
        ClosestSpecifiedGameObjectInteract("Bookcase", "Search");
        sleep(Calculations.random(5000,10000));
        // user ladder
        ClosestSpecifiedGameObjectInteract("Ladder", "Climb-down");
        sleep(Calculations.random(5000,10000));

        return Calculations.random(1250, 1550);
    }
}
