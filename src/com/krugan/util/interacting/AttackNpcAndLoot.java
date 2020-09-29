package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.methods.MethodProvider.sleep;
import static org.dreambot.api.methods.MethodProvider.sleepUntil;

public class AttackNpcAndLoot extends AdvancedTask {
    protected String npc;
    protected String[] itemToPickup;
    protected Area area;

    public AttackNpcAndLoot(Main main, String npc, Area area, String... itemToPickup) {
        super(main);
        this.npc = npc;
        this.itemToPickup = itemToPickup;
        this.area = area;
    }

    @Override
    public int execute() {
        NPC npcToKill = NPCs.closest(npc  -> npc.getName().equals(this.npc) && !npc.isInCombat() && npc.isOnScreen() && Map.canReach(npc));

        GroundItem groundItem = GroundItems.closest(this.itemToPickup);
        if (!Inventory.contains(this.itemToPickup)) {
            if (groundItem != null) {
                Walking.walk(groundItem);
                sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(5000, 8000));
                groundItem.interact("Take");
                sleepUntil(() -> Inventory.contains(groundItem) && main.getLocalPlayer().isStandingStill(), Calculations.random(2500, 3000));
            }
        }

        if (npcToKill != null) {
            if (!main.getLocalPlayer().isInCombat() && main.getLocalPlayer().getInteractingCharacter() == null) {
                Walking.walk(npcToKill);
                sleep(Calculations.random(300, 400));
                Camera.mouseRotateToEntity(npcToKill);

                if (npcToKill.interact("Attack")) {
                    sleepUntil(() -> main.getLocalPlayer().isInCombat() || main.getLocalPlayer().getInteractingCharacter() != null, Calculations.random(2000, 3500));
                    sleep(Calculations.random(2000, 4000));
                }
            }
            return Calculations.random(2000, 3500);
        } else {
            Walking.walk(area.getRandomTile());
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 4000));
        }

        if (Dialogues.canContinue()) {
            Dialogues.spaceToContinue();
        }

        return Calculations.random(1500,2250);
    }

    @Override
    public boolean isFinished() {
        return Inventory.contains(itemToPickup);
    }
}
