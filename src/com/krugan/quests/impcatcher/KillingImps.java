package com.krugan.quests.impcatcher;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.ArrayList;

import static org.dreambot.api.methods.MethodProvider.*;

public class KillingImps extends AdvancedTask {
    public KillingImps(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getInventory().containsAll("Black bead", "Yellow bead", "Red bead", "White bead");
    }

    @Override
    public void onFinish() {
        log("Done killing imps");
    }

    @Override
    public int execute() {
        main.setStateClient("Killing imps.");
        NPC imp = main.getNpcs().closest(npc  -> npc.getName().equals("Imp") && !npc.isInCombat());
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(new Area(2944, 3306, 2961, 3297));
        areas.add(new Area(3000, 3319, 3011, 3310));
        areas.add(new Area(2963, 3302, 2973, 3293));
        areas.add(new Area(2983, 3295, 3005, 3281));

        if (imp != null) {
            if (!main.getLocalPlayer().isInCombat() && main.getLocalPlayer().getInteractingCharacter() == null) {
                if (imp.interact("Attack")) {
                    sleepUntil(() -> main.getLocalPlayer().isInCombat() || main.getLocalPlayer().getInteractingCharacter() != null, Calculations.random(2000, 3500));
                    sleep(Calculations.random(2000, 4000));
                }
            }
            return Calculations.random(2000, 3500);
        } else {
            for (Area area : areas) {
                main.getWalking().walk(area.getRandomTile());
                sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
            }
        }

        if (main.getDialogues().canContinue()) {
            main.getDialogues().spaceToContinue();
        }

        return 0;
    }
}
