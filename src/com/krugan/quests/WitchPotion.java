package com.krugan.quests;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.QuestEnd;
import com.krugan.util.interacting.*;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;

public class WitchPotion extends AdvancedTask {
    public WitchPotion(Main main) {
        super(main);
    }

    @Override
    public void AddTask() {
        this.main.addTasks(new getCoins(main));
        this.main.addTasks(new getEye(main));
        this.main.addTasks(new getMeat(main));
        this.main.addTasks(new getOnion(main));
        this.main.addTasks(new cookMeat(main));
        this.main.addTasks(new startQuest(main));
        this.main.addTasks(new getRatTail(main));
        this.main.addTasks(new endWitchPotion(main));
        this.main.addTasks(new QuestEnd(main));
        this.main.setRunning(true);
    }

    public class startQuest extends AdvancedTask{
        public startQuest(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.hetty));
            this.tasks.add(new TalkTo(main, "Hetty", "Talk-to", 1));
            this.tasks.add(new TalkTo(main, "Hetty", "Talk-to", 0));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) >= 1;
        }

        @Override
        public String toString() {
            return "Starting Witch potion.";
        }
    }

    public class getRatTail extends AdvancedTask {
        public getRatTail(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.ratArea));
            this.tasks.add(new AttackNpcAndLoot(main, "Rat", AreaProvider.WitchPotion.ratArea, "Rat's tail"));
            this.tasks.add(new GetGroundItem(main, "Rat's tail"));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Rat's tail");
        }

        @Override
        public String toString() {
            return "Getting rat's tail";
        }
    }

    public class getOnion extends AdvancedTask {
        public getOnion(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.onionField));
            this.tasks.add(new InteractWithObject(main, "Onion", "Pick", null) {
                @Override
                public boolean isFinished() {
                    return Inventory.contains("Onion");
                }
            });
        }

        @Override
        public String toString() {
            return "Getting onion";
        }
    }

    public class getMeat extends AdvancedTask {
        public getMeat(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.cowArea));
            this.tasks.add(new AttackNpcAndLoot(main, "Cow", AreaProvider.WitchPotion.cowArea, "Raw beef"));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Raw beef");
        }

        @Override
        public String toString() {
            return "Getting meat";
        }
    }

    public class getEye extends AdvancedTask {
        public getEye(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.eyeofNewtShop));
            this.tasks.add(new TalkTo(main, "Hetty", "Trade", 0) {
                @Override
                public boolean isFinished() {
                    return Shop.isOpen();
                }
            });
            this.tasks.add(new BuyItem(main, "Eye of newt", "Buy 1"));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Eye of newt");
        }

        @Override
        public String toString() {
            return "Getting eye";
        }
    }

    public class cookMeat extends AdvancedTask {
        public cookMeat(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.cookArea));
            this.tasks.add(new UseItemOnGameObject(main, "Range", "Cook", ""));
            this.tasks.add(new UseItemOnGameObject(main, "Range", "Cook", ""));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Burnt meat");
        }

        @Override
        public String toString() {
            return "Cooking the meat";
        }
    }

    public class endWitchPotion extends AdvancedTask {

        public endWitchPotion(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.hetty));
            this.tasks.add(new TalkTo(main, "Hetty", "Talk-to", 0));
            this.tasks.add(new TalkTo(main, "Hetty", "Talk-to", 0));
            this.tasks.add(new InteractWithObject(main, "Cauldron", "Drink From", ""));
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.WITCHS_POTION.getConfigID()) >= 3;
        }

        @Override
        public String toString() {
            return "Ending the quest";
        }
    }

    public class getCoins extends AdvancedTask{
        public getCoins(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.WitchPotion.draynorBank));
            this.tasks.add(new InteractWithObject(main, "Bank booth", "Bank", ""));
            this.tasks.add(new WithdrawItem(main, "Coins", 3));
        }

        @Override
        public boolean isFinished() {
            return Inventory.count("Coins") >= 3;
        }

        @Override
        public String toString() {
            return "Getting coins";
        }
    }






}
