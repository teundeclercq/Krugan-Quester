package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;

public class GetFlourNode extends CookAssistant {

    public GetFlourNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean validate() {
        return !getItems() && isPot() && isGrain() && !isFlour() && isEgg() && isBucket() &&
                isMilk();
    }

    @Override
    public int execute() {
        main.setStateClient("Getting the flour");
        if (main.getInventory().contains("Pot of flour")) {
            setFlour(true);
            setGotItems(true);
            return (int) Calculations.nextGaussianRandom(400, 200);
        }
        MethodProvider.log("GetFlour: Start");
        Utility.TravelTo(AreaProvider.CooksAssistant.windMillArea);
        MethodProvider.sleep(Calculations.random(1000, 2000));
        while (main.getGameObjects().closest("Large door").hasAction("Open")) {
            Utility.ClosestSpecifiedGameObjectInteractConditional("Large door", "Open", "Open", true);
        }
        Utility.ClimbClosest(2, true, false);
        GameObject hopper = main.getGameObjects().closest("Hopper");
        if (hopper != null) {
            hopper.interact("Fill");
        }
        MethodProvider.sleep(Calculations.random(4000, 5000));
        Utility.ClosestSpecifiedGameObjectInteract("Hopper controls", "Operate");
        MethodProvider.sleep(Calculations.random(4000, 5000));
        Utility.ClimbClosest(-2, false, false);
        MethodProvider.sleep(Calculations.random(4000, 5000));
        GameObject flourBin = main.getGameObjects().closest("Flour bin");
        if (flourBin != null) {
            flourBin.interact("Empty");
            MethodProvider.sleep(Calculations.random(4000, 5000));
        }
        MethodProvider.log("GetFlour: End");
        return (int) Calculations.nextGaussianRandom(400, 200);
    }
}
