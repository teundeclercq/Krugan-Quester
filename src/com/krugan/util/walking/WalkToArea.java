package com.krugan.util.walking;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;

public class WalkToArea extends AdvancedTask {
    protected Area area;
    public WalkToArea(Main main, Area area) {
        super(main);
        this.area = area;
    }

    public boolean isFinished() {
        return area.contains(main.getLocalPlayer());
    }

    public int execute() {
        while (!area.contains(main.getLocalPlayer())) {
            Walking.walk(area.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        }
        return Calculations.random(3000, 4000);
    }
}
