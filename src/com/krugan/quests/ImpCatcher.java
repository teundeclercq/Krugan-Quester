package com.krugan.quests;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.QuestEnd;
import com.krugan.util.interacting.AttackNpcAndLoot;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class ImpCatcher extends AdvancedTask {
    public ImpCatcher(Main main) {
        super(main);
    }

    @Override
    public void AddTask() {
        this.main.addTasks(new killImpLootBeads(main));
        this.main.addTasks(new startAndEndImpCatcher(main));
        this.main.addTasks(new QuestEnd(main));
        this.main.setRunning(true);
    }

    public class killImpLootBeads extends AdvancedTask {
        public killImpLootBeads(Main main) {
            super(main);
            this.tasks.add(new AttackNpcAndLoot(main, "Imp", AreaProvider.ImpCatcher.impKillingArea, new String[] {"Black bead", "Yellow bead", "White bead", "Red bead" }) {
                @Override
                public boolean isFinished() {
                    return Inventory.contains("Yellow bead") &&
                            Inventory.contains("White bead") &&
                            Inventory.contains("Black bead") &&
                            Inventory.contains("Red bead");
                }
            });
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Yellow bead") &&
                    Inventory.contains("White bead") &&
                    Inventory.contains("Black bead") &&
                    Inventory.contains("Red bead");
        }

        @Override
        public String toString() {
            return "Getting beads";
        }
    }

    public class startAndEndImpCatcher extends AdvancedTask {

        public startAndEndImpCatcher(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.ImpCatcher.startQuest));
            this.tasks.add(new TalkTo(main, "Wizard Mizgog", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Wizard Mizgog", "Talk-to", 0));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.IMP_CATCHER.getConfigID()) >= 2;
        }

        @Override
        public String toString() {
            return "Finishing Imp catcher";
        }
    }
}
