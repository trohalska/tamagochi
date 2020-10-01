package world.ucode.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import world.ucode.Animation;
import world.ucode.Database;
import world.ucode.Model;
import world.ucode.scenes.NewScene;
import world.ucode.utils.GetResource;

import static world.ucode.GameGeometry.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayGameController implements Initializable {

    public Button feedButton;
    public Button giveWaterButton;
    public Button walkButton;
    public Button petButton;
    public Button cleanButton;
    public Button giveMedButton;
    public Button menuButton;

    public Label mainLabel;
    public Label firstLabel;
    public Label secondLabel;
    public Label vitalsLabel;

    public StackPane imgPane;
    public ImageView imgView;
    public GridPane gridPane;

    private Model pet;
    private Animation animation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pet = new Model();
        animation = new Animation(bearImgPath, bearBasicSound);

        mainLabel.setText(pet.getName());
        firstLabel.setText("Congrats! You have a new pet!");
        secondLabel.setText(pet.getMood());
        vitalsLabel.setText(pet.getVitals());

        timeline.setCycleCount(Timeline.INDEFINITE);
        ageTimeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        ageTimeline.play();

        animation.born(imgView);
//        imgView.setImage(new Image("/img/g.gif"));

//        ImageView image = new ImageView(new Image(imgPath + "g.gif"));
//        imgPane.getChildren().add(image);
//        imgPane.setAlignment(image, Pos.BOTTOM_CENTER);

    }

    @FXML
    public void handleFeedButton(ActionEvent event) {
//        animation.eat(imgView, pet.getMood());
        pet.feed();
        renewVitals();
    }
    @FXML
    public void handleGiveWaterButton(ActionEvent event) {
//        animation.drink(imgView, pet.getMood());
        pet.giveWater();
        renewVitals();
    }
    @FXML
    public void handleWalkButton(ActionEvent event) {
//        animation.play(imgView, pet.getMood());
        pet.walk();
        renewVitals();
    }
    @FXML
    public void handlePetButton(ActionEvent event) {
//        animation.pet(imgView, pet.getMood());
        pet.pet();
        renewVitals();
    }
    @FXML
    public void handleCleanButton(ActionEvent event) {
//        animation.clean(imgView, pet.getMood());
        pet.clean();
        renewVitals();
    }
    @FXML
    public void handleGiveMedButton(ActionEvent event) {
//        animation.meds(imgView, pet.getMood());
        pet.giveMedicine();
        renewVitals();
    }
    @FXML
    public void handleMenuButton(ActionEvent event) {
        (new NewScene("MainMenu.fxml")).setScene();
    }

    private Timeline timeline = new Timeline(
            new KeyFrame(
                    Duration.seconds(PassTimeChangeVitals),
                    event -> {
                        pet.passTime();
                        renewVitals();
                    }
            )
    );
    private Timeline ageTimeline = new Timeline(
            new KeyFrame(
                    Duration.seconds(AgingTime),
                    event -> {
                        pet.aging();
                        firstLabel.setText("Congrats! Your pet is now " + pet.getAge() + " years old!");
                    }
            )
    );
    private Timeline sleepTimeline = new Timeline(
            new KeyFrame(
                    Duration.seconds(SleepTimeChangeVitals),
                    event -> {
                        pet.sleepTime();
                        renewVitals();
                        ButtonsOn();
                        timeline.play();
                    }
            )
    );
    private Timeline sickTimeline = new Timeline(
            new KeyFrame(
                    Duration.seconds(SleepTimeChangeVitals),
                    event -> {
                        if (pet.isSickToDeath()) {
                            pet.isAlive = false;
                            IfDead();
                        } else {
                            pet.isSick = false;
                        }
                    }
            )
    );
//    private Timeline NewPet = new Timeline(
//        new KeyFrame(
//            Duration.seconds(timeDur),
//            event -> {
//                secondLabel.setText(pet.getMood());
//                timeline.play();
//            }
//        )
//    );

    private void IfAsleep() {
        if (pet.isSleep()) {
            ButtonsOff();
            timeline.stop();
            sleepTimeline.play();
        }
    }
    private void IfSick() {
        if (pet.isSick) {
            sickTimeline.play();
        }
    }
    private void IfDead() {
        if (pet.isAlive == false) {
            timeline.stop();
            sleepTimeline.stop();
            sickTimeline.stop();

            animation.deadSound();

            Database.updateDisactive();
            // delete from DB
            (new NewScene("GameOver.fxml")).setScene();
        }
    }

    private void renewVitals() {
        String mood = pet.getMood();
        secondLabel.setText(mood);
        animation.outputReactionOnMood(imgView, mood);
        vitalsLabel.setText(pet.getVitals());
        IfAsleep();
        IfSick();
        IfDead();
    }

    private void ButtonsOff() {
        feedButton.setDisable(true);
        giveWaterButton.setDisable(true);
        walkButton.setDisable(true);
        petButton.setDisable(true);
        cleanButton.setDisable(true);
        giveMedButton.setDisable(true);
    }
    private void ButtonsOn() {
        feedButton.setDisable(false);
        giveWaterButton.setDisable(false);
        walkButton.setDisable(false);
        petButton.setDisable(false);
        cleanButton.setDisable(false);
        giveMedButton.setDisable(false);
    }
}
