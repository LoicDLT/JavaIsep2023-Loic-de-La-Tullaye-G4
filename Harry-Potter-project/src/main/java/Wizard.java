
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
    private int maxManaPoints;
    private int currentManaPoints;
    private Pet pet;
    private Wand wand;
    private House house;

    public String levelUp(){
        String levelannoucement ="you just gained a level ! "+ConsoleColors.TOORANGE(String.valueOf(this.level))+" -> ";
        this.level+=1;
        return levelannoucement+=ConsoleColors.TOORANGE(String.valueOf(this.level));
    }

    public void getPotionsNames() {
        String potionlist = "";
        for (Potion potion : potions) {
            potionlist = potionlist + String.format("%s%s%s" + separator, potion.getColor(),potion.getName(),ConsoleColors.RESET);
        }
        System.out.printf(potionlist);
    }

    public static void usePotion(Potion potion, Wizard wizard) {
        if (wizard.getPotions().contains(potion)){
            wizard.removePotions(potion);
            Effect.healthRegen(potion.getAmountOfHealthRegen(), wizard);
            Effect.manaRegen(potion.getAmountOfManaRegen(), wizard);
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

    @Override
    public void attack() {


    }

    public void defend() {
    }
}