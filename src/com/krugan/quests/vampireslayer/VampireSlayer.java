package com.krugan.quests.vampireslayer;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.InteractWithObject;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class VampireSlayer extends AdvancedTask {
    public VampireSlayer(Main main) {
        super(main);
    }

    @Override
    public void AddTask() {
//        this.main.addTasks(new InteractWithObject(main, "Coffin", "Open", null) {
//            @Override
//            public boolean isFinished() {
//                NPC count = NPCs.closest("Count draynor");
//                if (count != null) {
//                    return count.hasAction("Attack");
//                }
//                return false;
//            }
//        });
        this.main.addTasks(new SafeSpotVampire(main, "Count draynor", new Tile(3076, 9769, 0), new Tile(3076, 9768, 0), new Tile(3075, 9769, 0), "Shrimp"));
        this.main.setRunning(true);
    }

    public class SafeSpotVampire extends AdvancedTask {
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
                Walking.walkOnScreen(firstTile);
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
//            return main.getLocalPlayer().getHealthPercent() >= 100;
        }
    }
}
