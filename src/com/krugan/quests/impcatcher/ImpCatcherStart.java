package com.krugan.quests.impcatcher;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import static org.dreambot.api.methods.MethodProvider.log;

public class ImpCatcherStart extends AdvancedTask {
    public ImpCatcherStart(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
//        return PlayerSettings.getConfig(FreeQuest.IMP_CATCHER.getConfigID()) >= 2;
        return true;
    }

    @Override
    public void onFinish() {
        log("Done with starting the quest");
    }

    @Override
    public int execute() {
        main.setStateClient("Starting quest");
        try {
            TravelTo(AreaProvider.ImpCatcher.startQuest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NPC wizardMizgog = NPCs.closest("Wizard Mizgog");
        if (wizardMizgog != null) {
            talkToNpc(wizardMizgog, "Talk-to", 1);
        }
        WidgetChild questWidget = Widgets.getMatchingWidget(widgetChild -> widgetChild.hasAction("Close"));
        if (questWidget != null) {
            questWidget.interact("Close");
        }
        return Calculations.random(3000, 4000);
    }
}
