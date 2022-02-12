module cellattack {

    // JavaFx
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    // Physics-library Jbox2d
    requires jbox2d.library;
    
    // HTTP REST-library
    requires unirest.java;

    // for JSON 
    requires java.sql;

    // UI-library for JavaFx
    requires org.controlsfx.controls;

    // needed for JavaFx
    opens de.bht.cellattack.controller to javafx.fxml;
    opens de.bht.cellattack.application to javafx.graphics;
    opens de.bht.cellattack.model.dto to javafx.base, unirest.java;

    exports de.bht.cellattack;
}
