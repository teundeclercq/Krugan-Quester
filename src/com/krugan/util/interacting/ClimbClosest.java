package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.Task;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.MethodProvider.log;

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
    }


    @Override
    public int execute() {
        climbClosest();
        return Calculations.random(3000, 4000);
    }

    @Override
    public boolean isFinished() {
        return main.getLocalPlayer().getZ() == zGoal;
    }

    private boolean climbClosest() {
        int attempts = 0;
        zGoal = main.getLocalPlayer().getZ() + floors;
        while ((main.getLocalPlayer().getZ() != zGoal) || attempts > ((this.floors >= 5) ? (10) : (5))) {
            GameObject climbable = GameObjects.closest((this.isStairs) ? ("Stairs") : ("Ladder"));
            if (climbable != null) {
                climbable.interact((this.isUp) ? ("Climb-up") : ("Climb-down"));
                attempts++;
                MethodProvider.sleepUntil(() -> !main.getLocalPlayer().isAnimating(), Calculations.random(3000, 6000));
            }
        }
        return true;
    }
}
