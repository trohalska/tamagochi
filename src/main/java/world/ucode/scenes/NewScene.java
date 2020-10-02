package world.ucode.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import world.ucode.Database;

import static world.ucode.GameGeometry.*;

public class NewScene extends AbstractScene {
    private Parent root;
    private FXMLLoader fxmlLoad;
    private String path = scenePath;

    private String theme;
    private String css = scenePath;

    public NewScene(String str) {
        if (Database.getThemeSettings().equals("Standart")) {
            css += "Game.css";
        } else {
            css += "Dark.css";
        }
        theme = getClass().getResource(css).toExternalForm();
        try {
            fxmlLoad = new FXMLLoader(getClass().getResource(path + str));
            root = fxmlLoad.load();
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(theme);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(userError);
        }
    }
}
