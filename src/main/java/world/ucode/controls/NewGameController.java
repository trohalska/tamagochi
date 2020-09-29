package world.ucode.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import world.ucode.Model;
import world.ucode.Tamagochi;
import world.ucode.scenes.NewScene;

public class NewGameController {
//    private Model pet = Tamagochi.getPet();

    public Button playButton;
    public Button menuButton;
    public TextField petNameBox;

    @FXML
    public void handlePlayButton(ActionEvent event) {
//        pet.setName(petNameBox.getText());
        System.out.println(petNameBox.getText());
//        Tamagochi.setPet(pet);
        (new NewScene("PlayGame.fxml")).setScene();
    }

    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new NewScene("MainMenu.fxml")).setScene();
    }


}
