package world.ucode;

import world.ucode.utils.Calculation;

import java.util.Random;

import static world.ucode.GameGeometry.*;

public class Model {
    private int hunger;
    private int thirst;
    private int happiness;
    private int cleanliness;
    private int tired;
    private int health;
    public boolean isAlive;
    public boolean isSick;

    private int max;
    private Calculation calc;

    private String name;
    private int age;
    private GameGeometry.CharacterType type;

    public void setName(String name) {
        this.name = name;
    }
    public void setType(CharacterType type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public Model() {
        this.max = maxVital;
        this.calc = new Calculation(maxVital);

        isAlive = true;
        isSick = false;
        age = 0;

        tired = 0;
        happiness = hunger = thirst = calc.m2();
        cleanliness = calc.m8();
        health = calc.m();
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
    }

    public void feed() {
        hunger -= calc.m2();
        checkVitals();
    }
    public void giveWater() {
        thirst -= calc.m2();
        checkVitals();
    }

    public void walk() {
        happiness += calc.m4();
        hunger += calc.m2();
        thirst += calc.m2();
        tired += calc.m2();
        checkVitals();
    }

    public void pet() {
        happiness += calc.m2();
        checkVitals();
    }

    public void clean() {
        cleanliness += calc.m3();
        checkVitals();
    }

    public void giveMedicine() {
        if (health >= calc.m8()) {
            isAlive = false;
        } else if ((new Random()).nextBoolean()) {
            health += calc.m5();
        }
        checkVitals();
    }

    public String getMood() {
        String res;
        if (tired == max) {
            res = "asleep, hrrrr...";
        } else if (health <= calc.m4()) {
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
            res = "I'm sad(( play with me...";
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
        return tired == max ? true : false;
    }
    public boolean isSickToDeath() {
        return (isSick && tired >= calc.m7() && hunger >= calc.m7()) ? true : false;
    }
}
