package world.ucode;

public class GameGeometry {

    public static final String scenePath = "/world/ucode/controls/";
    public static final String userError = "user error";

    /** choose animal */
    public static final String catImgPath = "/img/cat/";
    public static final String catBasicSound = "sounds/kitty.wav";
    public static final String bearImgPath = "/img/bear/";
    public static final String bearBasicSound = null;

    public static String setPetImgPath(String type) {
        if (type.equals("Cat")) {
            return catImgPath;
        } else {
            return bearImgPath;
        }
    }
    public static String setPetBasicSound(String type) {
        if (type.equals("Cat")) {
            return catBasicSound;
        } else {
            return bearBasicSound;
        }
    }

    /** TIMER to change vitals in seconds */
    public static final int PassTimeChangeVitals = 40;
    public static final int SleepTimeChangeVitals = 30;
    public static final int AgingTime = 120;
}
