package de.bht.cellattack.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import kong.unirest.json.JSONObject;
import de.bht.cellattack.application.Main;
import de.bht.cellattack.helper.AlertHelper;
import de.bht.cellattack.helper.Validator;


/**
 * Login Class
 * to login a user at the backend-server
 * 
 * @author Fredi Benko
 */
public class LoginController {

    // for Development - delete later !!!!!
    public static String token;

    @FXML
    private TextField email;


    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    Window window;


    /*
     * Method login() makes a HTTP-request to login a user
     */
    @FXML
    private void login() throws Exception {

        window = loginButton.getScene().getWindow();
        if (this.isValidated()) {

            CompletableFuture<HttpResponse<JsonNode>> reqResponse = Unirest
                    .post(MessageFormat.format("{0}/signin", Main.SERVER_URL))
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .body(new JSONObject()
                            .put("password", password.getText())
                            .put("usernameOrEmail", email.getText())
                            .toString())
                    .asJsonAsync();

            if (reqResponse.get().getStatus() == 200) {
                System.out.println("Login erfolgreich");
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information",
                        "Login erfolgreich");
                // save token
                System.out.println(reqResponse.get().getBody().getObject().getString("jwt"));
                LoginController.token = reqResponse.get().getBody().getObject().getString("jwt");
                
                // change pane
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

                //Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashboardView.fxml"));
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/avatarchoiceFX.fxml"));

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Dashboard");
                stage.show();

            } else {
                System.out.println("Email / Passwort falsch!");
                AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                        "Email / Passwort falsch!");
            }

        } else {
            System.out.println("Es ist ein Fehler beim einloggen aufgetreten");
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Es ist ein Fehler bei der Eingabe aufgetreten.");
        }

    }


    /*
     * method isValidated() checks if the user input ist valid
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



    /*
     * Method changes the displayed form from login to register
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


}

