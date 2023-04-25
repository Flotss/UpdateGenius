package com.flotss.updateallyourprograms.Controller;

import com.flotss.updateallyourprograms.Model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventButtonAjoutAll implements EventHandler<ActionEvent> {
    private final Model model;

    public EventButtonAjoutAll(Model model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        model.ajouterToutesLesMiseAjours();
    }
}
