package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.walking.WalkToArea;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class GoToCellar extends AdvancedTask {
    public GoToCellar(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.bookCaseRoom));
        this.tasks.add(new InteractWithObject(main, "Bookcase", "Search", "You've found a secret door!"));
        this.tasks.add(new InteractWithObject(main, "Ladder", "Climb-down", null));
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.cellarTrap));
    }

    @Override
    public String toString() {
        return "Going to cellar";
    }

    @Override
    public boolean isFinished() {
        return true;
//        AreaProvider.ErnestTheChick.cellarTrap.contains(main.getLocalPlayer());
    }

    @Override
    public void onFinish() {
        log("In the cellar");
    }
}
