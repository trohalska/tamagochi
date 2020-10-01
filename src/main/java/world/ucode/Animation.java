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
    private String imgPath;
    private String basicSound;

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public void setBasicSound(String basicSound) {
        this.basicSound = basicSound;
    }

    public Animation(String imgPath, String basicSound) {
        setImgPath(imgPath);
        setBasicSound(basicSound);
    }

    public URL getUrl(String s) {
        return this.getClass().getClassLoader().getResource(s);
    }

    private void output(ImageView v, String img, String sound) {
        v.setImage(new Image(img));
        if (sound != null)
            GetResource.playSound(getUrl(sound));
    }

    private void outputLong(ImageView v, String img, String sound) {

        v.setImage(new Image(img));
        Timeline time = new Timeline(
                new KeyFrame( Duration.seconds(2), event -> {
                    GetResource.playSound(getUrl(sound));
                } )
        );
        time.setCycleCount(5);
        time.play();
    }
    private void soundTimeout(String sound, int delay, int cycle) {
        Timeline time = new Timeline(
                new KeyFrame( Duration.seconds(delay), event -> {
                    GetResource.playSound(getUrl(sound));
                } )
        );
        time.setCycleCount(cycle);
        time.play();
    }
    /**
     * temporary phisical actions
     * */
    public void born(ImageView v) {
        output(v, imgPath+"born.gif", "sounds/heartbeat.wav");
    }
    public void deadSound() {
        GetResource.playSound(getUrl("sounds/dubbio.wav"));
    }
    public void eat(ImageView v, String mood) {
        output(v, imgPath+"eat4.gif", basicSound);
//        outputReactionOnMood(v, mood);
    }
    public void drink(ImageView v, String mood) {
        output(v, imgPath+"eat3.gif", basicSound);
//        outputReactionOnMood(v, mood);
    }
    public void play(ImageView v, String mood) {
        output(v, imgPath+"play.gif", basicSound);
//        outputReactionOnMood(v, mood);
    }
    public void pet(ImageView v, String mood) {
        output(v, imgPath+"pet.gif", basicSound);
//        outputReactionOnMood(v, mood);
    }
    public void clean(ImageView v, String mood) {
        output(v, imgPath+"clean.gif", basicSound);
//        outputReactionOnMood(v, mood);
    }
    public void meds(ImageView v, String mood) {
        output(v, imgPath+"boom.gif", basicSound);
//        outputReactionOnMood(v, mood);
    }

    /**
     * long standing reactions
     * */
    public void outputReactionOnMood(ImageView v, String mood) {
        reactionOnGetMood(v, mood);
//        (new Timeline(
//                new KeyFrame( Duration.seconds(3), event -> {
//                    reactionOnGetMood(v, mood);
//                } )
//        )).play();
    }
    public void reactionOnGetMood(ImageView v, String mood) {
        if (mood.contains("asleep")) {
            sleep(v);
        } else if (mood.contains("sick")) {
            sneeze(v);
        } else if (mood.contains("tired")) {
            tired(v);
        } else if (mood.contains("hungry")) {
            hungry(v);
        } else if (mood.contains("thirsty")) {
            thirsty(v);
        } else if (mood.contains("dirty")) {
            dirty(v);
        } else if (mood.contains("love")) {
            happy(v);
        } else if (mood.contains("ok")) {
            ok(v);
        } else {
            angry(v);
        }
    }

    public void tired(ImageView v) { output(v, imgPath+"tired.gif", "sounds/yawn.wav"); }
    public void hungry(ImageView v) {
        output(v, imgPath+"hungry.gif", basicSound);
    }
    public void thirsty(ImageView v) {
        output(v, imgPath+"thirsty.gif", basicSound);
    }
    public void dirty(ImageView v) {
        output(v, imgPath+"dirty.gif", "sounds/cry.wav");
    }
    public void ok(ImageView v) {
        output(v, imgPath+"normal.gif", basicSound);
    }
    public void happy(ImageView v) {
        output(v, imgPath+"happy.gif", basicSound);
    }
    public void angry(ImageView v) {
        output(v, imgPath+"unhappy.gif", basicSound);
    }
    public void sleep(ImageView v) {
        outputLong(v, imgPath+"sleep.gif", "sounds/snore.wav");
    }
    public void sneeze(ImageView v) {
        output(v, imgPath+"sick.gif", "sounds/sneeze.wav");
        soundTimeout("sounds/cough.wav", 2, 1);
    }
}