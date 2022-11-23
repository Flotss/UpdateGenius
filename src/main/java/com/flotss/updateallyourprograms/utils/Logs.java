package com.flotss.updateallyourprograms.utils;

import com.flotss.updateallyourprograms.Model.Model;

public class Logs {
    private String log;

    private final Model model;

    public Logs(Model model) {
        log = "";
        this.model = model;
    }

    public void addLog(String log) {
        this.log += this.log + "\n" + log;
        model.notifierObservateurs();
    }

    public String getLog() {
        return log;
    }
}
