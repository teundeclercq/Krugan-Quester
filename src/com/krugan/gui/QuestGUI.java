package com.krugan.gui;


import com.krugan.quester.Main;
import com.krugan.util.*;

import javax.swing.*;
import java.util.ArrayList;

public class QuestGUI {
    private boolean isRunning;
    private JPanel mainPanel;
    private JCheckBox ironManBTWCheckBox;
    private JButton startButton;
    private JComboBox QuestCMB;
    private ArrayList<Node> questNodes;

    public QuestGUI(Main main) {
        JFrame frame = new JFrame("QuestGUI");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        QuestCMB.setModel(new DefaultComboBoxModel<>(QuestType.values()));
        QuestCMB.addActionListener(l -> {
            questNodes = QuestFactory.createQuest((QuestType) QuestCMB.getSelectedItem(), main, Utility.getInstance(main));
        });
        startButton.addActionListener(l -> {
            main.setQuest(questNodes);
            main.setRunning(true);
            frame.dispose();
        });

        frame.pack();
        frame.setVisible(true);
    }
}
