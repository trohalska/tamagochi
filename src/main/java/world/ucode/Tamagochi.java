package world.ucode;

import javafx.stage.Stage;
import javafx.application.Application;
import world.ucode.scenes.NewScene;

public class Tamagochi extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Tamagochi");
        (new NewScene("MainMenu.fxml")).setScene();
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return stage;
    }
    public static void main(String[] args) {
        Database.createNewDB();
        launch(args);
    }
}
