package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.QuestEnd;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class ErnestTheChicken extends AdvancedTask {
    public ErnestTheChicken(Main main) {
        super(main);
    }

    public void AddTask() {
        this.main.addTasks(new TalkToVeronica(main));
        this.main.addTasks(new GetPoison(main));
        this.main.addTasks(new GetFishFood(main));
        this.main.addTasks(new GoToCellar(main));
        this.main.addTasks(new GetOil(main));
        this.main.addTasks(new GoToBookcaseRoom(main));
        this.main.addTasks(new GetSpade(main));
        this.main.addTasks(new GetKey(main));
        this.main.addTasks(new GetPresureGauge(main));
        this.main.addTasks(new GetRubberTube(main));
        this.main.addTasks(new TalkToProffesorOddenstein(main));
        this.main.addTasks(new QuestEnd(main));
        this.main.setRunning(true);
    }
}
