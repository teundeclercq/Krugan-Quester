package com.krugan.quester;

import com.krugan.gui.QuestGUI;
import com.krugan.quests.ernestthechicken.ErnestTheChicken;
import com.krugan.quests.ernestthechicken.GetOil;
import com.krugan.util.Node;
import com.krugan.util.Task;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.quest.book.FreeQuest;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


@ScriptManifest(category = Category.QUEST, name = "KruganQuester", author = "Krugan", version = 1.1)
public class Main extends AbstractScript {
    private boolean isRunning;
    public List<Task> tasks = new LinkedList<>();
    public List<Node> nodes = new LinkedList<>();
    private String stateClient;

    public void setStateClient(String stateClient) {
        this.stateClient = stateClient;
    }

    @Override
    public void onStart() {
        log("Starting the GUI");
        SwingUtilities.invokeLater(() -> {
            QuestGUI gui = new QuestGUI(this);
        });
    }

    @Override
    public void stop() {
        super.stop();
    }

    public void addNodes(Node node) {
        this.nodes.add(node);
    }
    public void addTasks(Task tasks) {
        this.tasks.add(tasks);
    }

    @Override
    public int onLoop() {
        log(PlayerSettings.getConfig(FreeQuest.ERNEST_THE_CHICKEN.getConfigID()));
        if (isRunning) {
            if (this.tasks.isEmpty()) {
                log("No more available nodes, script has ended.");
                stop();
                return Calculations.random(1000, 5000);
            } else {
                try {
                    Iterator iterator = tasks.iterator();
                    while (iterator.hasNext()) {
                        Task task = (Task) iterator.next();
                        if (!task.isFinished()) {
                            log(task.toString());
                            setStateClient(task.toString());
                            return task.execute();
                        }
                        task.onFinish();
                        iterator.remove();
                    }
                } catch (Exception e) {
                    log(e.toString());
                }
            }
        } else {
            log("Doing nothing");
            if (!tasks.isEmpty()) {
                Iterator it = tasks.iterator();
                Task t = (Task) it.next();
                t.AddTask();
            }
        }
        return Calculations.random(1000, 3000);
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    @Override
    public void onPaint(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Avenier", Font.PLAIN, 12));
        g.drawString("State: " + stateClient, 10, 35);
    }

}
