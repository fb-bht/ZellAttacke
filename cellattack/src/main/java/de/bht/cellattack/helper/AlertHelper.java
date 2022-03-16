package de.bht.cellattack.helper;

import org.controlsfx.control.Notifications;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * AlertHelper Class
 */
public class AlertHelper {

    public static boolean result = false;

    
    /** 
     * @param alertType
     * @param owner
     * @param title
     * @param message
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        if (alertType.name() == null ? AlertType.INFORMATION.name() == null : alertType.name().equals(AlertType.INFORMATION.name())) {
            Notifications.create()
                    .darkStyle()
                    .title(title)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showInformation();
        } else if (alertType.name() == null ? Alert.AlertType.ERROR.name() == null : alertType.name().equals(Alert.AlertType.ERROR.name())) {
            Notifications.create()
                    .darkStyle()
                    .title(title)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showError();
        }
    }
}