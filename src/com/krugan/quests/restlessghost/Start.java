package com.krugan.quests.restlessghost;

import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import com.krugan.util.AreaProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.methods.MethodProvider.*;

public class Start extends AdvancedTask {

    public Start(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return main.getPlayerSettings().getConfig(FreeQuest.THE_RESTLESS_GHOST.getConfigID()) >= 1;
    }

    @Override
    public void onFinish() {
        log("Started Restless Ghost");
    }

    @Override
    public int execute() {
        main.setStateClient("Start Restless ghost");
        Area startArea = AreaProvider.RestlessGhost.start;
        if (!startArea.contains(main.getLocalPlayer())) {
            main.getWalking().walk(startArea.getCenter());
            sleepUntil(() -> main.getLocalPlayer().isStandingStill(), Calculations.random(1500, 3500));
        }
        NPC father = main.getNpcs().closest("Father Aereck");
        Integer[] options = new Integer[] {3,1};
        for(Integer opt: options) {
            TalkToNpc(father, "Talk-to", opt);
        }
        return Calculations.random(1000, 2000);
    }

//    public boolean anwserDialog(Integer options) {
//        int count = options.length;
//        int realcount = 0;
//        for (Integer option : options) {
//            if (main.getDialogues().inDialogue() && !main.getDialogues().canContinue()) {
//                if (main.getDialogues().chooseOption(option)) {
//                    realcount++;
//                    sleep(Calculations.random(2000, 4000));
//                    if (realcount == count) {
//                        break;
//                    }
//                }
//            }
//        }
//        return true;
//    }
}
