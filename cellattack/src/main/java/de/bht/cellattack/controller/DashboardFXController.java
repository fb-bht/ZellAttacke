package de.bht.cellattack.controller;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
import kong.unirest.json.JSONObject;
import de.bht.cellattack.application.Main;
import de.bht.cellattack.application.MainPanel;
import de.bht.cellattack.helper.AlertHelper;
import de.bht.cellattack.helper.Validator;

/**
 * DashboardFXController Class
 * handles the User Dashboard View
 * 
 * @author Fredi Benko
 */
public class DashboardFXController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button btnVirus;
    @FXML
    private Button btnCell;
 
    Window window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CompletableFuture<HttpResponse<JsonNode>> reqResponse = Unirest
                    .get(MessageFormat.format("{0}/checkUser", Main.SERVER_URL))
                    .header("Authorization", MessageFormat.format("Bearer {0}", LoginController.token))
                    .asJsonAsync();

            if (reqResponse.get().getStatus() == 200) {
                System.out.println(reqResponse.get().getBody().getObject().isEmpty() );
                System.out.println(LoginController.token);
            } else {
                System.out.println("Es ist etwas schiefgelaufen!");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Es ist etwas schiefgelaufen!");
            e.printStackTrace();
        }
    }

    public DashboardFXController() {
    }

    @FXML
    private void logout() throws IOException {
        window = logoutButton.getScene().getWindow();

        System.out.println("Logout erfolgreich");
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, window, "Information", "Logout erfolgreich");
        // logout am Server
        showLoginStage();
    }

    /*
     * Method changes the display from dashboard to login form
     */
    private void showLoginStage() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("User Login");
        stage.show();
    }
}