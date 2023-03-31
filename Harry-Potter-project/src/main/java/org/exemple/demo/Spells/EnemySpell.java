package org.exemple.demo.Spells;

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
                        .script(" come forward and tries to bite you")
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
                        .script(" tries to hit you with his tail")
                        .damage(100)
                        .spellWeight(30)
                        .didNotWork("You are in luck ! He missed, but not by much")
                        .build();
        return Basilisk_Throw;
    }

    public static EnemySpell Dementor_Psychological_attack() {
        EnemySpell Dementor_Psychological_attack =
                EnemySpell.builder()
                        .name("Dementor's Psychological attack")
                        .chanceOfSuccess(90)
                        .script(" scares you to death, you slowly lose your life points")
                        .damage(30)
                        .spellWeight(30)
                        .didNotWork("he failed to scare you, you are not afraid of him")
                        .build();
        return Dementor_Psychological_attack;
    }

    public static EnemySpell Voldemort_Crucio(){
        EnemySpell Voldemort_Crucio =
                EnemySpell.builder()
                        .name("Crucio")
                        .chanceOfSuccess(80)
                        .script(" casts Crucio on you, this spell make you feel as if one thousand white-hot knives were boring into your skin")
                        .damage(180)
                        .spellWeight(100)
                        .didNotWork("But you are in luck ! The spell didn't hurt you")
                        .build();
        return Voldemort_Crucio;}
    public static EnemySpell Pettigrow_Confringo(){
        EnemySpell Pettigrow_Confringo =
                EnemySpell.builder()
                        .name("Confringo")
                        .chanceOfSuccess(50)
                        .script(" casts Confringo on you, as he's not very talented, his spell is not very accurate")
                        .damage(100)
                        .spellWeight(100)
                        .didNotWork("As expected, the spell missed you")
                        .build();
        return Pettigrow_Confringo;}

}
