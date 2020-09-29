package world.ucode.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import static world.ucode.GameGeometry.*;

public class NewScene extends AbstractScene {
    private Parent root;
    private FXMLLoader fxmlLoad;
    private String path = scenePath;

    public NewScene(String str) {
        try {
            fxmlLoad = new FXMLLoader(getClass().getResource(path + str));
            root = fxmlLoad.load();
            this.scene = new Scene(root);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(userError);
        }
    }
}
