package world.ucode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class PlayGameScene extends AbstractScene {
    private Parent root;
    private FXMLLoader fxmlLoad;
//    Model pet;
//
//    public Model getPet() {
//        return pet;
//    }

    public PlayGameScene() {
//        pet = new Model();
        try {
            fxmlLoad = new FXMLLoader(getClass().getResource("/world/ucode/PlayGame.fxml"));
            root = fxmlLoad.load();

//            javafx.scene.control.Label mainLabel = (javafx.scene.control.Label)fxmlLoad.getNamespace().get("mainLabel");
//            mainLabel.setText("New animal");

            this.scene = new Scene(root);


        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error");
        }
    }
}
