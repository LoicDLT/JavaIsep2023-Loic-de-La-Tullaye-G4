package org.exemple.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.exemple.demo.SPELLS.EnemySpell;
import lombok.Data;

import lombok.experimental.SuperBuilder;

import java.util.*;


@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
public class Enemy extends Character {

    private int level;
    private ArrayList<EnemySpell> attackList;
    private int amoutOfExp;
    private int amoutOfGold;

    public static Enemy Troll(ArrayList<EnemySpell> listTrollAttacks){
        Enemy Troll = (Enemy) builder()
                .firstname("Troll")
                .lastname("")
                .level(3)
                .amoutOfExp(300)
                .attackList(listTrollAttacks)
                .curseList(new HashMap<Curse, Integer>())
                .currentHealthPoints(1000)
                .maxHealthPoints(1000)
                .dodgingChancePercentage(5)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Troll;
    }
    public static Enemy Trollette(ArrayList<EnemySpell> listTrollAttacks){
        Enemy Trollette = (Enemy) builder()
                .firstname("Trollette")
                .level(12)
                .lastname("")
                .amoutOfExp(300)
                .amoutOfGold(100)
                .curseList(new HashMap<Curse, Integer>())
                .maxHealthPoints(1200)
                .attackList(listTrollAttacks)
                .currentHealthPoints(1200)
                .dodgingChancePercentage(20)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Trollette;
    }

    public static Enemy Basilisk(ArrayList<EnemySpell> listBasiliskAttacks){
        Enemy Basilisk = (Enemy) builder()
                .firstname("Basilisk")
                .level(12)
                .lastname("")
                .amoutOfExp(500)
                .amoutOfGold(100)
                .maxHealthPoints(1200)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listBasiliskAttacks)
                .currentHealthPoints(1200)
                .dodgingChancePercentage(20)
                .maxDodgingChancePercentage(5)
                .dead(false)
                .build();
        return Basilisk;
    }


    public String attack(Wizard wizard) {
        EnemySpell spellChoosed=attackList.get(0);
        int weight = 0;
        float damageDealt;


        //select enemy attack
        for (EnemySpell spell : attackList) {
            weight += spell.getSpellWeight();

        }
        int rand = new Random().nextInt(weight);

        weight = 0;
        for (EnemySpell spell : attackList) {
            weight += spell.getSpellWeight();

            if (weight >= rand) {

                spellChoosed = spell;
                break;
            }
        }

            String script = "[38;5;160m"+getFirstname() + ConsoleColors.RESET + spellChoosed.getScript() + "\n";

            float totalChanceOfSuccess = (spellChoosed.getChanceOfSuccess() * (100 - wizard.getDodgingChancePercentage()) / 100);
            if (!(getCurseList()==null)) {
                for (Curse curse : getCurseList().keySet()) {
                    totalChanceOfSuccess = totalChanceOfSuccess*curse.getCurseDodgingMultiplier();
                }
            }

            if (totalChanceOfSuccess > 100) {
                totalChanceOfSuccess = 100;
            }
            boolean Hit = Probability.YesOrNo(totalChanceOfSuccess);
            if (!Hit) {
                script += spellChoosed.getDidNotWork();
            } else {

                damageDealt = spellChoosed.getDamage();
                if (!(getCurseList()==null)) {
                    for (Curse curse : getCurseList().keySet()) {
                        damageDealt = damageDealt*curse.getCurseDamageMultiplier();
                    }
                }

                /*boolean Crit = Probability.YesOrNo(currentLuckPoints);
                if (Crit) {
                    script += "\n" + "The spell is very effective and dealt extra damage ! ";
                    damageDealt = Math.round(damageDealt * (1 + (getCurrentStrengthPoints() / 100)));
                }*/
                script += ConsoleColors.CYAN_BOLD + wizard.getFirstname() + " " + wizard.getLastname() + " : " +
                        ConsoleColors.RED_BOLD_BRIGHT + "❤ " +
                        Math.round(wizard.getCurrentHealthPoints()) + "/" + Math.round(wizard.getMaxHealthPoints()) +
                        ConsoleColors.RESET + " -> ";


                wizard.healthRegen(-Math.round(damageDealt));

                script += ConsoleColors.RED_BOLD_BRIGHT + "❤ " +
                        Math.round(wizard.getCurrentHealthPoints()) + "/" + Math.round(wizard.getMaxHealthPoints()) +
                        ConsoleColors.RESET + " (" + ConsoleColors.RED_BOLD_BRIGHT + " ❤ -" + Math.round(damageDealt) + ConsoleColors.RESET + " )";
                if (wizard.isDead()) {
                    script += "    " + wizard.getFirstname() + " is defeated !";

                }
            }
            return script+"\n";


    }
}
