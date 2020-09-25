package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.Node;
import com.krugan.util.walking.WalkToArea;
import org.dreambot.api.methods.Calculations;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class ErnestTheChicken extends Node {
    public ErnestTheChicken(Main main) {
        super(main);
    }

    @Override
    public void execute() {
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


        sleep(Calculations.random(1000, 4000));
    }

    @Override
    public boolean validate() {
        return this.main.tasks.isEmpty();
    }
}
