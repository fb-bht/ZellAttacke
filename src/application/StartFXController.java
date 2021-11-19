package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartFXController {

	@FXML
	private Button newGame, gamerules;
	
	Button []button = new Button[2];
	
	public void openNewGame(ActionEvent e) {
		if (e.getSource() == button[0]) {
			//...
		
		}
	}
	
	 /* public void howToPlay()
	 * 
	 * }
	 */
	
	public void initialize() {
		for (int i = 0; i<button.length; i++) {
			button[i] = new Button();
			button[i].setOnAction(this::openNewGame);
		}
	}
}
