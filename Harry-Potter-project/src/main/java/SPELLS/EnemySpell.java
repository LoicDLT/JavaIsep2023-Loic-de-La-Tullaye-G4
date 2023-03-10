package SPELLS;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EnemySpell extends AbstractSpell{
    private int spellWeight;
    public static EnemySpell Troll_Throw(){
        EnemySpell Troll_Throw =
                EnemySpell.builder()
                        .name("Troll Throw")
                        .chanceOfSuccess(100)
                        .script("The Troll throws at you some rubble he found on the ground")
                        .damage(45)
                        .spellWeight(70)
                        .didNotWork("Thanks to your skills (or the lack of his) you dodge his attack !")
                        .build();
        return Troll_Throw;
    }
    public static EnemySpell Troll_Hit(){
        EnemySpell Troll_Hit =
                EnemySpell.builder()
                        .name("Troll Hit")
                        .chanceOfSuccess(100)
                        .script("The Troll Tries to hit you with his fist")
                        .damage(50)
                        .spellWeight(30)
                        .didNotWork("Thanks to your skills (or the lack of his) you dodge his attack !")
                        .build();
        return Troll_Hit;
    }
}
