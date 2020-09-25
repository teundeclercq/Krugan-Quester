package com.krugan.quests.ernestthechicken;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.talking.TalkTo;

public class TalkToTest extends AdvancedTask {
    public TalkToTest(Main main) {
        super(main);
        this.tasks.add(new TalkTo(main, "Bob", "Talk-to", 1));
        this.tasks.add(new TalkTo(main, "Bob", "Talk-to", 1));
    }
}
