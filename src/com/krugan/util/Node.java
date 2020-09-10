package com.krugan.util;

import com.krugan.quester.Main;

public abstract class Node {
    protected final Main main;
    protected final Utility utility;

    protected Node(Main main, Utility utility) {
        this.main = main;
        this.utility = utility;
    }
    public int priority() {
        return 0;
    }

    public abstract boolean validate();
    public abstract int execute();
}
