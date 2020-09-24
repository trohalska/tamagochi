package world.ucode;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface InterfaceScene {
    Scene scene = null;

    void setScene(Stage primaryStage);
    Scene getScene();
}
