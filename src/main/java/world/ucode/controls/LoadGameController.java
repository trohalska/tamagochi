package world.ucode.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import world.ucode.Database;
import world.ucode.scenes.NewScene;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadGameController implements Initializable {
    public ChoiceBox<String> petTypeBox;
    public Button playButton;
    public Button menuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> listNames = Database.selectNames();
        if (listNames.isEmpty()) {
            playButton.setDisable(true);
        } else {
            ObservableList<String> names = FXCollections.observableArrayList(listNames);
            petTypeBox.setItems(names);
            petTypeBox.setValue(listNames.get(0));
        }
    }

    @FXML
    public void handlePlayButton() {
        String name = petTypeBox.getValue();
        int id = Integer.parseInt(name.substring(0, name.indexOf('.')));
        Database.updateActive(id);
        (new NewScene("PlayGame.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton() {
        (new NewScene("MainMenu.fxml")).setScene();
    }

}
