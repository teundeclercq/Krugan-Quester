package com.krugan.quests;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.QuestEnd;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;

import static org.dreambot.api.methods.MethodProvider.log;

public class RuneMysteries extends AdvancedTask {
    public RuneMysteries(Main main) {
        super(main);
    }

    public void AddTask() {
        // Walk to lumbridge
        this.main.addTasks(new startQuest(main));

        // Walk to Sedridor wizard tower.
        this.main.addTasks(new talkToSeridor(main));
        // Walk to Varrock.
        this.main.addTasks(new talkToAubury(main));
        //walk back to sedridor
        this.main.addTasks(new endRuneMysteries(main));
        this.main.addTasks(new QuestEnd(main));
        this.main.setRunning(true);
    }

    private static class startQuest extends AdvancedTask {
        public startQuest(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main,AreaProvider.RuneMysteries.dukeArea));
            this.tasks.add(new TalkTo(main, "Duke Horacio", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Duke Horacio", "Talk-to", 1));
        }
        @Override
        public boolean isFinished() {
            return Inventory.contains("Air talisman") || PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) >= 1;
        }

        @Override
        public String toString() {
            return "Starting Rune Mysteries";
        }

        @Override
        public void onFinish() {
            log("Done starting quest");
        }
    }

    private static class talkToSeridor extends AdvancedTask {
        public talkToSeridor(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RuneMysteries.seridorArea));
            this.tasks.add(new TalkTo(main, "Sedridor", "Talk-to", 3));
            this.tasks.add(new TalkTo(main, "Sedridor", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Sedridor", "Talk-to", 1));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Research package") || PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) >= 3;
        }

        @Override
        public String toString() {
            return "Talking to Seridor";
        }

        @Override
        public void onFinish() {
            log("Done talking to seridor");
        }
    }

    private static class talkToAubury extends AdvancedTask {
        public talkToAubury(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RuneMysteries.auburyShop));
            this.tasks.add(new TalkTo(main, "Aubury", "Talk-to", 3));
            this.tasks.add(new TalkTo(main, "Aubury", "Talk-to", 0));
        }
        @Override
        public boolean isFinished() {
            return Inventory.contains("Notes") || PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) >= 5;
        }

        @Override
        public String toString() {
            return "Talking to Aubury";
        }

        @Override
        public void onFinish() {
            log("Done talking to aubury");
        }
    }

    private static class endRuneMysteries extends AdvancedTask {

        public endRuneMysteries(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RuneMysteries.seridorArea));
            this.tasks.add(new TalkTo(main, "Sedridor", "Talk-to", 0));
            this.tasks.add(new TalkTo(main, "Sedridor", "Talk-to", 0));
        }

        @Override
        public String toString() {
            return "Finishing Rune mysteries";
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.RUNE_MYSTERIES.getConfigID()) >= 6;
        }

        @Override
        public void onFinish() {
            log("Done with rune mysteries");
        }
    }
}
