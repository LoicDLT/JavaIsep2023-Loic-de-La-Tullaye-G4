
import SPELLS.AbstractSpell;
import SPELLS.Spell;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;


@Data
@SuperBuilder

public class Wizard extends Character {
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;
    private int level;
    private ArrayList<AbstractSpell> knownSpells;
    private ArrayList<Potion> potions;
    private float maxManaPoints;
    private float currentManaPoints;
    private float maxLuckPoints;
    private float currentLuckPoints;
    private float maxStrengthPoints;
    private float currentStrengthPoints;
    private Pet pet;
    private Wand wand;
    private House house;

    public String levelUp() {
        String levelannoucement = "you just gained a level ! " + ConsoleColors.TOORANGE(String.valueOf(this.level)) + " -> ";
        this.level += 1;
        return levelannoucement += ConsoleColors.TOORANGE(String.valueOf(this.level));
    }


    //===============================================================SPELLS==================================================================
    public String getKnownSpellsNames() {
        String spellList = "";
        for (AbstractSpell spell : knownSpells) {
            spellList += spell.getName()+ConsoleColors.BLUE_BOLD_BRIGHT+" \uD83C\uDF22 "+Math.round(spell.getManaCost())+ConsoleColors.RESET+"\n";
        }

        return "Spell List :\n" + spellList.trim();

    }

    public AbstractSpell stringToSpell(String spellName) {

        for (AbstractSpell spell : knownSpells) {
            if (spellName.equalsIgnoreCase(spell.getName())) {
                return spell;
            }
        }
        return null;
    }


//===============================================================POTIONS=================================================================

    public String getPotionsNames() {
        ArrayList<Potion> found = new ArrayList<>();
        ArrayList<Integer> occurence = new ArrayList<>();
        String potionlist = "";
        for (Potion potion : potions) {

            if (!found.contains(potion)) {
                found.add(potion);
                occurence.add(1);
            } else {
                occurence.set(found.indexOf(potion), occurence.get(found.indexOf(potion)) + 1);
            }

        }
        for (int index = 0; index < found.size(); index++) {
            potionlist += occurence.get(index) + "x " + String.format("%s%s%s\n", found.get(index).getColor(), found.get(index).getName(), ConsoleColors.RESET);
        }
        return "Potion List :\n" + potionlist.trim();
    }

    public Potion stringToPotion(String PotionName) {

        for (Potion potion : potions) {
            if (PotionName.equalsIgnoreCase(potion.getName())) {
                return potion;
            }
        }
        return null;
    }

    public static void usePotion(Potion potion, Wizard wizard) {
        if (wizard.getPotions().contains(potion)) {
            wizard.removePotions(potion);
            Effect.healthRegen(potion.getAmountOfHealthRegen(), wizard);
            Effect.manaRegen(potion.getAmountOfManaRegen(), wizard);
            Effect.LuckIncrease(potion.getAmountOfLuck(), wizard);
            Effect.StrengthIncrease(potion.getAmountOfStrength(), wizard);

        }
    }

    public void addPotions(Potion potion) {
        this.potions.add(potion);
    }

    public void addSpell(Spell spell) {
        this.knownSpells.add(spell);
    }

    public void removePotions(Potion potion) {
        this.potions.remove(potion);
    }

    public void removeSpell(Spell spell) {
        this.knownSpells.remove(spell);
    }


    //===============================================================COMBAT=================================================================

    public String attack(Character character, AbstractSpell spellChoosed) {
        String script = spellChoosed.getScript();

        float damageDealt;

        boolean Hit = Probability.YesOrNo((spellChoosed.getChanceOfSuccess() * (100 - character.getDodgingChancePercentage())) / 100);
        if (!Hit) {
            script += "\n" + spellChoosed.getDidNotWork();
        } else {
            boolean Crit = Probability.YesOrNo(currentLuckPoints);
            damageDealt = spellChoosed.getDamage();
            if (Crit) {
                script += "\n" + "The spell is very effective and dealt extra damage ! ";
                damageDealt = Math.round(damageDealt * (1 + (getCurrentStrengthPoints() / 100)));
            }
            script += "\n\033[38;5;160m" + character.getFirstname() + " : " +
                    ConsoleColors.RED_BOLD_BRIGHT + "❤ " +
                    Math.round(character.getCurrentHealthPoints()) + "/" + Math.round(character.getMaxHealthPoints()) +
                    ConsoleColors.RESET + " -> ";


            Effect.healthRegen(-damageDealt, character);
            currentManaPoints += -spellChoosed.getManaCost();


            script += ConsoleColors.RED_BOLD_BRIGHT + "❤ " +
                    Math.round(character.getCurrentHealthPoints()) + "/" + Math.round(character.getMaxHealthPoints()) +
                    ConsoleColors.RESET+" ("+ConsoleColors.RED_BOLD_BRIGHT+" ❤ -"+Math.round(damageDealt)+ConsoleColors.RESET+" )";
            if (character.isDead()) {
                script += "\n\n" + character.getFirstname() + " is defeated !";

            }
        }
        return script;
    }




    public void defend() {
    }
}