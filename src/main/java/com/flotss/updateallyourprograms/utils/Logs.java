package com.flotss.updateallyourprograms.utils;

import com.flotss.updateallyourprograms.Model.Model;

import java.util.ArrayList;

public class Logs {
    private final ArrayList<String> logs;

    private final Model model;

    public Logs(Model model) {
        this.logs = new ArrayList<>();
        this.model = model;
    }

    public void addLog(String log) {
        this.logs.add(log);
        model.notifierObservateurs();
    }

    public ArrayList<String> getAllLogsString() {
        return this.logs;
    }
}
