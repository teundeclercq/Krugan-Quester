package com.krugan.quests.cooksassistant;

import com.krugan.quester.Main;
import com.krugan.util.Node;
import com.krugan.util.Utility;

public abstract class CookAssistant extends Node {
    private boolean gotItems = false;
    private boolean bucket = false;
    private boolean pot = false;
    private boolean egg = false;
    private boolean milk = false;
    private boolean grain = false;
    private boolean flour = false;

    protected CookAssistant(Main main, Utility utility) {
        super(main, utility);
    }
    public boolean getItems() {
        return gotItems;
    }
    public void setGotItems(boolean gotItems) {
        this.gotItems = gotItems;
    }

    @Override
    public int execute() {
        return 0;
    }

    public boolean isGotItems() {
        return gotItems;
    }

    public boolean isBucket() {
        return bucket;
    }

    public void setBucket(boolean bucket) {
        this.bucket = bucket;
    }

    public boolean isPot() {
        return pot;
    }

    public void setPot(boolean pot) {
        this.pot = pot;
    }

    public boolean isEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isGrain() {
        return grain;
    }

    public void setGrain(boolean grain) {
        this.grain = grain;
    }

    public boolean isFlour() {
        return flour;
    }

    public void setFlour(boolean flour) {
        this.flour = flour;
    }
}
