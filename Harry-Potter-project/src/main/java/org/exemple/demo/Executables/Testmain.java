package org.exemple.demo.Executables;

import org.exemple.demo.Characters.Wizard;
import org.exemple.demo.Tools.ConsoleColors;
import org.exemple.demo.GameplayMaterial.Playgame;
import org.exemple.demo.Music.MusicLibrary;
import org.exemple.demo.Music.MusicPlayer;
import org.exemple.demo.Spells.AbstractSpell;
import org.exemple.demo.Spells.Curse;
import org.exemple.demo.Usables.Equipement;
import org.exemple.demo.Usables.Potion;
import org.exemple.demo.WizardCreation.Core;
import org.exemple.demo.WizardCreation.House;
import org.exemple.demo.WizardCreation.Pet;
import org.exemple.demo.WizardCreation.Wand;

import java.util.ArrayList;
import java.util.HashMap;


public class Testmain {
    public static boolean musicEnabled = true;
    public static ArrayList<AbstractSpell> knownSpellList = new ArrayList<>();
    public static ArrayList<Potion> potionsList = new ArrayList<>();
    public static ArrayList<Equipement> equipements = new ArrayList<>();
    public static HashMap<Curse,Integer> curseList = new HashMap<>();
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;
    public static float musicVolume = 0.03F;
    public static void main(String[] args) throws InterruptedException {

        boolean repeat=true;
        while(repeat) {
            System.out.println("1. With Music\n2. Without Music\n3. Meme Version");
            String Choice = Main.scanner.nextLine();
        switch (Choice){
            case "1":
                musicEnabled = true;
                repeat = false;
                break;
            case "2":
                musicEnabled = false;
                repeat = false;
                break;
            case "3":
                //TODO MEME VERSION
            default:
                System.out.println("please choose a valid option");
                repeat = true;

        }
        }


if (musicEnabled) {
    MusicPlayer.playloop(MusicLibrary.lezgongue);
    MusicPlayer.setVolume(0.1F);
}

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
                .levelUpRatio(0.20F)
                .maxHealthPoints(500)
                .currentHealthPoints(500)
                .knownSpells(knownSpellList)
                .potions(potionsList)
                .equipements(equipements)
                .curseList(curseList)
                .maxManaPoints(600)
                .currentManaPoints(300)
                .currentLuckPoints(100)
                .maxLuckPoints(100)
                .currentStrengthPoints(300)
                .maxStrengthPoints(300)
                .dodgingChancePercentage(0)
                .maxDodgingChancePercentage(100)
                .gold(300)
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

        //displaying Players infos and potions list

        Hero.getPotionsNames();

        //getting proprieties of a certain

        //starting Year 1
        Playgame.playgame(Hero);

        System.out.println("TEST SORTIE PLAYGAME");

    }
}