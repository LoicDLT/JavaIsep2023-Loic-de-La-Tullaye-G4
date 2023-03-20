package org.exemple.demo.SPELLS;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EnemySpell extends AbstractSpell {
    private int spellWeight;

    public static EnemySpell Troll_Throw() {
        EnemySpell Troll_Throw =
                EnemySpell.builder()
                        .name("Troll Throw")
                        .chanceOfSuccess(90)
                        .script(" throws at you some rubble he found on the ground")
                        .damage(30)
                        .spellWeight(70)
                        .didNotWork("Thanks to your skills (or the lack of his) you dodge his attack !")
                        .build();
        return Troll_Throw;
    }

    public static EnemySpell Troll_Hit() {
        EnemySpell Troll_Hit =
                EnemySpell.builder()
                        .name("Troll Hit")
                        .chanceOfSuccess(85)
                        .script(" tries to hit you with his fist")
                        .damage(50)
                        .spellWeight(30)
                        .didNotWork("Thanks to your skills (or the lack of his) you dodge his attack !")
                        .build();
        return Troll_Hit;
    }

    public static EnemySpell Basiliks_bite() {
        EnemySpell Basilisk_Throw =
                EnemySpell.builder()
                        .name("Basilik's bite")
                        .chanceOfSuccess(80)
                        .script("The come forward Basilik and tries to bite you")
                        .damage(150)
                        .spellWeight(70)
                        .didNotWork("That was scary ! You barely dodged his attack")
                        .build();
        return Basilisk_Throw;
    }

    public static EnemySpell Basiliks_Tail() {
        EnemySpell Basilisk_Throw =
                EnemySpell.builder()
                        .name("Basilik's Tail")
                        .chanceOfSuccess(90)
                        .script("the Basilik tries to hit you with his tail")
                        .damage(100)
                        .spellWeight(30)
                        .didNotWork("You are in luck ! He missed, but not by much")
                        .build();
        return Basilisk_Throw;
    }

}
