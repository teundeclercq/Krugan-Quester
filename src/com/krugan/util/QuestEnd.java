package com.krugan.util;

import c_utilities.CTime;
import com.krugan.quester.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.awt.*;

import static org.dreambot.api.methods.MethodProvider.*;

public class QuestEnd extends AdvancedTask {
    private int widgetParent = 277;
    private int widgetChild = 15;
    private WidgetChild widget;

    public QuestEnd(Main main) {
        super(main);
    }

    @Override
    public boolean isFinished() {
        return widget != null;
    }

    @Override
    public void onFinish() {
        log("Handling quest end");
    }

    @Override
    public int execute() {
        widget = Widgets.getWidgetChild(widgetParent, widgetChild);
        sleep(Calculations.random(2000, 4000));
        if (widget != null) {
            widget.interact("Close");
            sleep(Calculations.random(1000, 2000));
            if (Dialogues.inDialogue()) {
                Dialogues.spaceToContinue();
                sleep(Calculations.random(1000, 2000));

            }
        }
        return Calculations.random(2000, 4000);
    }
}
