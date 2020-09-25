package world.ucode;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractScene implements InterfaceScene {
    protected Scene scene = null;

    public void setScene() {
        Stage primaryStage = Tamagochi.getPrimaryStage();
        primaryStage.setScene(scene);
    }
    public Scene getScene() {
        return scene;
    }
}
