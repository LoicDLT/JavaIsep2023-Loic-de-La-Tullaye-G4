package org.exemple.demo.Characters;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.exemple.demo.Tools.ConsoleColors;
import org.exemple.demo.Tools.Probability;
import org.exemple.demo.Spells.Curse;
import org.exemple.demo.Spells.EnemySpell;

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

    public static Enemy Troll(ArrayList<EnemySpell> listAttacks){
        Enemy Troll = (Enemy) builder()
                .id(1)
                .firstname("Troll")
                .lastname("")
                .level(3)
                .amoutOfExp(300)
                .amoutOfGold(100)
                .attackList(listAttacks)
                .curseList(new HashMap<Curse, Integer>())
                .currentHealthPoints(1000)
                .maxHealthPoints(1000)
                .dodgingChancePercentage(5)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Troll;
    }
    public static Enemy Trollette(ArrayList<EnemySpell> listAttacks){
        Enemy Trollette = (Enemy) builder()
                .id(2)
                .firstname("Trollette")
                .level(12)
                .lastname("")
                .amoutOfExp(300)
                .amoutOfGold(100)
                .curseList(new HashMap<Curse, Integer>())
                .maxHealthPoints(1200)
                .attackList(listAttacks)
                .currentHealthPoints(1200)
                .dodgingChancePercentage(20)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Trollette;
    }
    public static Enemy Basilisk(ArrayList<EnemySpell> listAttacks){
        Enemy Basilisk = (Enemy) builder()
                .id(3)
                .firstname("Basilisk")
                .level(12)
                .lastname("")
                .amoutOfExp(500)
                .amoutOfGold(250)
                .maxHealthPoints(1200)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(1200)
                .dodgingChancePercentage(30)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Basilisk;
    }
    public static Enemy Dementor1(ArrayList<EnemySpell> listAttacks){
        Enemy Dementor = (Enemy) builder()
                .id(4)
                .firstname("Dementor 1")
                .level(6)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(75)
                .maxHealthPoints(400)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(400)
                .dodgingChancePercentage(10)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Dementor;
    }
    public static Enemy Dementor2(ArrayList<EnemySpell> listAttacks){
        Enemy Dementor = (Enemy) builder()
                .id(4)
                .firstname("Dementor 2")
                .level(6)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(75)
                .maxHealthPoints(400)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(400)
                .dodgingChancePercentage(10)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Dementor;
    }
    public static Enemy Dementor3(ArrayList<EnemySpell> listAttacks){
        Enemy Dementor = (Enemy) builder()
                .id(4)
                .firstname("Dementor 3")
                .level(6)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(75)
                .maxHealthPoints(400)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(400)
                .dodgingChancePercentage(10)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Dementor;
    }
    public static Enemy Voldemort(ArrayList<EnemySpell> listAttacks){
        Enemy Voldemort = (Enemy) builder()
                .id(5)
                .firstname("Voldemort")
                .level(30)
                .lastname("")
                .amoutOfExp(1000)
                .amoutOfGold(300)
                .maxHealthPoints(3000)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(3000)
                .dodgingChancePercentage(10)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Voldemort;
    }
    public static Enemy Pettigrow(ArrayList<EnemySpell> listAttacks){
        Enemy Pettigrow = (Enemy) builder()
                .id(6)
                .firstname("Pettigrow")
                .level(3)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(150)
                .maxHealthPoints(500)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(500)
                .dodgingChancePercentage(5)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Pettigrow;
    }
    public static Enemy Dolores(ArrayList<EnemySpell> listAttacks){
        Enemy Dolores = (Enemy) builder()
                .id(6)
                .firstname("Dolorès")
                .level(3)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(150)
                .maxHealthPoints(960)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(500)
                .dodgingChancePercentage(5)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Dolores;
    }
    public static Enemy DeathEater(ArrayList<EnemySpell> listAttacks){
        Enemy DeathEater = (Enemy) builder()
                .id(6)
                .firstname("DeathEater")
                .level(3)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(150)
                .maxHealthPoints(960)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(500)
                .dodgingChancePercentage(5)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return DeathEater;
    }
    public static Enemy Bellatrix(ArrayList<EnemySpell> listAttacks){
        Enemy Bellatrix = (Enemy) builder()
                .id(6)
                .firstname("Bellatrix")
                .level(3)
                .lastname("")
                .amoutOfExp(200)
                .amoutOfGold(150)
                .maxHealthPoints(960)
                .curseList(new HashMap<Curse, Integer>())
                .attackList(listAttacks)
                .currentHealthPoints(500)
                .dodgingChancePercentage(5)
                .maxDodgingChancePercentage(100)
                .dead(false)
                .build();
        return Bellatrix;
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
                if (!(spellChoosed.getCurse() == null)){

                    script+= "\n" +wizard.applyCurse(spellChoosed.getCurse(),true);

                }
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
            return script;


    }
}
