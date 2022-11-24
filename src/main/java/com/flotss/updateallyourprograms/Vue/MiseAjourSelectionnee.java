package com.flotss.updateallyourprograms.Vue;

import com.flotss.updateallyourprograms.Controller.EventMouseSelectionnee;
import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Set;

public class MiseAjourSelectionnee extends ScrollPane implements Observateur {

    public MiseAjourSelectionnee(Model model) {
        super();
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.hbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
        this.setMinWidth(350);
        this.setMinHeight(350);
        this.setMaxWidth(400);
        this.setMaxHeight(400);
        // Set padding in right and left and bottom
        this.setPadding(new Insets(0, 10, 10, 10));

        this.update(model);
    }

    @Override
    public void update(Model model) {
        // The scroll bar is always at the top
        this.setVvalue(0);

        Set<MiseAjour> miseAjours = model.getMiseAjoursSelectionnes();
        VBox vBox = new VBox();
        vBox.setMaxHeight(400);
        vBox.setMinHeight(400);
        vBox.setMinWidth(400);
        vBox.setMaxWidth(400);
        this.setContent(vBox);
        for (MiseAjour miseAjour : miseAjours) {
            HBox hBox = new HBox();
            hBox.setId(miseAjour.getID());
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(labelFactory(miseAjour.getNom(), miseAjour.getID(), model));
            vBox.getChildren().add(hBox);

            if (miseAjour == model.getMiseAjourCouranteSelectionnee()) {
                hBox.setStyle("-fx-background-color: #00daff");
            }
        }
    }


    private Label labelFactory(String text, String id, Model model) {
        Label label = new Label(text);
        label.setPadding(new Insets(0, 10, 0, 0));
        label.setFont(Font.font("Arial", 13));
        label.setId(id);
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventMouseSelectionnee(model));
        return label;
    }

}
