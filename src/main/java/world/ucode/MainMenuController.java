package world.ucode;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainMenuController {

    @FXML
    public Button playButton;
    @FXML
    public Button loadButton;
    @FXML
    public Button exitButton;

    @FXML
    public void handlePlayButtonAction(ActionEvent event) {
        try {
            Stage s = Tamagochi.getPrimaryStage();
//            Window s = playButton.getScene().getWindow();
//            Scene scene = playButton.getScene();

            FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("/world/ucode/PlayGame.fxml"));
            Scene scene = new Scene(fxmlLoad.load());
            s.setScene(scene);


            System.out.println("play");
        }
        catch (Exception e) {
            System.out.println("mainMenuController error");
        }
    }

    @FXML
    public void handleLoadButtonAction(ActionEvent event) {
        System.out.println("load");
    }

    @FXML
    public void handleExitButtonAction(ActionEvent event) { System.exit(0); }

}
