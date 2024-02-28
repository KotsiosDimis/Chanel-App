package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

 

    public static void Main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        UIManager uiManager = new UIManager();
        try {
			uiManager.startUI(primaryStage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   
}
 