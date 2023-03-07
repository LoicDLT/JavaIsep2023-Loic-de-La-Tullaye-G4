import SPELLS.AbstractSpell;

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

        House chosenHouse = House.GRYPHONDOR;


        Pet chosenPet = Pet.OWL;


        Wand chosenWand = new Wand(Core.PHOENIX_FEATHER, 35);


        Wizard Hero = Wizard.builder()
                .level(1)
                .firstname("Joe")
                .lastname("Mama")
                .maxHealthPoints(200)
                .currenthHealthPoints(150)
                .knownSpells(knownSpellList)
                .potions(potionsList)
                .maxManaPoints(600)
                .currentManaPoints(300)
                .pet(chosenPet)
                .wand(chosenWand)
                .house(chosenHouse)
                .build();
    //je sabote eton codde


        Hero.addPotions(Health_potion);
        Hero.addPotions(Health_potion);
        Hero.addPotions(Mana_potion);
        Hero.addPotions(Strength_potion);

        Hero.getPotionsNames();

        Wizard.usePotion(Health_potion,Hero);
        Wizard.usePotion(Mana_potion,Hero);
        ActionCharacter.displayPlayerInfos(Hero);
        Hero.getPotionsNames();
        System.out.println(Potion.proprieties(Luck_potion));
        String todisplay = "";
        //todisplay+=Hero.levelUp();
        todisplay=ActionCharacter.displayPlayerInfos(Hero)+todisplay;
        ActionCharacter.displayer(todisplay);
        YearsList.Year_1(Hero);

    }
}
