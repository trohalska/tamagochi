package world.ucode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuScene extends AbstractScene {
    private Parent root;
    private FXMLLoader fxmlLoad;

    public MainMenuScene() {
        try {
            fxmlLoad = new FXMLLoader(getClass().getResource("/world/ucode/MainMenu.fxml"));
            root = fxmlLoad.load();
            this.scene = new Scene(root);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error");
        }
    }
}
