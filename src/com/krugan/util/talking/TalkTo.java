package com.krugan.util.talking;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class TalkTo extends AdvancedTask {
    protected String npc;
    protected String action;
    protected Integer option;
    protected boolean doneTalkingTo = false;
    public TalkTo(Main main, String npc, String action, Integer option) {
        super(main);
        this.npc = npc;
        this.action = action;
        this.option = option;
    }

    public boolean isFinished() {
        return doneTalkingTo;
    }

    public int execute() {
        NPC npc = NPCs.closest(this.npc);
        if (!Dialogues.inDialogue()) {
            npc.interact(action);
            sleep(Calculations.random(1500, 2750));
        }
        if (Dialogues.inDialogue()) {
            while (Dialogues.canContinue()) {
                Dialogues.spaceToContinue();
                sleep(Calculations.random(2000, 2500));
            }
            if (option != 0) {
                if (Dialogues.getOptions().length > 0) {
                    Dialogues.chooseOption(option);
                    sleep(Calculations.random(1750, 3250));
                }
            }
            while (Dialogues.canContinue()) {
                Dialogues.spaceToContinue();
                sleep(Calculations.random(2000, 2500));
            }
        }
        this.doneTalkingTo = true;
        return Calculations.random(1750, 2500);
    }
}
