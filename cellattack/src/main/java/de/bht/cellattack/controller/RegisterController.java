package de.bht.cellattack.controller;


import de.bht.cellattack.helper.AlertHelper;
import de.bht.cellattack.helper.Validator;
import de.bht.cellattack.model.dto.ApiResponse;
import de.bht.cellattack.model.dto.RestApi;
import de.bht.cellattack.model.dto.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * Register Class
 * to register a new user at the backend-server
 * 
 * @author Fredi Benko
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button registerButton;

    Window window;
    User playerRef;

    
    /** 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Constructor
     */
    public RegisterController() {
    }

    public RegisterController(User user) { //TODO
        playerRef = user;
    }

    
    /** 
     * Method register() makes a HTTP-request to register a new user
     * 
     * @throws Exception
     */
    @FXML
    private void register() throws Exception {
        window = registerButton.getScene().getWindow();
        if (this.isValidated()) {
            ApiResponse registerResponse = RestApi.sentNewUserToApi(email.getText(), password.getText());
            if (registerResponse == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                        "Es ist ein Fehler beim registrieren aufgetreten");
                throw new Exception("Es ist ein Fehler beim registrieren aufgetreten");
            }
            if (registerResponse.getSuccess()) {
                this.clearForm();
                System.out.println("Registrierung erfolgreich abgeschlossen.");
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information",
                        "Registrierung erfolgreich abgeschlossen.");
                this.showLoginStage();
            } else if (!registerResponse.getSuccess()
                    || registerResponse.getMessage().toString()
                            .equals("Email Address already in use!")) {
                System.out.println("Der Benutzername ist bereits vergeben");
                AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                        "Der Benutzername ist bereits vergeben.");
            } 

        } else {
            System.out.println("Es ist ein Fehler beim registrieren aufgetreten");
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

        window = registerButton.getScene().getWindow();
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
     * Method clears form input
     * 
     * @return boolean
     */
    private boolean clearForm() {
        email.clear();
        password.clear();
        return true;
    }

    
    /** 
     * Method changes the displayed form from register to login
     * 
     * @throws IOException
     */
    @FXML
    private void showLoginStage() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        LoginController ctrl = new LoginController(playerRef);
        fxmlLoader.setController(ctrl);
        Parent root = fxmlLoader.load();
        
        // Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml")); //TODO
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("User Login");
        stage.show();
    }
    
}

