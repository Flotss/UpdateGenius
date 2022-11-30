package com.flotss.updateallyourprograms.Vue;

import com.flotss.updateallyourprograms.Controller.EventMouseDisponible;
import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Set;

public class MiseAjourDisponible extends ScrollPane implements Observateur {

    public MiseAjourDisponible(Model model) {
        super();
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.setMinWidth(350);
        this.setMinHeight(350);
        this.setMaxWidth(400);
        this.setMaxHeight(400);
        this.hbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
        // Set padding in right and left and bottom
        this.setPadding(new Insets(0, 10, 10, 10));

        this.update(model);
    }

    @Override
    public void update(Model model) {
        // The scroll bar is always at the top
        this.setVvalue(0);

        Set<MiseAjour> miseAjours = model.getMiseAjours();
        VBox vBox = new VBox();
        this.setMaxWidth(400);
        this.setMaxHeight(400);
        this.setWidth(350);
        this.setHeight(350);
        this.setContent(vBox);
        for (MiseAjour miseAjour : miseAjours) {
            HBox hBox = new HBox();
            hBox.setId(miseAjour.getID());
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(labelFactory(miseAjour.getNom(), miseAjour.getID(), model));
            hBox.getChildren().add(labelFactory(miseAjour.getVersionActuelle(), miseAjour.getID(), model));
            hBox.getChildren().add(labelFactory(miseAjour.getVersionDisponible(), miseAjour.getID(), model));
            vBox.getChildren().add(hBox);

            if (miseAjour == model.getMiseAjourCouranteDisponible()) {
                hBox.setStyle("-fx-background-color: "+Model.COLOR_SELECTED);
            }

        }
    }


    private Label labelFactory(String text, String id, Model model) {
        Label label = new Label(text);
        label.setId(id);
        label.setFont(Font.font("Arial", 13));
        label.setPadding(new Insets(0, 10, 0, 0));
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventMouseDisponible(model));
        return label;
    }

}
