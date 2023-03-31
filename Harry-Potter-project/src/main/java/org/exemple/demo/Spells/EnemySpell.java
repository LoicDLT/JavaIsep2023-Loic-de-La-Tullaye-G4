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
    public static EnemySpell Dolores_suspend(){
        EnemySpell Voldemort_Imperio =
                EnemySpell.builder()
                        .name("Suspention")
                        .chanceOfSuccess(80)
                        .script(" gives you a suspension, it gives you a lot of emotional damage")
                        .damage(50)
                        .spellWeight(100)
                        .didNotWork("But you fell like you are above the rules, you are not affected by this sanction")
                        .build();
        return Voldemort_Imperio;}

    public static EnemySpell Confringo(){
        EnemySpell Confringo =
                EnemySpell.builder()
                        .name("Confringo")
                        .chanceOfSuccess(50)
                        .script(" casts Confringo on you, as he's not very talented, his spell is not very accurate")
                        .damage(100)
                        .spellWeight(100)
                        .didNotWork("As expected, the spell missed you")
                        .build();
        return Confringo;}
    public static EnemySpell Confringo2(){
        EnemySpell Confringo =
                EnemySpell.builder()
                        .name("Confringo")
                        .chanceOfSuccess(70)
                        .script(" casts Confringo on you, but much more accurately than Peter Pettigrew")
                        .damage(100)
                        .spellWeight(100)
                        .didNotWork("The spell missed you")
                        .build();
        return Confringo;}
    public static EnemySpell Voldemort_Avadakedavra(){
    EnemySpell Voldemort_AvadaKedavra =
            EnemySpell.builder()
                    .name("Crucio")
                    .chanceOfSuccess(80)
                    .script(" casts Avada-kedavra on you, this spell is deadly on impact")
                    .damage(180)
                    .spellWeight(100)
                    .didNotWork("It was so close ! don't ever get touched by this spell.")
                    .build();
        return Voldemort_AvadaKedavra;

    }
}
