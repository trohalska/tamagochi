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
A tamagotchi has:
hungriness, hapiness, cleaniness, tireness (int in the range 0-10).

Interaction:
- feed (hungry -5)
- take for a walk (happy +5 && tired +2)
- clean (clean -10)
- pet (happy +5)
- get mood:
    * asleep (tired == 10)
    * tired (tired >= 8)
    * hunry (hungry >= 7)
    * dirty (clean >= 8)
    * happy (happy >= 7)
    * OK (4 <= happy < 7)
    * sad (happy < 4)
- pas time:
    hunger goes UP for 1
    clean goes UP for 1
    tired goes UP for 1, if tired==10 -> pet wakes up and tired =0.
    

```
