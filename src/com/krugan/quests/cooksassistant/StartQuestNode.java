package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.log;

public class StartQuestNode extends AdvancedTask {
    public StartQuestNode(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getPlayerSettings().getConfig(FreeQuest.COOKS_ASSISTANT.getConfigID()) > 0;
    }

    @Override
    public void onFinish() {
        log("Quest task complete: start quest");
    }

    @Override
    public int execute() {
        main.setStateClient("Starting the quest");
        Area area = AreaProvider.CooksAssistant.cookArea;
        if (!area.contains(main.getLocalPlayer())) {
            main.getWalking().walk(area.getRandomTile());
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(3000, 6000));
        } else {
            TalkToCook(main.getNpcs(), main.getDialogues());
        }
        return Calculations.random(1000, 4000);
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
