package world.ucode;

public class Model {
    private int full;
    private int happy;
    private int clean;
    private int tired;
    private int maxLevel;

    public Model() {
        maxLevel = 10;
        clean = tired = 0;
        happy = full = maxLevel / 4;
    }

    public void feed() {
        full += maxLevel / 4;
        if (full > maxLevel) {
            full = maxLevel;
            // sick
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
        String res = "";
        if (tired == maxLevel) {
            res += "asleep";
        } else if (tired >= maxLevel * 2/3) {
            res += "I'm tired...";
        } else if (full <= maxLevel / 3) {
            res += "I'm hungry! Feed me!";
        } else if (full == -1) {
            res += "I'm DEAD...";
        } else if (clean >= maxLevel * 2/3) {
            res += "I'm dirty! Clean me!";
        } else if (happy >= maxLevel  * 2/3) {
            res += "I'm happy, love you)";
        } else if (happy >= maxLevel / 3) {
            res += "I'm ok.";
        } else {
            res += "I'm sad(( walk with me...";
        }
        return res;
    }

    public void passTime() {
        full--;
        clean++;
        tired++;

        if (full <= maxLevel / 3) {
            happy--;
        }
        if (clean >= maxLevel * 2/3) {
            happy--;
        }

        happy = happy < 0 ? 0 : happy;
        tired = tired >= maxLevel ? maxLevel : tired;
        clean = clean >= maxLevel ? maxLevel : clean;

        /** dies */
        full = full < 0 ? -1 : full;
    }

    public void getAll() {
        System.out.println("ситий="+full+", щастя="+happy+", чистота="+clean+", втома="+tired+";");
    }

    public boolean isSleep() {
        if (tired == 10) {
            return true;
        }
        return false;
    }
}
