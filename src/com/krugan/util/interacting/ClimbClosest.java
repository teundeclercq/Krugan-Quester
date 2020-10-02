package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.Task;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class ClimbClosest extends AdvancedTask {
    protected int floors;
    protected boolean isUp;
    protected boolean isStairs;
    protected int zGoal;
    public ClimbClosest(Main main, int floors, boolean isUp, boolean isStairs) {
        super(main);
        this.floors = floors;
        this.isUp = isUp;
        this.isStairs = isStairs;
        zGoal = main.getLocalPlayer().getZ() + floors;
    }


    @Override
    public int execute() {
        if ((main.getLocalPlayer().getZ() != zGoal)) {
            GameObject climbable = GameObjects.closest((this.isStairs) ? ("Stairs") : ("Ladder"));
            if (climbable != null) {
                climbable.interact((this.isUp) ? ("Climb-up") : ("Climb-down"));
                sleepUntil(() -> !main.getLocalPlayer().isAnimating(), Calculations.random(3000, 6000));
            }
        }
        return Calculations.random(3000, 4000);
    }

    @Override
    public boolean isFinished() {
        return main.getLocalPlayer().getZ() == zGoal;
    }

}