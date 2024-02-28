package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

    @FXML
    Tab MoviesTab;

    @FXML
    Tab TvShowsTab;

    @FXML
    Tab AdsTab;

    public void initialize(URL location, ResourceBundle resources) {
        loadTabContent("MoviesTab.fxml", MoviesTab);
        loadTabContent("TvShowsTab.fxml", TvShowsTab);
        loadTabContent("AdsTab.fxml", AdsTab);
    }

    private void loadTabContent(String fxmlFileName, Tab tab) {
        try {
            // Load the FXML file into an AnchorPane
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            AnchorPane anch = loader.load();

            // Set the content of the Tab to the loaded AnchorPane
            tab.setContent(anch);
        } catch (IOException iex) {
            System.out.println("File " + fxmlFileName + " not found");
            iex.printStackTrace();
        }
    }
}
