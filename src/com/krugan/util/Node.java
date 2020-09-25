package com.krugan.util;

import com.krugan.quester.Main;

public abstract class Node {
    protected Main main;

    public Node(Main main) {
        this.main = main;
    }

    public abstract void execute();

    public abstract boolean validate();
}
