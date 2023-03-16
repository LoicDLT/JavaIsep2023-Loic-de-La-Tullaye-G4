package org.exemple.demo.SPELLS;

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
                        .script(" throws at you some rubble he found on the ground")
                        .damage(50)
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
                        .script(" tries to hit you with his fist")
                        .damage(50)
                        .spellWeight(30)
                        .didNotWork("Thanks to your skills (or the lack of his) you dodge his attack !")
                        .build();
        return Troll_Hit;
    }

public static EnemySpell glaze(){
        EnemySpell Basilisk_Throw =
                EnemySpell.builder()
                        .name("Basilisk Throw")
                        .chanceOfSuccess(100)
                        .script(" throws at you some rubble he found on the ground")
                        .damage(50)
                        .spellWeight(70)
                        .didNotWork("Thanks to your skills (or the lack of his) you dodge his attack !")
                        .build();
        return Basilisk_Throw;
    }

}
