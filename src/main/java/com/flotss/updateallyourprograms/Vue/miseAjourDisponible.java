package com.flotss.updateallyourprograms.Vue;

import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class miseAjourDisponible extends VBox implements Observateur {


    public miseAjourDisponible() {
        super();
        this.getChildren().add(new Label("Mise à jour disponible"));

    }

    @Override
    public void update(Model model) {
        this.getChildren().clear();

        ArrayList<MiseAjour> miseAjours = model.getMiseAjours();
        for (MiseAjour miseAjour : miseAjours) {
            HBox hBox = new HBox();
            hBox.setId(miseAjour.ID());
            hBox.getChildren().add(new Label(miseAjour.nom()));
            hBox.getChildren().add(new Label(miseAjour.ID()));
            hBox.getChildren().add(new Label(miseAjour.versionActuelle()));
            hBox.getChildren().add(new Label(miseAjour.versionDisponible()));
            this.getChildren().add(hBox);
        }
    }

}
