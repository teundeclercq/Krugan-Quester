package com.krugan.util;

public interface Task {
    void taskState();
    boolean isFinished();
    void onFinish();
    int execute();
    void AddTask();
}
