package com.krugan.util.interacting;

import c_utilities.CTime;
import com.krugan.quester.Main;
import com.krugan.util.AdvancedTask;
import org.dreambot.api.input.event.impl.InteractionEvent;
import org.dreambot.api.input.event.impl.InteractionSetting;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.interact.Interactable;
import org.dreambot.api.wrappers.widgets.message.Message;

import static org.dreambot.api.methods.MethodProvider.*;

public class InteractWithObject extends AdvancedTask implements ChatListener {
    protected String obj;
    protected String action;
    protected String message;
    protected boolean interactedWithObject;
    public InteractWithObject(Main main, String gameObject, String action, String message) {
        super(main);
        this.action = action;
        this.obj = gameObject;
        this.message = message;
        this.interactedWithObject = false;
    }

    public int execute() {
        if (!this.main.getLocalPlayer().isAnimating()) {
            GameObject object = GameObjects.closest(_object -> _object.hasAction(action) && _object.getName().equals(this.obj));
            if (object != null) {
                    Camera.rotateToEntity(object);
                    object.interact(action);
                    log("Interacting with");
                    sleepUntil(() -> main.getLocalPlayer().isAnimating(), Calculations.random(3000, 4000));
            }
        }
        return Calculations.random(3000, 4000);
    }

    @Override
    public boolean isFinished() {
        return interactedWithObject;
    }

    @Override
    public void onGameMessage(Message message) {
        if (message.getMessage().equals(this.message)) {
            if (sleepUntil(() -> !main.getLocalPlayer().isAnimating() && main.getLocalPlayer().isStandingStill(), Calculations.random(4000, 6000))) {
                interactedWithObject = true;
            }
        }
    }
}
