package world.ucode;

public class Model {
    private int hungry;
    private int happy;
    private int clean;
    private int tired;
    private int maxLevel;

    public Model() {
        clean = tired = 0;
        happy = hungry = maxLevel / 4;
    }

    public void feed() {
        hungry -= maxLevel / 2;
        if (hungry < 0) {
            hungry = 0;
            // dies ?????
        }
    }

    public void walk() {
        happy += maxLevel / 3;
        tired += maxLevel / 4;

        happy = happy >= maxLevel ? maxLevel : happy;
        tired = tired >= maxLevel ? maxLevel : tired;
    }

    public void clean() {
        clean = 0;
    }

    public void pet() {
        happy += maxLevel / 2;
        happy = happy >= maxLevel ? maxLevel : happy;
    }

    public String getMood() {
        if (tired >= 3 * maxLevel / 4) {
            return "asleep";
        } else if (tired >= 2 * maxLevel / 3) {
            return "tired";
        } else if (hungry >= 2 * maxLevel / 3) {
            return "hungry";
        } else if (clean >= maxLevel / 3) {
            return "dirty";
        } else if (happy >= 2 * maxLevel / 3) {
            return "happy";
        } else if (happy >= maxLevel / 3) {
            return "ok";
        } else {
            return "sad";
        }
    }

    public void passTime() {
        hungry++;
        clean++;
        tired = (tired >= maxLevel) ? 0 : tired++;
        happy = (hungry >= 2 * maxLevel / 3) ? happy-- : happy;
        happy = (clean >= 2 * maxLevel / 3) ? happy-- : happy;

        happy = happy < 0 ? 0 : happy;
        tired = tired >= maxLevel ? maxLevel : tired;
        hungry = hungry >= maxLevel ? maxLevel : hungry;
        clean = clean >= maxLevel ? maxLevel : clean;
    }
}
