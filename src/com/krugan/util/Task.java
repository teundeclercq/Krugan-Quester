package com.krugan.util;

public interface Task {

    boolean isFinished();
    void onFinish();
    int execute();
}
