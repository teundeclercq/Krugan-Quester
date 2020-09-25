package com.krugan.util.walking;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class WalkToGroundItem extends AdvancedTask {
    protected String item;
    protected boolean standingOnTile = false;
    public WalkToGroundItem(Main main, String item) {
        super(main);
        this.item = item;
    }

    public boolean isFinished() {
        return standingOnTile;
    }

    public int execute() {
        GroundItem item = GroundItems.closest(this.item);
        while (Walking.walk(item.getTile())) {
            if (main.getLocalPlayer().getTile().equals(item.getTile())) {
                sleep(Calculations.random(2000, 4000));
                standingOnTile =true;
                break;
            }
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(800, 1250));
        }
        return Calculations.random(1000, 1500);

    }
}
