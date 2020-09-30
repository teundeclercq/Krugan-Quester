package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.walking.WalkToArea;
import com.krugan.util.walking.WalkToObject;
import org.dreambot.api.methods.interactive.GameObjects;

public class GoToBookcaseRoom extends AdvancedTask {
    public GoToBookcaseRoom(Main main) {
        super(main);
        this.tasks.add(new InteractWithObject(main, "Door", "Open", null));
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.cellarTrap));
        this.tasks.add(new InteractWithObject(main, "Ladder", "Climb-up", null));
        this.tasks.add(new WalkToObject(main, GameObjects.closest("Lever")));
        this.tasks.add(new InteractWithObject(main, "Lever", "Pull", null));
    }

    @Override
    public String toString() {
        return "Getting to book case room";
    }

    @Override
    public boolean isFinished() {
        return true;
//        AreaProvider.ErnestTheChick.bookCaseRoom.contains(main.getLocalPlayer());
    }
}
