package world.ucode.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import world.ucode.scenes.NewScene;

public class MainMenuController {

    public Button playButton;
    public Button loadButton;
    public Button settingsButton;
    public Button exitButton;

    @FXML
    public void handleNewButtonAction() { (new NewScene("NewGame.fxml")).setScene(); }

    @FXML
    public void handleLoadButtonAction() {
        (new NewScene("LoadGame.fxml")).setScene();
    }

    @FXML
    public void handleSettingsButtonAction() {
        (new NewScene("Settings.fxml")).setScene();
    }

    @FXML
    public void handleExitButtonAction() { System.exit(0); }

}
