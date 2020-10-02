package world.ucode.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import world.ucode.Database;
import world.ucode.scenes.NewScene;

public class NewGameController{

    public TextField petNameBox;
    public ChoiceBox petTypeBox;

    public Button playButton;
    public Button menuButton;

    @FXML
    public void handlePlayButton(ActionEvent event) {
        Database.insertNewDB(petNameBox.getText(), (String)petTypeBox.getValue(), 10);
        (new NewScene("PlayGame.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new NewScene("MainMenu.fxml")).setScene();
    }
}
