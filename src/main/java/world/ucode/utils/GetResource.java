package world.ucode.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import world.ucode.Animation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

//import static world.ucode.GameGeometry.imgPath;
import static world.ucode.GameGeometry.scenePath;

public class GetResource {
    public static void playSound(URL url) {
        try {
//            URL url = this.getClass().getClassLoader().getResource("sounds/" + str);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(str));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public static void playMP3(String bip) {
        try {
//            String bip = "bip.mp3";
            Media hit = new Media(new File(bip).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
        catch(Exception ex) {
            System.out.println("Error with playing mp3.");
            ex.printStackTrace();
        }
    }
    public static void getImage(ImageView v, String str) {
//        v.setImage(new Image(imgPath + str));
    }
}
