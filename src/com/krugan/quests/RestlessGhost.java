package com.krugan.quests;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.QuestEnd;
import com.krugan.util.interacting.EquipItem;
import com.krugan.util.interacting.UseItemOnGameObject;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class RestlessGhost extends AdvancedTask {
    public RestlessGhost(Main main) {
        super(main);
        this.main.addTasks(new startRestlessGhost(main));
        this.main.addTasks(new getGhostSpeak(main));
        this.main.addTasks(new walkToGraveyard(main));
        this.main.addTasks(new getSkull(main));
        this.main.addTasks(new finishRestlessGhost(main));
        this.main.addTasks(new QuestEnd(main));
    }

    public class startRestlessGhost extends AdvancedTask {
        public startRestlessGhost(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RestlessGhost.start));
            this.tasks.add(new TalkTo(main, "Father Aereck", "Talk-to", 3));
            this.tasks.add(new TalkTo(main, "Father Aereck", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Father Aereck", "Talk-to", 0));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 1;
        }
    }

    public class getSkull extends AdvancedTask {
        public getSkull(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RestlessGhost.wizardTowerBasementSkull));
            this.tasks.add(new InteractWithObject(main, "Altar", "Search", ""));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 4;
        }
    }
    public class getGhostSpeak extends AdvancedTask {
        public getGhostSpeak(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RestlessGhost.fatherUrayArea));
            this.tasks.add(new TalkTo(main, "Father Urhney", "Talk-to", 2));
            this.tasks.add(new TalkTo(main, "Father Urhney", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Father Urhney", "Talk-to", 0));

        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 2;
        }
    }

    public class walkToGraveyard extends AdvancedTask {
        public walkToGraveyard(Main main) {
            super(main);
            this.tasks.add(new EquipItem(main, "Ghostspeak amulet", "Wear"));
            this.tasks.add(new WalkToArea(main, AreaProvider.RestlessGhost.ghostGraveYard));
            this.tasks.add(new InteractWithObject(main, "Coffin", "Open", ""));
            this.tasks.add(new TalkTo(main, "Restless ghost", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Restless ghost", "Talk-to", 0));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 3;
        }
    }

    public class finishRestlessGhost extends AdvancedTask {
        public finishRestlessGhost(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.RestlessGhost.ghostGraveYard));
            this.tasks.add(new TalkTo(main, "Restless ghost", "Talk-to", 0));
            this.tasks.add(new UseItemOnGameObject(main, "Coffin", "Skull", ""));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 5;
        }
    }



}
