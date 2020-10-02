package world.ucode.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import world.ucode.Database;
import world.ucode.GameGeometry;
import world.ucode.scenes.NewScene;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LoadGameController implements Initializable {
    private ArrayList<String> listNames = null;

    public ChoiceBox petTypeBox;
    public Button playButton;
    public Button menuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listNames = Database.selectNames();
        if (listNames.isEmpty()) {
            playButton.setDisable(true);
        } else {
            ObservableList<String> names = FXCollections.observableArrayList(listNames);
            petTypeBox.setItems(names);
            petTypeBox.setValue(listNames.get(0));
//            System.out.println(names.toString());
        }
    }

    @FXML
    public void handlePlayButton(ActionEvent event) {
        String name = (String)petTypeBox.getValue();
        int id = Integer.valueOf(name.substring(0, name.indexOf('.')));

        // поставити active=1 для вибраного типа
        Database.updateActive(id);

        (new NewScene("PlayGame.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new NewScene("MainMenu.fxml")).setScene();
    }

}
