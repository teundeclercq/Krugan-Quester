package com.krugan.quester;

import com.krugan.gui.QuestGUI;
import com.krugan.util.Node;
import org.dreambot.api.Client;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.quest.book.Quest$State;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


@ScriptManifest(category = Category.QUEST, name = "KruganQuester", author = "Krugan", version = 1.1)
public class Main extends AbstractScript {
    private ArrayList<Node> cache = new ArrayList<>();
    private ArrayList<Node> open = new ArrayList<>();
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
        int delay = Calculations.random(300, 400);

           if (isRunning) {
               if (!cache.isEmpty()) {
                   open.clear();
                   open.addAll(cache.stream().filter(Node::validate).collect(Collectors.toList()));
                   if (!open.isEmpty()) {
                      delay = getSuitableOpenNode().execute();
                   }
               }
           }
        return delay;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public void setQuest(ArrayList<Node> quest) {
        this.cache = quest;
    }

    public ArrayList<Node> getQuest() {
        return this.cache;
    }

    @Override
    public void onPaint(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Avenier", Font.PLAIN, 12));
        g.drawString("State: " + stateClient, 10, 35);
    }

    public Node getSuitableOpenNode() {
        Node node = null;
        if (!open.isEmpty()) {
            node = open.get(0);
            if (open.size() > 1) {
                for (Node possible  : open) {
                    if (node.priority() < possible.priority()) {
                        node = possible;
                    }
                }
            }
        }
        return node;
    }
}
