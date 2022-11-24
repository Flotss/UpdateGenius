package com.flotss.updateallyourprograms.Controller;

import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class EventMouseSelectionnee implements EventHandler<MouseEvent> {
    private final Model model;

    public EventMouseSelectionnee(Model model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        String idMiseAjour = label.getId();
        for (MiseAjour miseAjour : model.getMiseAjoursSelectionnes()) {
            if (miseAjour.getID().equals(idMiseAjour)) {
                model.setMiseAjourCouranteSelectionnee(miseAjour);
                break;
            }
        }
    }
}
