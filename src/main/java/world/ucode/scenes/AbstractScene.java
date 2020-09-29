package world.ucode.scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import world.ucode.Tamagochi;

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
