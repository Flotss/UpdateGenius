package com.flotss.updateallyourprograms.Controller;

import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventButtonAjout implements EventHandler<ActionEvent> {
    private final Model model;

    public EventButtonAjout(Model model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MiseAjour miseAjour = model.getMiseAjourCouranteDisponible();
        if (miseAjour != null) {
            model.selectionnerMiseAjour(miseAjour);
            model.notifierObservateurs();
        }
    }
}
