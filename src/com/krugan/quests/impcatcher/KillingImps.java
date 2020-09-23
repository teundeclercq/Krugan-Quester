package com.krugan.quests.impcatcher;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.*;

public class KillingImps extends AdvancedTask {
    public KillingImps(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
//        return Inventory.contains("Black bead") &&
//                Inventory.contains("Yellow bead") &&
//                Inventory.contains("White bead") &&
//                Inventory.contains("Red bead");
        return true;
    }

    @Override
    public void onFinish() {
        log("Done killing imps");
    }

    @Override
    public int execute() {
        main.setStateClient("Killing imps.");
        NPC imp = NPCs.closest(npc  -> npc.getName().equals("Imp") && Map.canReach(npc));

        GroundItem bead = GroundItems.closest(item -> (item.getName().equals("Black bead") | item.getName().equals("Yellow bead") | item.getName().equals("Red bead") | item.getName().equals("White bead")) && Map.canReach(item));

        if (bead != null) {
            Walking.walk(bead);
            sleep( Calculations.random(500, 1500));
            bead.interact("Take");
            sleepUntil(() -> Inventory.contains(bead), Calculations.random(2500, 3000));
        }

        if (imp != null) {
            if (!main.getLocalPlayer().isInCombat() && main.getLocalPlayer().getInteractingCharacter() == null) {
                Walking.walk(imp);
                sleep(Calculations.random(300, 400));
                Camera.mouseRotateToEntity(imp);

                if (imp.interact("Attack")) {
                    sleepUntil(() -> main.getLocalPlayer().isInCombat() || main.getLocalPlayer().getInteractingCharacter() != null, Calculations.random(2000, 3500));
                    sleep(Calculations.random(2000, 4000));
                }
            }
            return Calculations.random(2000, 3500);
        } else {
            Walking.walk(AreaProvider.ImpCatcher.impKillingArea.getRandomTile());
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
        }

        if (Dialogues.canContinue()) {
            Dialogues.spaceToContinue();
        }

        return Calculations.random(1500,2250);
    }
}
