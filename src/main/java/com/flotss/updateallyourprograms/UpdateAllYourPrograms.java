package com.flotss.updateallyourprograms;

import com.flotss.updateallyourprograms.Model.Model;
import com.flotss.updateallyourprograms.Vue.miseAjourDisponible;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class UpdateAllYourPrograms extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Update All Your Programs");
        BorderPane root = new BorderPane();
        Model model = new Model();
        miseAjourDisponible miseAjourDisponible = new miseAjourDisponible();
        model.ajouterObservateur(miseAjourDisponible);
        root.setCenter(miseAjourDisponible);


        stage.setScene(new Scene(root));
        stage.show();

        model.notifierObservateurs();
    }
}