package com.krugan.quests;

import com.krugan.quester.Main;
import com.krugan.util.Node;
import com.krugan.util.Utility;

public class SheepShearer extends Node {
    public SheepShearer(Main main, Utility utility) {
        super(main, utility);
    }

    @Override
    public boolean validate() {
        return main.getQuest().equals(this);
    }

    @Override
    public void execute() {
        main.log("Sheep shearer is running");
    }
}
