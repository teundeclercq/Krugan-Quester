package com.krugan.quests;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.QuestEnd;
import com.krugan.util.interacting.BuyItem;
import com.krugan.util.interacting.InteractWithObject;
import com.krugan.util.interacting.WithdrawItem;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class VampireSlayer extends AdvancedTask {
    public VampireSlayer(Main main) {
        super(main);
    }

    @Override
    public void AddTask() {
        this.main.addTasks(new startVampireSlayer(main));
        this.main.addTasks(new getGarlic(main));
        this.main.addTasks(new getCoins(main));
        this.main.addTasks(new talkToHarlow(main));
        this.main.addTasks(new getHammer(main));
        this.main.addTasks(new withdrawingAttackItems(main));
        this.main.addTasks(new goToVampire(main));
        this.main.addTasks(new SafeSpotVampire(main, "Count draynor", new Tile(3076, 9769, 0), new Tile(3076, 9768, 0), new Tile(3075, 9769, 0), "Shrimps"));
        this.main.addTasks(new killVampire(main , "Count draynor", new Tile(3076, 9769, 0), new Tile(3076, 9768, 0), new Tile(3075, 9769, 0), "Shrimps"));
        this.main.addTasks(new QuestEnd(main));
        this.main.setRunning(true);
    }

    private class getHammer extends AdvancedTask {
        public getHammer(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.varrockGeneralStore));
            this.tasks.add(new TalkTo(main, "Shop keeper", "Trade", null));
            this.tasks.add(new BuyItem(main, "Hammer", "Buy 1"));

        }

        @Override
        public String toString() {
            return "Getting hammer.";
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Hammer");
        }
    }

    private class talkToHarlow extends AdvancedTask  {
        public talkToHarlow(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.varrockInn));
            this.tasks.add(new TalkTo(main, "Dr Harlow", "Talk-to", 2));
            this.tasks.add(new TalkTo(main, "Bartender", "Talk-to", 1) {
                @Override
                public boolean isFinished() {
                    return Inventory.contains("Beer");
                }
            });
            this.tasks.add(new TalkTo(main, "Dr Harlow", "Talk-to",2));

        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Stake");
        }

        @Override
        public String toString() {
            return "Talking to dr harlow";
        }
    }

    private class getCoins extends  AdvancedTask {

        public getCoins(Main main) {
            super(main);
            this.tasks.add(new InteractWithObject(main, "Staircase", "Climb-down", null));
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.draynorBank));
            this.tasks.add(new InteractWithObject(main, "Bank booth", "Bank", null));
            this.tasks.add(new WithdrawItem(main, "Coins", 4));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Coins");
        }

        @Override
        public String toString() {
            return "Getting coins";
        }
    }
    private class getGarlic extends AdvancedTask {

        public getGarlic(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.upstairsMorgan));
            this.tasks.add(new InteractWithObject(main, "Cupboard", "Open", ""));
            this.tasks.add(new InteractWithObject(main, "Cupboard", "Search", ""));
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Garlic");
        }

        @Override
        public String toString() {
            return "Getting garlic";
        }
    }

    private class startVampireSlayer extends AdvancedTask {
        public startVampireSlayer(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.MorganArea));
            this.tasks.add(new TalkTo(main, "Morgan", "Talk-to", 2));
        }

        @Override
        public String toString() {
            return "Starting vampire slayer";
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) >= 1;
        }
    }

    private class withdrawingAttackItems extends AdvancedTask {
        public withdrawingAttackItems(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.draynorBank));
            this.tasks.add(new InteractWithObject(main, "Bank booth", "Bank", null));
            this.tasks.add(new WithdrawItem(main, "Fire rune", 300));
            this.tasks.add(new WithdrawItem(main, "Mind rune", 100));
            this.tasks.add(new WithdrawItem(main, "Air rune", 200));
            this.tasks.add(new WithdrawItem(main, "Shrimps", 20));
        }

        @Override
        public String toString() {
            return "Getting items to kill vampire";
        }

        @Override
        public boolean isFinished() {
            return Inventory.contains("Fire rune") &&
                    Inventory.contains("Mind rune") &&
                    Inventory.contains("Air rune") &&
                    Inventory.contains("Hammer") &&
                    Inventory.contains("Stake") &&
                    Inventory.contains("Shrimps") &&
                    Inventory.contains("Garlic");
        }
    }

    private class killVampire extends AdvancedTask {
        private String npc;
        private Tile firstTile;
        private Tile attackTile;
        private Tile safeSpotTile;
        private String itemToEat;

        public killVampire(Main main, String npc, Tile firstTile, Tile attackTile, Tile safeSpotTile, String itemToEat ) {
            super(main);
            this.npc = npc;
            this.firstTile = firstTile;
            this.attackTile = attackTile;
            this.safeSpotTile = safeSpotTile;
            this.itemToEat = itemToEat;
        }

        @Override
        public int execute() {

            NPC npc = NPCs.closest(this.npc);
            if (!npc.getTile().equals(attackTile)) {
                this.main.addTasks(new SafeSpotVampire(main, "Count draynor", new Tile(3076, 9769, 0), new Tile(3076, 9768, 0), new Tile(3075, 9769, 0), "Shrimps"));
                this.isFinished();
            }

            if (npc.getTile().equals(attackTile) && main.getLocalPlayer().getTile().equals(safeSpotTile)) {
                Magic.castSpellOn(Normal.FIRE_STRIKE, npc);
                sleepUntil(() -> main.getLocalPlayer().isInCombat() || main.getLocalPlayer().getInteractingCharacter() != null, Calculations.random(3000, 4000));
            }
            return Calculations.random(2000, 3000);
        }

        @Override
        public boolean isFinished() {
            return PlayerSettings.getConfig(FreeQuest.VAMPIRE_SLAYER.getConfigID()) >= 3;
        }
    }

    private class goToVampire extends AdvancedTask {
        public goToVampire(Main main) {
            super(main);
            this.tasks.add(new WalkToArea(main, AreaProvider.VampireSlayer.countDraynor));
            this.tasks.add(new InteractWithObject(main, "Coffin", "Open", null) {
                @Override
                public boolean isFinished() {
                    NPC count = NPCs.closest("Count draynor");
                    if (count != null) {
                        return count.hasAction("Attack");
                    }
                    return false;
                }
            });
        }

        @Override
        public String toString() {
            return "Going to the vampire";
        }

        @Override
        public boolean isFinished() {
            NPC count = NPCs.closest("Count draynor");
            if (count != null) {
                return count.hasAction("Attack");
            }
            return false;
        }
    }

    private class SafeSpotVampire extends AdvancedTask {
        protected String npc;
        protected Tile firstTile;
        protected Tile attackTile;
        protected Tile safeSpotTile;
        protected String itemToEat;

        public SafeSpotVampire(Main main, String npc,Tile firstTile, Tile attackTile, Tile safeSpotTile, String itemToEat ) {
            super(main);
            this.npc = npc;
            this.firstTile = firstTile;
            this.attackTile = attackTile;
            this.safeSpotTile = safeSpotTile;
            this.itemToEat = itemToEat;
        }

        @Override
        public int execute() {
          NPC npc = NPCs.closest(this.npc);
            if (main.getLocalPlayer().getHealthPercent() < 70) {
                    Inventory.interact(itemToEat, "eat");
                    sleepUntil(() -> !main.getLocalPlayer().isAnimating(), Calculations.random(900, 1200));
            }

            if (npc.getTile().equals(firstTile)) {
                Camera.rotateToTile(firstTile);
                Walking.walk(firstTile);
                sleepUntil(() -> npc.getTile().equals(attackTile), Calculations.random(2000, 2250));
                if (npc.getTile().equals(attackTile)) {
                    Walking.walkOnScreen(safeSpotTile);
                } else {
                    Walking.walkOnScreen(attackTile);
                    sleepUntil(() -> npc.getTile().equals(firstTile), Calculations.random(2000, 2250));
                }
            } else {
                Walking.walkOnScreen(attackTile);
                sleepUntil(() -> main.getLocalPlayer().isStandingStill() && npc.getTile().equals(firstTile), Calculations.random(2000, 2250));
            }


            return Calculations.random(1500, 1750);
        }

        @Override
        public String toString() {
            return "SafeSpotVampire";
        }

        @Override
        public boolean isFinished() {
            return NPCs.closest(this.npc).getTile().equals(attackTile);
        }
    }
}
