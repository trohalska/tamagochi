package world.ucode.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import world.ucode.Database;
import world.ucode.scenes.NewScene;

public class NewGameController {

    public TextField petNameBox;
    public ChoiceBox<String> petTypeBox;
    public TextField petHealthBox;

    public Button playButton;
    public Button menuButton;

    @FXML
    public void handlePlayButton() {
        String health = petHealthBox.getText();
        if (health.equals("")) {
            health = "10";
        }

        Database.insertNewDB(petNameBox.getText(),
                            petTypeBox.getValue(),
                            Integer.parseInt(health));
        (new NewScene("PlayGame.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton() {
        (new NewScene("MainMenu.fxml")).setScene();
    }
}
