package Controllers_Tabs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TabsUtilitys {
	

	public static void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

	

}
