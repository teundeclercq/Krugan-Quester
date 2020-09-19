package com.krugan.util;

import com.krugan.quester.Main;
import com.krugan.quests.cooksassistant.*;

import java.util.ArrayList;

public class QuestFactory {
    public static ArrayList<Task> createQuest(QuestType type, Main ctx) {
        switch (type) {
            case COOKS_ASSISTANT:
                ArrayList<Task> cookAssistantNodes = new ArrayList<>();
                cookAssistantNodes.add(new StartQuestNode(ctx));
                cookAssistantNodes.add(new GetBucketNode(ctx));
                cookAssistantNodes.add(new GetPotNode(ctx));
                cookAssistantNodes.add(new GetEggNode(ctx));
                cookAssistantNodes.add(new GetMilkNode(ctx));
                cookAssistantNodes.add(new GetGrainNode(ctx));
                cookAssistantNodes.add(new GetFlourNode(ctx));
                cookAssistantNodes.add(new EndQuestNode(ctx));
                return cookAssistantNodes;
            case SHEEP_SHEARER:
               return null;
            default:
                return null;
        }

    }
}
