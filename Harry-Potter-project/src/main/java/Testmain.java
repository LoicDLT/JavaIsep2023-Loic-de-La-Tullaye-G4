import SPELLS.AbstractSpell;
import SPELLS.*;

import java.util.ArrayList;
import java.util.Scanner;



public class Testmain {
    public static ArrayList<AbstractSpell> knownSpellList = new ArrayList<>();
    public static ArrayList<Potion> potionsList = new ArrayList<>();
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;

    public static void main(String[] args) throws InterruptedException {

        Potion Health_potion = Potion.health_Potion();
        Potion Mana_potion = Potion.mana_Potion();
        Potion Luck_potion = Potion.luck_Potion();
        Potion Strength_potion = Potion.strength_Potion();

        //Parameters for player
        House chosenHouse = House.GRYPHONDOR;
        Pet chosenPet = Pet.OWL;
        Wand chosenWand = new Wand(Core.PHOENIX_FEATHER, 35);

        //build wizard
        Wizard Hero = Wizard.builder()
                .level(1)
                .firstname("Joe")
                .lastname("Mama")
                .maxHealthPoints(500)
                .currentHealthPoints(500)
                .knownSpells(knownSpellList)
                .potions(potionsList)
                .maxManaPoints(600)
                .currentManaPoints(300)
                .currentLuckPoints(100)
                .maxLuckPoints(100)
                .currentStrengthPoints(300)
                .maxStrengthPoints(300)
                .dodgingChancePercentage(0)
                .maxDodgingChancePercentage(100)
                .pet(chosenPet)
                .wand(chosenWand)
                .house(chosenHouse)
                .build();


        //test adding potions
        Hero.addPotions(Health_potion);
        Hero.addPotions(Health_potion);
        Hero.addPotions(Mana_potion);
        Hero.addPotions(Mana_potion);
        Hero.addPotions(Mana_potion);
        Hero.addPotions(Mana_potion);
        Hero.addPotions(Strength_potion);
        //getting potions names
        Hero.getPotionsNames();

        //using potions
        Wizard.usePotion(Health_potion,Hero);
        Wizard.usePotion(Mana_potion,Hero);

        //displaying Players infos and potions list
        ActionCharacter.displayPlayerInfos(Hero);
        Hero.getPotionsNames();

        //getting proprieties of a certain potion
        System.out.println(Potion.proprieties(Luck_potion));

        //Hero.levelUp();



        //starting Year 1
        YearsList.Year_1(Hero);

    }
}
