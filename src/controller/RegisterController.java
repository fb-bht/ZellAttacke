package controller;

import helper.AlertHelper;
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
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

/**
 * Register Class
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public RegisterController() {
    }

    @FXML
    private void register() {
        window = registerButton.getScene().getWindow();

        // String host = "localhost";
        // String port = "8080";

        // try {
        // String authToken = Unirest.post(MessageFormat.format("http://{0}:{1}/signup",
        // host, port))

        String authToken = Unirest.post("localhost:8080/signup")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(new JSONObject()
                        .put("password", "kennword")
                        .put("email", "usereId@test.de")
                        .toString())
                .asJson()
                .getBody().getObject().getString("token");

        // HttpResponse<JsonNode> apiResponse =
        // Unirest.post("http://localhost:8080/signup")
        // .header("accept", "application/json")
        // .header("Content-Type", "application/json")
        // .field("password", "meinpasswort")
        // .field("email", "Gary@test.de")
        // .asJson();//.getBody().getArray()
        // .getBody().getObject().getString("token");

        // } catch (UnirestException e) {
        // e.printStackTrace();
        // }
        // return null;

        if (true) {
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information",
                    "You have registered successfully.");
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Something went wrong.");
        }
    }

    private boolean isAlreadyRegistered() {
        boolean usernameExist = false;

        // try {
        // usernameExist = true;
        // } catch (UnirestException e) {
        // System.out.println(e);
        // }
        return usernameExist;
    }

    private boolean isValidated() {

        window = registerButton.getScene().getWindow();
        if (email.getText().equals("")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Email text field cannot be blank.");
            email.requestFocus();
        } else if (email.getText().length() < 5 || email.getText().length() > 45) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Email text field cannot be less than 5 and greator than 45 characters.");
            email.requestFocus();
        } else if (password.getText().equals("")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Password text field cannot be blank.");
            password.requestFocus();
        } else if (password.getText().length() < 5 || password.getText().length() > 25) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "Password text field cannot be less than 5 and greator than 25 characters.");
            password.requestFocus();
        } else if (isAlreadyRegistered()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Error",
                    "The username is already taken by someone else.");
            email.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    private boolean clearForm() {
        email.clear();
        password.clear();
        return true;
    }

    @FXML
    private void showLoginStage() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("User Login");
        stage.show();
    }
}