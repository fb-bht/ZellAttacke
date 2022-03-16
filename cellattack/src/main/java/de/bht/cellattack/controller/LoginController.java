package de.bht.cellattack.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import de.bht.cellattack.helper.AlertHelper;
import de.bht.cellattack.helper.Validator;
import de.bht.cellattack.model.dto.RestApi;
import de.bht.cellattack.model.dto.User;
import de.bht.cellattack.model.dto.UserLoginResponse;

/**
 * Login Class
 * to login a user at the backend-server
 * 
 * @author Fredi Benko
 */
public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML private Button loginButton;
    @FXML private Button backButton;

    Window window;
    User playerRef;

    /**
     * Constructor
     * 
     * @param x
     */
    public LoginController(User x) {
        playerRef = x;
    }

    
    /** 
     * Method login() makes a HTTP-request to login a user
     * 
     * @throws Exception
     */
    @FXML
    private void login() throws Exception {
        System.out.println("PlayerRef: " + playerRef);

        window = loginButton.getScene().getWindow();
        if (this.isValidated()) {

            UserLoginResponse loginResponse = RestApi.getLoginToken(email.getText(), password.getText());
            if (loginResponse == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                        "Email / Passwort falsch!");
                throw new Exception("Email / Passwort falsch!");
            }
            System.out.println("Login erfolgreich");
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information",
                    "Login erfolgreich");
            // save token
            System.out.println(loginResponse.getJwt());
            playerRef.setToken(loginResponse.getJwt());

            // change pane
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();

            DashboardFXController ctrl = new DashboardFXController(playerRef);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DashboardView.fxml"));
			fxmlLoader.setController(ctrl);
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();

        } else {
            System.out.println("Es ist ein Fehler beim einloggen aufgetreten");
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Es ist ein Fehler bei der Eingabe aufgetreten.");
        }

    }

    
    /** 
     * method isValidated() checks if the user input ist valid
     * 
     * @return boolean
     */
    private boolean isValidated() {

        window = loginButton.getScene().getWindow();
        if (email.getText().equals("")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Email Textfeld kann nicht leer sein.");
            email.requestFocus();
        } else if (!Validator.isValidEmail(email.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Eingabe im Email Textfeld ist keine valide E-Mail.");
            email.requestFocus();
        } else if (email.getText().length() > 40) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Email kann maximal 40 Zeichen lang sein.");
            email.requestFocus();
        } else if (password.getText().equals("")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Passwort Textfeld kann nicht leer sein.");
            password.requestFocus();
        } else if (password.getText().length() < 5 || password.getText().length() > 20) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Passwort muss zwischen 5 und 20 Zeichen lang sein.");
            password.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    
    /** 
     * Method changes the displayed form from login to register
     * 
     * @throws IOException
     */
    @FXML
    private void showRegisterStage() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegisterView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("User Registration");
        stage.show();
    }

    /**
     * Method to change view back to caller
     * 
     * @param e
     * @throws IOException
     */
    @FXML
    private void back(ActionEvent e) throws IOException {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = stage.getScene();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/avatarFX.fxml"));
            Parent root = (Parent) fxmlLoader.load(); 
            scene.setRoot(root);
    }
    

}
