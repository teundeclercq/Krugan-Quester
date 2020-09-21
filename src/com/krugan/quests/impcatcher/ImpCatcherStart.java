package com.krugan.quests.impcatcher;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;

public class ImpCatcherStart extends AdvancedTask {
    public ImpCatcherStart(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void onFinish() {

    }

    @Override
    public int execute() {
        return 0;
    }
}
