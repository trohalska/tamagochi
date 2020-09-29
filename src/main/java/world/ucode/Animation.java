package world.ucode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import world.ucode.utils.GetResource;
import java.net.URL;

import static world.ucode.GameGeometry.timeDur;

public class Animation {
//    private ImageView viev;
//
//    public Animation(ImageView v) {
//        this.v = v;
//    }

    public URL getUrl(String s) {
        return this.getClass().getClassLoader().getResource(s);
    }
    public void setNormal(ImageView v) {
        v.setImage(new Image("/img/simpleCat/happy.gif"));
    }

    public void born(ImageView v) {
        v.setImage(new Image("/img/simpleCat/born.gif"));
        (new Timeline(
            new KeyFrame( Duration.seconds(1), event -> {
                    GetResource.playSound(getUrl("sounds/kitty.wav"));
                } )
        )).play();
    }

    public void eat(ImageView v) {
        v.setImage(new Image("/img/simpleCat/eat1.gif"));
        GetResource.playSound(getUrl("sounds/kitty.wav"));
        (new Timeline(
            new KeyFrame( Duration.seconds(timeDur), event -> {
                setNormal(v);
            } )
        )).play();
    }
}
