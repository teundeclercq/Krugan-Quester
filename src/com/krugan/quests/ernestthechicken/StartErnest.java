package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.log;

public class StartErnest extends AdvancedTask {
    public StartErnest(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return PlayerSettings.getConfig(FreeQuest.ERNEST_THE_CHICKEN.getConfigID()) >= 1;
    }

    @Override
    public void onFinish() {
        log("Done staring Ernest the Chicken");
    }

    @Override
    public int execute() {
        Area area = AreaProvider.ErnestTheChick.veronicaArea;
            TravelTo(area);
        NPC veronica = NPCs.closest("Veronica");
        TalkToNpc(veronica, "Talk-to", 1);
        return Calculations.random(2500, 3000);
    }
}
