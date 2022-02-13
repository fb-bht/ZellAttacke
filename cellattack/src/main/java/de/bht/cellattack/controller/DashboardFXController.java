package de.bht.cellattack.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import de.bht.cellattack.helper.AlertHelper;
import de.bht.cellattack.model.dto.Score;
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
    private Button logoutButton;
    @FXML
    private Button btnBack;
   
    @FXML private TableView<Score> table;
    @FXML private TableColumn<Score, Integer> columnOne;
    @FXML private TableColumn<Score, String> columnTwo;
    
    Window window;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserApiResponse userResponse = null;
        userResponse = RestApi.getUserFromAPI();
        if(userResponse == null) {
            throw new RuntimeException("Es ist etwas schiefgelaufen. Bitte in einiger Zeit noch einmal versuchen.");
        }
        // fill TableView with Score-data from the backend-server
        List<Score> apiscores = userResponse.getUser().getScores();
       columnOne.setCellValueFactory(new PropertyValueFactory<Score, Integer>("score"));
       columnTwo.setCellValueFactory(new PropertyValueFactory<Score, String>("date"));
       for (Score tmpscore : apiscores){
           table.getItems().add(tmpscore);
       }
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
    
    @FXML
    protected void backToAvatarLogin(ActionEvent e) throws Exception {
    	if (btnBack.isPressed() == true) {
    		
    		Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginCellFX.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setTitle("CellAttack");
            stage.show();
    	} else {
            Stage stage2 = (Stage) btnBack.getScene().getWindow();
            stage2.close();
            
            Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/LoginVirusFX.fxml"));
            
            Scene scene2 = new Scene(root2);
            
            stage2.setScene(scene2);
            stage2.setTitle("CellAttack");
            stage2.show();
    	}
    	
    }


    public DashboardFXController() {
    }
    
}