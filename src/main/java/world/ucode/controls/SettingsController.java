package world.ucode.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import world.ucode.Database;
import world.ucode.scenes.NewScene;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    public ChoiceBox soundSettings;
    public ChoiceBox themeSettings;
    public ChoiceBox difficultySettings;

    public Button saveButton;
    public Button menuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        soundSettings.setValue(Database.getSoundSettings());
        themeSettings.setValue(Database.getThemeSettings());
        difficultySettings.setValue((Database.getDifficultySettings()));
    }

    @FXML
    public void handleSaveButton(ActionEvent event) {
        String sound = (String)soundSettings.getValue();
        String theme = (String)themeSettings.getValue();
        String diff = (String)difficultySettings.getValue();
        Database.updateSettings(sound, theme, diff);
        (new NewScene("Settings.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new NewScene("MainMenu.fxml")).setScene();
    }
}
