# tamagochi

### Description:
Ucode project: develop minigame tamagochi. 

### Usage:
```
1. git clone https://github.com/trohalska/tamagochi tamagochi
2. cd tamagochi
3. mvn install
4. mvn javafx:run
```

### Specification:
```
A tamagotchi has basics (int in the range 0-max(default=10)):

hunger = 2, thirst = 2, tired = 0 (good when 0);
happiness = 2, cleanliness = 10, health = 10 (good when 10).

Interaction:
- feed (hungry -2)
- give water (thirst -2)
- take for a walk (happy +4, tired +2, hunger +2, thirst +2)
- pet (happy +2)
- clean (clean +10)
- give meds (health +10)

- get mood:
    * asleep (tired == 10)
    * tired (tired >= 8)
    * hungry, thirst (hungry, thirst >= 7)
    * dirty (clean <= 3)
    * happy (happy >= 7)
    * OK (4 <= happy < 7)
    * sad (happy < 4)
- pas time:
    hunger, thirst goes UP for 1
    happy, clean goes DOWN for 1

    tired goes UP for 1, if tired==10 -> pet wakes up and tired =0.
    

```
