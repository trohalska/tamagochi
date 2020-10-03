# tamagochi

### Description:
Ucode project: develop game tamagochi. 
Tamagochi has a lot of different behaviors models. Hi will be happy if takes a good care! 
Or hi may exidentally passed away if you give him a lot of food, can't heal in time or give medicine to healthy creature.

### Implementation:
GUI implemented with JavaFX library and has soundtracks during the game.
Games results can be saved and loaded from database (SQLite).
Game has 5 windows:
- menu;
- new game;
- load game;
- settings window:
    - sound: on/off;
    - theme: standard/dark;
    - difficulty: easy/normal/hard.
- play window.

### Usage:
```
1. git clone https://github.com/trohalska/tamagochi tamagochi
2. cd tamagochi
3. mvn install
4. mvn javafx:run

```

### Specification:
A tamagotchi has basics characteristics (in the range 0-max):
good when 0 -> hunger = 2/10 max, thirst = 2/10 max, tired = 0;
good when max -> happiness = 2/10 max, cleanliness = 8/10 max, health = max.

Interaction:
- feed (hungry -2)
- give water (thirst -2)
- take for a walk (happy +4, tired +2, hunger +2, thirst +2)
- pet (happy +2)
- clean (clean +3)
- give meds (health +5 with 50%-chance)

With time passed:
- hunger, thirst goes UP
- happy, clean goes DOWN
- tired goes UP, if tired==max -> pet wakes up and tired =0.


![view game](https://i.postimg.cc/P59cCgv1/tamagochi-07.png?raw=true "gameplay")

![menu](https://i.postimg.cc/Gh36VJ1T/tamagochi-02.png?raw=true "menu")

![settings](https://i.postimg.cc/3rFPtfzH/tamagochi-01.png?raw=true "settings")

![new game](https://i.postimg.cc/15WbQn9C/tamagochi-04.png?raw=true "new game")