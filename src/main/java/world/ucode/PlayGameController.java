package world.ucode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayGameController implements Initializable {

    public Button feedButton;
    public Button walkButton;
    public Button cleanButton;
    public Button menuButton;
    public Label secondLabel;
    public Label mainLabel;
    public GridPane gridPane;

    private Model pet = new Model();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainLabel.setText("New pet");
        secondLabel.setText(pet.getMood());
        pet.getAll();
    }

    @FXML
    public void handleFeedButton(ActionEvent event) {
        pet.feed();
        System.out.println("feed");
        doAll();
    }
    @FXML
    public void handleWalkButton(ActionEvent event) {
        pet.walk();
        System.out.println("walk");
        doAll();
    }
    @FXML
    public void handleCleanButton(ActionEvent event) {
        pet.clean();
        System.out.println("clean");
        doAll();
    }
    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new MainMenuScene()).setScene();
    }

    private void checkIfAsleep() {
        if (pet.isSleep()) {
            feedButton.setVisible(false);
            walkButton.setVisible(false);
            cleanButton.setVisible(false);
        }
    }

    private void doAll() {
        secondLabel.setText(pet.getMood());
        pet.getAll();
        pet.passTime();
        checkIfAsleep();
    }
}
