package de.bht.cellattack.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import de.bht.cellattack.model.dto.Score;
import de.bht.cellattack.model.dto.User;
import de.bht.cellattack.model.dto.RestApi;
import de.bht.cellattack.model.dto.UserApiResponse;

/**
 * DashboardFXController Class
 * handles the User Dashboard View
 * 
 * @author Fredi Benko
 */
public class DashboardFXController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Score> table;

    @FXML
    private TableColumn<Score, Integer> columnOne;

    @FXML
    private TableColumn<Score, String> columnTwo;

    User playerRef;

    public DashboardFXController(User player) {
        this.playerRef = player;
    }

    
    /** 
     * Initialize Dashboard
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserApiResponse userResponse = null;
        String token = playerRef.getToken();
        userResponse = RestApi.getUserFromAPI(token);
        if (userResponse == null) {
            throw new RuntimeException("Es ist etwas schiefgelaufen. Bitte in einiger Zeit noch einmal versuchen.");
        }
        playerRef = userResponse.getUser();
        playerRef.setToken(token); 
        // fill TableView with Score-data from the backend-server
        List<Score> apiscores = userResponse.getUser().getScores();
        columnOne.setCellValueFactory(new PropertyValueFactory<Score, Integer>("score"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<Score, String>("date"));
        for (Score tmpscore : apiscores) {
            table.getItems().add(tmpscore);
        }
    }
    
    /** 
     * Method changes the display from dashboard back to AvatarSelection
     * 
     * @param e
     * @throws Exception
     */
    @FXML
    protected void backToAvatarFX(ActionEvent e) throws Exception {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/avatarFX.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("CellAttack");
            stage.show();
    }

}