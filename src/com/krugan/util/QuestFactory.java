package com.krugan.util;

import com.krugan.quester.Main;
import com.krugan.quests.cooksassistant.*;
import com.krugan.quests.impcatcher.ImpCatcherStart;
import com.krugan.quests.impcatcher.KillingImps;
import com.krugan.quests.restlessghost.*;
import com.krugan.quests.witchpotion.*;

import java.awt.geom.Area;
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
            case RESTLESS_GHOST:
                ArrayList<Task> restlessGhostNodes = new ArrayList<>();
                restlessGhostNodes.add(new Start(ctx));
                restlessGhostNodes.add(new GetGhostSpeak(ctx));
                restlessGhostNodes.add(new WalkToGraveyard(ctx));
                restlessGhostNodes.add(new GetSkull(ctx));
                restlessGhostNodes.add(new Finish(ctx));
                return restlessGhostNodes;
            case IMP_CATCHER:
                ArrayList<Task> impCatcherNodes = new ArrayList<>();
                impCatcherNodes.add(new KillingImps(ctx));
                impCatcherNodes.add(new ImpCatcherStart(ctx));
                impCatcherNodes.add(new QuestEnd(ctx));
                return impCatcherNodes;
            case WITCH_POTION:
                ArrayList<Task> witchPotionNodes = new ArrayList<>();
                witchPotionNodes.add(new GetEyeOfNewt(ctx));
                witchPotionNodes.add(new GetMeat(ctx));
                witchPotionNodes.add(new GetOnion(ctx));
                witchPotionNodes.add(new CookingMeat(ctx));
                witchPotionNodes.add(new StartWitchPotion(ctx));
                witchPotionNodes.add(new GetRatTail(ctx));
                witchPotionNodes.add(new EndWitchPotion(ctx));
                witchPotionNodes.add(new QuestEnd(ctx));
                return witchPotionNodes;
            default:
                return null;
        }

    }
}
