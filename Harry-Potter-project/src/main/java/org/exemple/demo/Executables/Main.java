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
import org.exemple.demo.WizardCreation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static boolean musicEnabled = true;

    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;
    public static float musicVolume = 0.03F;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {

        boolean repeat=true;
        while(repeat) {
            System.out.println("1. With Music\n2. Without Music");
            String Choice = scanner.nextLine();
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
    MusicPlayer.playloop(MusicLibrary.backgroudMusic);
    MusicPlayer.setVolume(0.1F);
}

        Potion Health_potion = Potion.health_Potion();
        Potion Mana_potion = Potion.mana_Potion();
        Potion Luck_potion = Potion.luck_Potion();
        Potion Strength_potion = Potion.strength_Potion();



        //Parameters for playe

        //build wizard
        Wizard hero = WizardCreator.createWizard();

        //test adding potions
        hero.addPotions(Health_potion);
        hero.addPotions(Health_potion);
        hero.addPotions(Mana_potion);
        hero.addPotions(Mana_potion);
        hero.addPotions(Mana_potion);
        hero.addPotions(Mana_potion);
        hero.addPotions(Strength_potion);

        //starting Year 1
        Playgame.playgame(hero);

        System.out.println("TEST SORTIE PLAYGAME");

    }
}
