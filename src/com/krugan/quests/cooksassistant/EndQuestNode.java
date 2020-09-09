package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AreaProvider;
import com.krugan.util.Node;
import com.krugan.util.Utility;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.log;

public class EndQuestNode extends Node {
    public EndQuestNode(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public boolean validate() {
        main.getTabs().open(Tab.QUEST);
        return (main.getInventory().contains("Pot of flour") ||
                main.getInventory().contains("Bucket of milk") ||
                main.getInventory().contains("Egg")) &&
                main.getQuests().isStarted(FreeQuest.COOKS_ASSISTANT);
    }

    @Override
    public void execute() {
        main.setStateClient("Ending quest");
        Area area = AreaProvider.CooksAssistant.cookArea;
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        } else {
            TalkToCook(main.getNpcs(), main.getDialogues());
            if (main.getQuests().isFinished(FreeQuest.COOKS_ASSISTANT)) {
                main.stop();
            }
        }
    }

    public void TalkToCook(NPCs npcs, Dialogues dialogues) {
        NPC cook = npcs.closest(npc -> npc.getName().equals("Cook"));
        if (cook != null) {
            if (!dialogues.inDialogue()) {
                cook.interact("Talk-to");
            }
            while(dialogues.canContinue()) {
                dialogues.spaceToContinue();
            }
            if (!dialogues.canContinue()) {
                dialogues.chooseOption(1);
            }
        }
    }
}
