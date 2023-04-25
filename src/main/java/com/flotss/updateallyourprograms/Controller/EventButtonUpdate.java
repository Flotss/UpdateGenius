package com.flotss.updateallyourprograms.Controller;

import com.flotss.updateallyourprograms.Model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;


public class EventButtonUpdate implements EventHandler<ActionEvent> {
    private final Model model;

    public EventButtonUpdate(Model model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            model.executerLesMiseAjours();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
