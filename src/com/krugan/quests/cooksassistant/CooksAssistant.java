package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.*;
import com.krugan.util.interacting.ClimbClosest;
import com.krugan.util.interacting.GetGroundItem;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.pathfinding.impl.obstacle.impl.ClimbableObstacle;
import org.dreambot.api.wrappers.interactive.Player;

public class CooksAssistant extends AdvancedTask {
    public CooksAssistant(Main main) {
        super(main);
        this.main.addTasks(new startCooksAssistant(main));
        this.main.addTasks(new getPot(main));
        this.main.addTasks(new getBucket(main));
        this.main.addTasks(new getMilk(main));
        this.main.addTasks(new getEgg(main));
        this.main.addTasks(new getGrain(main));
        this.main.addTasks(new getFlour(main));
        this.main.addTasks(new finishCooksAssistant(main));
        this.main.addTasks(new QuestEnd(main));
        this.main.setRunning(true);
    }
    private class startCooksAssistant extends AdvancedTask {
        public startCooksAssistant(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.cookArea));
            this.tasks.add(new TalkTo(main, "Cook", "Talk-to", 1));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) >= 1;
        }

        @Override
        public String toString() {
            return "Starting the quest";
        }
    }


    private class getBucket extends AdvancedTask {
        public getBucket(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.basementArea));
            this.tasks.add(new GetGroundItem(main, "Bucket"));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Bucket");
        }

        @Override
        public String toString() {
            return "Getting the bucket";
        }
    }

    private class getPot extends AdvancedTask {
        public getPot(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.cookArea));
            this.tasks.add(new GetGroundItem(main, "Pot"));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Pot");
        }

        @Override
        public String toString() {
            return "getting the pot";
        }
    }
    private class getMilk extends AdvancedTask {
        public getMilk(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.cowArea));
            this.tasks.add(new InteractWithObject(main, "Cow", "Milk", ""));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Bucket of milk");
        }

        @Override
        public String toString() {
            return "Getting the milk";
        }
    }

    private class getFlour extends AdvancedTask {
        public getFlour(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.windMillArea));
            this.tasks.add(new ClimbClosest(main, 2, true, false));
            this.tasks.add(new InteractWithObject(main, "Hopper", "Fill", ""));
            this.tasks.add(new InteractWithObject(main, "Hopper controls", "Operate", ""));
            this.tasks.add(new ClimbClosest(main, -2, false, false));
            this.tasks.add(new InteractWithObject(main, "Flour bin", "Empty", ""));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Pot of flour");
        }

        @Override
        public String toString() {
            return "Getting the Flour";
        }
    }

    private class getGrain extends AdvancedTask {
        public getGrain(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.grainArea));
            this.tasks.add(new InteractWithObject(main, "Wheat", "Pick", ""));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Grain");
        }

        @Override
        public String toString() {
            return "Getting the grain";
        }
    }


    private class finishCooksAssistant extends AdvancedTask {
        public finishCooksAssistant(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.cookArea));
            this.tasks.add(new TalkTo(main, "Cook", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Cook", "Talk-to", 0));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) >= 2;
        }

        @Override
        public String toString() {
            return "Finishing the quest";
        }
    }

    private class getEgg extends AdvancedTask {
        public getEgg(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.CooksAssistant.chickenArea));
            this.tasks.add(new GetGroundItem(main, "Egg"));
        }

        @Override
        public String toString() {
            return "Getting the egg";
        }
    }
}
