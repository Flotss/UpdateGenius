package com.flotss.updateallyourprograms.Vue;

import com.flotss.updateallyourprograms.Model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LogsAffichage extends ScrollPane implements Observateur {


    public LogsAffichage(Model model) {
        super();
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.setFocusTraversable(false);
        this.hbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
        this.resize(100, 100);
        // Set padding bottom
        this.setPadding(new Insets(0, 0, 10, 0));

        this.update(model);
    }

    @Override
    public void update(Model model) {
        ArrayList<String> logs = model.getLogs().getAllLogsString();
        VBox vBox = new VBox();
        this.setContent(vBox);
        for (String log : logs) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(labelFactory(log));
            vBox.getChildren().add(hBox);
        }

        // The scroll bar is always at the bottom
        this.setVvalue(1.0);
    }


    private Label labelFactory(String text) {
        Label label = new Label(text);
        label.setPadding(new Insets(0, 10, 0, 0));
        return label;
    }

}
