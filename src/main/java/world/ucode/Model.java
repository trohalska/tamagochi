package world.ucode;

import world.ucode.utils.Calculation;
import java.util.Random;

public class Model {
    private final Calculation calc;

    public int id;
    private String name;
    private String type;
    private String time;
    private int max;

    private int age;
    private int hunger;
    private int thirst;
    private int happiness;
    private int cleanliness;
    private int tired;
    private int health;
    public boolean isAlive;
    public boolean isSick;

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) { this.type = type; }
    public void setTime(String time) {
        this.time = time;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    public void setThirst(int thirst) {
        this.thirst = thirst;
    }
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }
    public void setTired(int tired) {
        this.tired = tired;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public void setSick(boolean sick) {
        isSick = sick;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getTime() {
        return time;
    }

    public int getHunger() {
        return hunger;
    }
    public int getThirst() {
        return thirst;
    }
    public int getHappiness() {
        return happiness;
    }
    public int getCleanliness() {
        return cleanliness;
    }
    public int getTired() {
        return tired;
    }
    public int getHealth() {
        return health;
    }
    public int getAliveInt() {
        return isAlive ? 1 : 0;
    }
    public int getSickInt() {
        return isSick ? 1 : 0;
    }

    public Model() {
        Database.loadModel(this);
        this.calc = new Calculation(max);
    }

    public void aging() { age++; }
    public int getAge() { return age; }

    private void checkVitals() {
        if (hunger > max || hunger < 0) { health--; }
        if (thirst > max || thirst < 0) { health--; }
        if (cleanliness < 0)            { health--; }

        if (health <= calc.m4()) {
            isSick = true;
        }

        hunger = hunger > max ? max : (hunger = hunger < 0 ? 0 : hunger);
        thirst = thirst > max ? max : (thirst = thirst < 0 ? 0 : thirst);
        happiness = happiness > max ? max : (happiness = happiness < 0 ? 0 : happiness);
        cleanliness = cleanliness > max ? max : (cleanliness < 0 ? 0 : cleanliness);
        tired = tired > max ? max : tired;
        health = health > max ? max : health;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }

    public void feed() {
        hunger -= 2;
        cleanliness -= 2;
        tired++;
        checkVitals();
    }
    public void giveWater() {
        thirst -= 2;
        checkVitals();
    }

    public void walk() {
        happiness += 4;
        hunger += 2;
        thirst += 2;
        tired += 2;
        cleanliness -= 2;
        checkVitals();
    }

    public void pet() {
        happiness += 2;
        checkVitals();
    }

    public void clean() {
        cleanliness += 3;
        checkVitals();
    }

    public void giveMedicine() {
        if (health >= max) {
            isAlive = false;
        } else if ((new Random()).nextBoolean()) {
            health += 5;
        }
        checkVitals();
    }

    public String getMood() {
        String res;
        if (!isAlive) {
            res = "I'm dead, I'll come to you in your nightmares!";
        } else if (tired == max) {
            res = "asleep, hrrrr...";
        } else if (health <= calc.m4() || isSick) {
            res = "I think, I'm sick ((";
        } else if (tired >= calc.m8()) {
            res = "I'm tired...";
        } else if (hunger >= calc.m7()) {
            res = "I'm hungry!";
        } else if (thirst >= calc.m7()) {
            res = "I am thirsty!";
        } else if (cleanliness <= calc.m3()) {
            res = "I'm dirty!";
        } else if (happiness >= calc.m7()) {
            res = "I'm happy, love you)";
        } else if (happiness >= calc.m4()) {
            res = "I'm ok.";
        } else {
            res = "I'm unhappy, play with me...";
        }
        return res;
    }

    public void passTime() {
        hunger++;
        thirst++;
        cleanliness--;
        happiness--;
        tired++;

        if (hunger >= calc.m7()) {
            happiness--;
        }
        if (thirst >= calc.m7()) {
            happiness--;
        }
        if (cleanliness <= calc.m3()) {
            happiness--;
        }
        checkVitals();
    }

    public void sleepTime() {
        hunger += calc.m5();
        thirst += calc.m5();
        cleanliness -= calc.m5();
        happiness -= calc.m5();
        tired = 0;
        checkVitals();
    }

    public String getVitals() {
        return  "hunger \t" + hunger + "\n" +
                "thirst \t" + thirst + "\n" +
                "happy \t" + happiness + "\n" +
                "tired \t\t" + tired + "\n" +
                "clean \t" + cleanliness + "\n" +
                "health \t" + health;
    }

    public boolean isSleep() {
        return tired == max;
    }
    public boolean isSickToDeath() {
        return isSick && tired >= calc.m8() && hunger >= calc.m7();
    }
}
