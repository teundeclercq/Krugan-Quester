package com.krugan.quester;

import com.krugan.gui.QuestGUI;
import com.krugan.util.Node;
import org.dreambot.api.Client;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.quest.book.Quest$State;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;


@ScriptManifest(category = Category.QUEST, name = "KruganQuester", author = "Krugan", version = 1.1)
public class Main extends AbstractScript {
    private ArrayList<Node> nodes;
    private boolean isRunning;
    private String stateClient;

    public void setStateClient(String stateClient) {
        this.stateClient = stateClient;
    }

    @Override
    public void onStart() {
        log("Starting the GUI");
        QuestGUI gui = new QuestGUI(this);
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public int onLoop() {
//        log(String.valueOf(settings.length));
//        for(int setting : settings) {
//            log(String.valueOf(setting));
//        }
        FreeQuest.DEMON_SLAYER.(getClient());
        if (isRunning) {
            for (Node node: nodes) {
                    if (node.validate()) {
                        node.execute();
                    }
                }
        }
        return Calculations.random(300, 400);
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public void setQuest(ArrayList<Node> quest) {
        this.nodes = quest;
    }
    public ArrayList<Node> getQuest() {
        return this.nodes;
    }

    @Override
    public void onPaint(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Avenier", Font.PLAIN, 12));
        g.drawString("State: " + stateClient, 10, 35);
    }
}
