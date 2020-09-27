package com.krugan.util.interacting;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.wrappers.interactive.interact.Interactable;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class InsideDialogue extends AdvancedTask {
    protected Integer dialogOption;
    public InsideDialogue(Main main, Integer dialogOption) {
        super(main);
        this.dialogOption = dialogOption;
    }

    public int execute() {
        while (Dialogues.canContinue()) {
            Dialogues.spaceToContinue();
            sleep(Calculations.random(2000, 2500));
        }
        if (dialogOption != 0) {
            if (Dialogues.getOptions().length > 0) {
                Dialogues.chooseOption(dialogOption);
                sleep(Calculations.random(1750, 3250));
            }
        }
        return Calculations.random(3000, 4000);
    }

    public boolean isFinished() {
        return !Dialogues.inDialogue();
    }
}
