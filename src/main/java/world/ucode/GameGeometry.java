package world.ucode;

public class GameGeometry {

    public static final String scenePath = "/world/ucode/controls/";
//    public static final String imgPath = "img/";
    public static final String userError = "user error";
    public static final int maxVital = 10;

    /** TIMER to change vitals in seconds */
    public static final int PassTimeChangeVitals = 30;
    public static final int SleepTimeChangeVitals = 15;
    public static final int AgingTime = 120;
    public static final int timeDur = 5;

    /** CHARACTER TYPE */
    public enum CharacterType {
        CAT,
        DOG
    }
}
