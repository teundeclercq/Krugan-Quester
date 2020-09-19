package com.krugan.util;

import com.krugan.quester.Main;
import org.dreambot.api.methods.MethodProvider;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class AdvancedTask implements Task {
    protected Main main;

    public AdvancedTask(Main main) {
        this.main = main;
    }

}
