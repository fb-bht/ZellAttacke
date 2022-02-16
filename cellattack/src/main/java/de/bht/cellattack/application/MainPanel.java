package de.bht.cellattack.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * MainPanel Class for Login process
 * only needed for Debugging/Development
 */
public class MainPanel extends Application {

    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("User Login");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}
