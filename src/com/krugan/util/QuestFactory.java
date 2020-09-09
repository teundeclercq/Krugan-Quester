package com.krugan.util;

import com.krugan.quester.Main;
import com.krugan.quests.cooksassistant.*;

import java.util.ArrayList;

public class QuestFactory {
    public static ArrayList<Node> createQuest(QuestType type, Main ctx, Utility utility) {
        switch (type) {
            case COOKS_ASSISTANT:
                ArrayList<Node> cookAssistantNodes = new ArrayList<>();
                cookAssistantNodes.add(new EndQuestNode(ctx, utility));
                cookAssistantNodes.add(new StartQuestNode(ctx, utility));
                cookAssistantNodes.add(new GetEggNode(ctx, utility));
                cookAssistantNodes.add(new GetFlourNode(ctx, utility));
                cookAssistantNodes.add(new GetGrainNode(ctx, utility));
                cookAssistantNodes.add(new GetMilkNode(ctx, utility));
                cookAssistantNodes.add(new GetPotNode(ctx, utility));
                cookAssistantNodes.add(new GetBucketNode(ctx, utility));
                return cookAssistantNodes;
            case SHEEP_SHEARER:
                ArrayList<Node> sheepShearerNodes = new ArrayList<>();
                return sheepShearerNodes;
            default:
                return null;
        }
    }
}
