package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class WalkToTile extends AdvancedTask {
    protected Tile tile;
    protected boolean standingOnTile = false;
    public WalkToTile(Main main, Tile tile) {
        super(main);
        this.tile = tile;
    }

    public boolean isFinished() {
        return standingOnTile;
    }

    public int execute() {
        main.setStateClient("Walking to tile");
        while (Walking.walk(tile)) {
            if (main.getLocalPlayer().getTile().equals(tile)) {
                sleep(Calculations.random(2000, 4000));
                standingOnTile =true;
                break;
            }
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(800, 1250));
        }
        return Calculations.random(1000, 1500);

    }
}
