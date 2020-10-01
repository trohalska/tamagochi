package world.ucode.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import world.ucode.scenes.NewScene;

public class MainMenuController {

    public Button playButton;
    public Button loadButton;
    public Button settingsButton;
    public Button exitButton;

    @FXML
    public void handleNewButtonAction(ActionEvent event) { (new NewScene("NewGame.fxml")).setScene(); }

    @FXML
    public void handleLoadButtonAction(ActionEvent event) {
        (new NewScene("LoadGame.fxml")).setScene();
    }

    @FXML
    public void handleSettingsButtonAction(ActionEvent event) {
        System.out.println("settings");
    }

    @FXML
    public void handleExitButtonAction(ActionEvent event) { System.exit(0); }

}
