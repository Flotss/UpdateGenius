package com.flotss.updateallyourprograms;

import com.flotss.updateallyourprograms.Controller.*;
import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.Vue.LogsAffichage;
import com.flotss.updateallyourprograms.Vue.MiseAjourDisponible;
import com.flotss.updateallyourprograms.Vue.MiseAjourSelectionnee;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class UpdateAllYourPrograms extends Application {

    private static final String TITLE = "Update All Your Programs";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);


        Model model = new Model();


        // Top
        BorderPane top = new BorderPane();
        Label titleGestionnaire = new Label("Gestionnaire de mise à jour");
        titleGestionnaire.setPadding(new Insets(10, 10, 10, 10));
        titleGestionnaire.setAlignment(Pos.CENTER);
        titleGestionnaire.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        top.setCenter(titleGestionnaire);

        // Left
        VBox left = new VBox();
        left.setMaxHeight(400);
        left.setMinHeight(400);
        left.setMinWidth(400);
        left.setMaxWidth(400);
        Label titleMiseAjourDisponible = labelFactory("Mise à jour disponible");
        MiseAjourDisponible miseAjourDisponible = new MiseAjourDisponible(model);
        model.ajouterObservateur(miseAjourDisponible);

        left.getChildren().addAll(titleMiseAjourDisponible, miseAjourDisponible);



        // Right
        VBox right = new VBox();
        right.setMinHeight(400);
        right.setMaxHeight(400);
        right.setMinWidth(400);
        right.setMaxWidth(400);
        Label titleMiseAjourSelec = labelFactory("Mise à jour sélectionnée");

        MiseAjourSelectionnee miseAjourSelectionnee = new MiseAjourSelectionnee(model);
        model.ajouterObservateur(miseAjourSelectionnee);

        right.getChildren().addAll(titleMiseAjourSelec , miseAjourSelectionnee);


        // Bottom
        VBox bottom = new VBox();
        bottom.setMaxHeight(150);
        Label titleLogs = labelFactory("Détails");
        LogsAffichage logsAffichage = new LogsAffichage(model);
        model.ajouterObservateur(logsAffichage);
        bottom.getChildren().addAll(titleLogs, logsAffichage);

        // Center
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);
        Button button = new Button(">");
        button.setMinWidth(50);
        button.addEventHandler(ActionEvent.ACTION, new EventButtonAjout(model));
        Button button2 = new Button("<");
        button2.setMinWidth(50);
        button2.addEventHandler(ActionEvent.ACTION, new EventButtonRetire(model));
        Button button3 = new Button("> > >");
        button3.setMinWidth(50);
        button3.addEventHandler(ActionEvent.ACTION, new EventButtonAjoutAll(model));
        Button button4 = new Button("< < <");
        button4.addEventHandler(ActionEvent.ACTION, new EventButtonRetireAll(model));
        button4.setMinWidth(50);
        Button button5 = new Button("Executer les mises à jour");
        button5.addEventHandler(ActionEvent.ACTION, new EventButtonUpdate(model));
        center.getChildren().addAll( button, button2, button3, button4, button5);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setLeft(left);
        root.setRight(right);
        root.setBottom(bottom);
        root.setCenter(center);





        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }

    private Label labelFactory(String text) {
        Label label = new Label(text);
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        return label;
    }
}