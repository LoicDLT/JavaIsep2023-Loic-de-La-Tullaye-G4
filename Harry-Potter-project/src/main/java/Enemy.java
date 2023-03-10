import SPELLS.AbstractSpell;
import SPELLS.EnemySpell;
import lombok.Data;

import lombok.experimental.SuperBuilder;

import java.util.*;


@Data
@SuperBuilder(toBuilder = true)
public class Enemy extends Character {

    private int level;
    private ArrayList<EnemySpell> attackList;

    private int amoutOfExp;
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

            String script = spellChoosed.getScript() + "\n";


            boolean Hit = Probability.YesOrNo((spellChoosed.getChanceOfSuccess() * (100 - wizard.getDodgingChancePercentage())) / 100);
            if (!Hit) {
                script += "\n" + spellChoosed.getDidNotWork();
            } else {

                damageDealt = spellChoosed.getDamage();

                /*boolean Crit = Probability.YesOrNo(currentLuckPoints);
                if (Crit) {
                    script += "\n" + "The spell is very effective and dealt extra damage ! ";
                    damageDealt = Math.round(damageDealt * (1 + (getCurrentStrengthPoints() / 100)));
                }*/
                script += ConsoleColors.CYAN_BOLD + wizard.getFirstname() + " " + wizard.getLastname() + " : " +
                        ConsoleColors.RED_BOLD_BRIGHT + "❤ " +
                        Math.round(wizard.getCurrentHealthPoints()) + "/" + Math.round(wizard.getMaxHealthPoints()) +
                        ConsoleColors.RESET + " -> ";


                Effect.healthRegen(-damageDealt, wizard);

                script += ConsoleColors.RED_BOLD_BRIGHT + "❤ " +
                        Math.round(wizard.getCurrentHealthPoints()) + "/" + Math.round(wizard.getMaxHealthPoints()) +
                        ConsoleColors.RESET + " (" + ConsoleColors.RED_BOLD_BRIGHT + " ❤ -" + Math.round(damageDealt) + ConsoleColors.RESET + " )";
                if (wizard.isDead()) {
                    script += "\n\n" + wizard.getFirstname() + " is defeated !";

                }
            }
            return script;


    }


}
