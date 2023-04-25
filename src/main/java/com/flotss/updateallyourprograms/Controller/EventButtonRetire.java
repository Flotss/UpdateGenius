package com.flotss.updateallyourprograms.Controller;

import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventButtonRetire implements EventHandler<ActionEvent> {
    private final Model model;

    public EventButtonRetire(Model model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MiseAjour miseAjour = model.getMiseAjourCouranteSelectionnee();
        if (miseAjour != null) {
            model.deselectionnerMiseAjour(miseAjour);
            model.notifierObservateurs();
        }
    }
}
