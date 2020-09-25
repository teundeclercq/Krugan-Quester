package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.log;

public class TalkToVeronica extends AdvancedTask {
    public TalkToVeronica(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.veronicaArea));
        this.tasks.add(new TalkTo(main, "Veronica", "Talk-to", 1));
    }

    public boolean isFinished() {
        return PlayerSettings.getConfig(FreeQuest.ERNEST_THE_CHICKEN.getConfigID()) >= 1;
    }

    public void onFinish() {
        log("Done staring Ernest the Chicken");
    }

    public int execute() {
//        Area area = AreaProvider.ErnestTheChick.veronicaArea;
//            TravelTo(area);
//        NPC veronica = NPCs.closest("Veronica");
//        TalkToNpc(veronica, "Talk-to", 1);
        return Calculations.random(2500, 3000);
    }
}
