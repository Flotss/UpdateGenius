module com.flotss.updateallyourprograms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.flotss.updateallyourprograms to javafx.fxml;
    exports com.flotss.updateallyourprograms;
}