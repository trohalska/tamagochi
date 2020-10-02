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
import world.ucode.GameGeometry;
import world.ucode.Model;
import world.ucode.scenes.NewScene;

import static world.ucode.GameGeometry.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private Timeline passTimeline;
    private Timeline ageTimeline;
    private Timeline sleepTimeline;
    private Timeline sickTimeline;

    private Model pet;
    private Animation animation;
    private int difficulty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pet = new Model();
        animation = new Animation(GameGeometry.setPetImgPath(pet.getType()),
                                  GameGeometry.setPetBasicSound(pet.getType()));
        setDifficulty(Database.getDifficultySettings());

        mainLabel.setText(pet.getName());
        vitalsLabel.setText(pet.getVitals());

        passTimeline = setPassTimeline();
        ageTimeline = setAgeTimeline();
        sleepTimeline = setSleepTimeline();
        sickTimeline = setSickTimeline();

        passTimeline.setCycleCount(Timeline.INDEFINITE);
        ageTimeline.setCycleCount(Timeline.INDEFINITE);

        passTimeline.play();
        ageTimeline.play();

        if (pet.getTime().equals("0")) {
            firstLabel.setText("You have a new pet!");
            secondLabel.setText("Hiiii! Now i'm yours ))");
            animation.born(imgView);
        } else {
            timeManager(pet);
            firstLabel.setText("Your pet is " + pet.getAge() + " years old!");
            renewVitals();
        }
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
        stopAllActions();
        Database.saveModel(pet);
        (new NewScene("MainMenu.fxml")).setScene();
    }

    private void setDifficulty(String diffSettings) {
        if (diffSettings.equals("Easy")) {
            difficulty = 1;
        } else if (diffSettings.equals("Normal")) {
            difficulty = 2;
        } else {
            difficulty = 3;
        }
    }

    private Timeline setPassTimeline() {
        return new Timeline(
                new KeyFrame(
                        Duration.seconds(PassTimeChangeVitals / difficulty),
                        event -> {
                            pet.passTime();
                            renewVitals();
                        }
                )
        );
    }
    private Timeline setAgeTimeline() {
        return new Timeline(
                new KeyFrame(
                        Duration.seconds(AgingTime / difficulty),
                        event -> {
                            pet.aging();
                            firstLabel.setText("Your pet is " + pet.getAge() + " years old!");
                        }
                )
        );
    }
    private Timeline setSleepTimeline() {
        return new Timeline(
                new KeyFrame(
                        Duration.seconds(SleepTimeChangeVitals / difficulty),
                        event -> {
                            pet.sleepTime();
                            renewVitals();
                            ButtonsOn();
                            passTimeline.play();
                        }
                )
        );
    }
    private Timeline setSickTimeline() {
        return new Timeline(
                new KeyFrame(
                        Duration.seconds(SleepTimeChangeVitals / difficulty),
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
    }

    private void IfAsleep() {
        if (pet.isSleep()) {
            ButtonsOff();
            passTimeline.stop();
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
            stopAllActions();
            animation.deadSound();
            Database.deleteModel(pet.id);
            (new NewScene("GameOver.fxml")).setScene();
        }
    }

    private void renewVitals() {
        IfDead();
        IfSick();
        IfAsleep();
        String mood = pet.getMood();
        secondLabel.setText(mood);
        animation.outputReactionOnMood(imgView, mood);
        vitalsLabel.setText(pet.getVitals());
    }

    private void stopAllActions() {
        passTimeline.stop();
        ageTimeline.stop();
        sleepTimeline.stop();
        sickTimeline.stop();
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

    private void timeManager(Model pet) {
        SimpleDateFormat formatter = new SimpleDateFormat("y-M-d h:m:s");

        try {
            Date d = formatter.parse(pet.getTime());
            float timeDiff = (System.currentTimeMillis() - d.getTime()) / 1000F;
//            System.out.println(timeDiff + " seconds");
            int counter = (int)timeDiff / (PassTimeChangeVitals / difficulty);
            while (counter > 0) {
                pet.passTime();
                counter--;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
