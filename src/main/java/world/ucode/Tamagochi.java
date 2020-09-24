package world.ucode;

import javafx.stage.Stage;
import javafx.application.Application;


public class Tamagochi extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Tamagochi");
//        primaryStage.setResizable(false);
//        primaryStage.centerOnScreen();
        (new MainMenuScene()).setScene(primaryStage);
        primaryStage.show();
    }
    public static Stage getPrimaryStage() {
        return stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
