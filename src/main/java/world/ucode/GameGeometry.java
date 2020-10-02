package world.ucode;

public class GameGeometry {

    public static final String scenePath = "/world/ucode/controls/";
    public static final String userError = "user error";
    public static final int maxVital = 10;

    /** choose animal */
    public static final String catImgPath = "/img/cat/";
    public static final String catBasicSound = "sounds/kitty.wav";
    public static final String bearImgPath = "/img/bear/";
    public static final String bearBasicSound = null;

    public static String setPetImgPath(String type) {
        return(switch (type) {
            case "Cat" -> catImgPath;
            default -> bearImgPath;
        });
    }
    public static String setPetBasicSound(String type) {
        return(switch (type) {
            case "Cat" -> catBasicSound;
            default -> bearBasicSound;
        });
    }

    /** TIMER to change vitals in seconds */
    public static final int PassTimeChangeVitals = 40;
    public static final int SleepTimeChangeVitals = 30;
    public static final int AgingTime = 120;
}
