package world.ucode.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import world.ucode.Database;

import java.util.Objects;

import static world.ucode.GameGeometry.*;

public class NewScene extends AbstractScene {

    public NewScene(String str) {
        String css = scenePath;
        if (Objects.equals(Database.getThemeSettings(), "Standart")) {
            css += "Game.css";
        } else {
            css += "Dark.css";
        }
        String theme = getClass().getResource(css).toExternalForm();
        try {
            FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource(scenePath + str));
            Parent root = fxmlLoad.load();
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(theme);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(userError);
        }
    }
}
