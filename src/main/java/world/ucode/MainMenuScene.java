package world.ucode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuScene implements InterfaceScene {
    private Parent root;
    private FXMLLoader fxmlLoad;
    private Scene scene = null;

    public MainMenuScene() {
        try {
            fxmlLoad = new FXMLLoader(getClass().getResource("/world/ucode/MainMenu.fxml"));
            root = fxmlLoad.load();
//            NewGameScene scene = new NewGameScene();
            scene = new Scene(root);
        }
        catch (Exception e) {
            System.err.println("error");
        }
    }

    @Override
    public void setScene(Stage primaryStage) {
        primaryStage.setScene(scene);
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
