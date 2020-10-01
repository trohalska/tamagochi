package world.ucode.controls;

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
    private ArrayList<String> names = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        names = Database.selectNames();

        System.out.println(names.toString());
        // сформувати список загрузки (map[id,name])

    }

    public ChoiceBox petTypeBox;

    public Button playButton;
    public Button menuButton;

    @FXML
    public void handlePlayButton(ActionEvent event) {
        int i = petTypeBox.getSelectionModel().getSelectedIndex();

        // поставити active=1 для вибраного типа
//        Database.insertNewDB(name, type, 10);
        (new NewScene("PlayGame.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new NewScene("MainMenu.fxml")).setScene();
    }

}
