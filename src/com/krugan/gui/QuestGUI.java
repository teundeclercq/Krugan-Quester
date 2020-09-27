package com.krugan.gui;


import com.krugan.quester.Main;
import com.krugan.util.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestGUI {
    private boolean isRunning;
    private JPanel mainPanel;
    private JCheckBox ironManBTWCheckBox;
    private JButton startButton;
    private JComboBox<QuestType> QuestCMB;
    private Task questNodes;


    public QuestGUI(Main main) {
        JFrame frame = new JFrame("QuestGUI");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        QuestCMB.setModel(new DefaultComboBoxModel<>(QuestType.values()));
        QuestCMB.addActionListener(l -> {
            questNodes = QuestFactory.createQuest((QuestType) Objects.requireNonNull(QuestCMB.getSelectedItem()), main);
        });

        frame.pack();
        frame.setVisible(true);

        startButton.addActionListener(l -> {
            main.addTasks(questNodes);
            frame.dispose();
        });
    }
}
