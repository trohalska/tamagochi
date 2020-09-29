package world.ucode;

import javafx.stage.Stage;
import javafx.application.Application;
import world.ucode.scenes.NewScene;

public class Tamagochi extends Application {
    public static Stage stage;

    public static Model pet;
//    public static Model getPet() {
//        return pet;
//    }
//    public static void setPet(Model pet1) {
//        pet = pet1;
//    }

    @Override
    public void start(Stage primaryStage) {
        pet = new Model();
        this.stage = primaryStage;
        primaryStage.setTitle("Tamagochi");
//        primaryStage.setResizable(false);
//        primaryStage.centerOnScreen();

        (new NewScene("PlayGame.fxml")).setScene();
//        (new NewScene("MainMenu.fxml")).setScene();
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return stage;
    }
    public static void main(String[] args) {
        launch(args);
    }


}
