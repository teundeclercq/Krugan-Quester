package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import com.krugan.util.sleepTask;
import com.krugan.util.talking.TalkTo;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.settings.PlayerSettings;

public class TalkToProffesorOddenstein extends AdvancedTask {
    public TalkToProffesorOddenstein(Main main) {
        super(main);
        this.tasks.add(new WalkToArea(main, AreaProvider.ErnestTheChick.professorArea));
        this.tasks.add(new TalkTo(main, "Professor oddenstein", "Talk-to", 1));
        this.tasks.add(new TalkTo(main, "Professor oddenstein", "Talk-to", 2));
        this.tasks.add(new TalkTo(main, "Professor oddenstein", "Talk-to", 0));
        this.tasks.add(new sleepTask(main, 6000, 8000));
        this.tasks.add(new TalkTo(main, "Ernest", "Talk-to", 0));
    }


    @Override
    public String toString() {
        return "Finishing - talkiing to professor oddenstein";
    }

    @Override
    public boolean isFinished() {
        return PlayerSettings.getConfig(FreeQuest.ERNEST_THE_CHICKEN.getConfigID()) >= 3;
    }
}
