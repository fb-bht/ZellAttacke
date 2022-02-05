module cellattack {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires jbox2d.library;
    requires unirest.java;
    requires org.controlsfx.controls;

    opens de.bht.cellattack.controller to javafx.fxml;

    exports de.bht.cellattack;
}
