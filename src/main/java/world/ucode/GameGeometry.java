package world.ucode;

public class GameGeometry {

    public static final String scenePath = "/world/ucode/controls/";
//    public static final String imgPath = "img/";
    public static final String userError = "user error";
    public static final int maxVital = 10;

    /** choose animal */
    public static final String catImgPath = "/img/cat/";
    public static final String catBasicSound = "sounds/kitty.wav";
    public static final String bearImgPath = "/img/bear/";
    public static final String bearBasicSound = null;

    /** TIMER to change vitals in seconds */
    public static final int PassTimeChangeVitals = 40;
    public static final int SleepTimeChangeVitals = 30;
    public static final int AgingTime = 120;
    public static final int timeDur = 5;

    /** CHARACTER TYPE */
    public enum CharacterType {
        CAT,
        BEAR
    }

    public static CharacterType setType(int t) {
        return(switch (t) {
            case 0 -> CharacterType.CAT;
            default -> CharacterType.BEAR;
        });
    }
    public static int getType(CharacterType t) {
        return(switch (t) {
            case CAT -> 0;
            case BEAR -> 1;
        });
    }
    public static int getTypeStr(String t) {
        return(switch (t) {
            case "Cat" -> 0;
            default -> 1;
        });
    }
}
