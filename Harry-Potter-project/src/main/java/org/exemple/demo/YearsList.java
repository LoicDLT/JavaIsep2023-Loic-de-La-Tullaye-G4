package org.exemple.demo;

import org.exemple.demo.Music.MusicLibrary;
import org.exemple.demo.Music.MusicPlayer;
import org.exemple.demo.Music.SoundEffectPlayer;
import org.exemple.demo.SPELLS.AbstractSpell;
import org.exemple.demo.SPELLS.Spell;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public interface YearsList {

    //MAIN MENU

    static void CurseCooldown(ArrayList<Enemy> enemyList, Wizard Hero) {
        for (Enemy enemy : enemyList) {
            enemy.reduceAllCurseTime();
        }
        Hero.reduceAllCurseTime();
    }
    //POTION SWITCH CASE
    static void potionSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean potion_choosed, String currentState) {
        while (!potion_choosed) {

            String Choice = Main.scanner.nextLine();
            ArrayList<Potion> found = (ArrayList<Potion>) Hero.getPotionsNames().get(1);

            if (Choice.equals("back")) {
                //MAIN SCREEN
                displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                potion_choosed = true;
            }

            try {
                int selected = Integer.parseInt(Choice);
                if (selected >= 1 & selected <= found.size()) {
                    Hero.usePotion(found.get(selected - 1));
                    if (Testmain.musicEnabled) {
                        SoundEffectPlayer.play(MusicLibrary.potionSlurpFortnite);
                        SoundEffectPlayer.setVolume(0.1F);
                    }
                    currentState = "\n\n\n" + found.get(selected - 1).getName() + " used successfully!";
                    potion_choosed = true;

                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                } else {
                    displayer.potionDisplayUpdate(enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                displayer.potionDisplayUpdate(enemyList, Hero, true);
            }

        }

    }

    static boolean equipementSwitchCase(Displayer displayer,Year year, ArrayList<Enemy> enemyList, Wizard Hero,boolean equipement_chosed, String currentState) {

        if (Hero.getEquipements().isEmpty()) {
            year.setCurrentState("\n\nYou don't have any equipement ");
            displayer.mainDisplayUpdate(year.getCurrentState(), year.getEnemyList(), Hero, false, false);
            return equipement_chosed;
        }
        else if (equipement_chosed) {
            year.setCurrentState("\n\nYou have already used equipement this turn ");
            displayer.mainDisplayUpdate(year.getCurrentState(), year.getEnemyList(), Hero, false, false);
            return equipement_chosed;
        }
        else{
            displayer.equipementDisplayUpdate(year.getEnemyList(), Hero, false);






        while (true) {

            String Choice = Main.scanner.nextLine();

            if (Choice.equals("back")) {
                //MAIN SCREEN
                displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);

                return false;
            }

            try {
                int selected = Integer.parseInt(Choice);
                if (selected >= 1 & selected <= Hero.getEquipements().size()) {
                    Hero.useEquipement(Hero.getEquipements().get(selected - 1));
                    currentState = "\n\n\n" + Hero.getEquipements().get(selected - 1).getName() + " used successfully!";
                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                    return true;
                } else {
                    displayer.potionDisplayUpdate(enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                displayer.potionDisplayUpdate(enemyList, Hero, true);
            }

        }
        }
    }

    //SPELL SWITCH CASE
    static @NotNull ArrayList spellSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean spell_choosed_state, String attackResult, String currentState, String Choice) {
        ArrayList spellReturn = new ArrayList();
        spellReturn.add(spell_choosed_state);
        spellReturn.add(attackResult);
        while (!spell_choosed_state) {

            Choice = Main.scanner.nextLine();
            if (Choice.equals("back")) {
                //BACK TO MAIN SCREEN
                displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                break;

            }
            try {

                int selectedSpellIndex = Integer.parseInt(Choice);

                if (selectedSpellIndex >= 0 & selectedSpellIndex <= Hero.getKnownSpells().size()) {

                    AbstractSpell spell_choosed = Hero.getKnownSpells().get(selectedSpellIndex - 1);
                    if (Testmain.musicEnabled) {
                        SoundEffectPlayer.play(spell_choosed.getSoundEffect());
                        SoundEffectPlayer.setVolume(0.2F);
                    }
                    displayer.targetDisplayUpdate("", enemyList, Hero, false);
                    while (!spell_choosed_state) {
                        Choice = Main.scanner.nextLine();
                        try {

                            int selectedTargetIndex = Integer.parseInt(Choice);


                            if (selectedTargetIndex < 0 | selectedTargetIndex > enemyList.size()) {
                                displayer.targetDisplayUpdate("", enemyList, Hero, true);
                            } else {
                                //get target
                                Enemy enemy = enemyList.get(selectedTargetIndex - 1);
                                spell_choosed_state = true;
                                //reduce all curse cooldowns
                                CurseCooldown( enemyList,  Hero);

                                attackResult = Hero.attack(enemy, spell_choosed);
                                if (enemy.isDead()) {
                                    attackResult += Hero.getRewardFrom(enemy);
                                    enemyList.remove(enemy);
                                }
                                spellReturn.clear();
                                spellReturn.add(spell_choosed_state);
                                spellReturn.add(attackResult);
                                return spellReturn;

                            }
                        } catch (NumberFormatException e) {
                            displayer.targetDisplayUpdate("", enemyList, Hero, true);
                        }
                    }


                } else {
                    displayer.spellDisplayUpdate(enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                displayer.spellDisplayUpdate(enemyList, Hero, true);
            }

        }

        return spellReturn;
    }

    //=========================================================================YEARLIST===================================================================================

    static boolean Year_1(@NotNull Wizard Hero) throws InterruptedException {

        //initYear
        Year year1 = Year.year1Constructor();
        Hero.addSpell(Spell.Wingardium_Leviosa());
        Displayer displayer = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero, false) + "-".repeat(45) + "\n" + ActionCharacter.displayEnemyInfos(year1.getEnemyList(), false),
                year1.getCurrentState(),
                "1. Attack\n2. Use potion\n3. Equipements\n4. Dodge");


        System.out.println(year1.getEnemyList().get(0).getCurseList().keySet());
        displayer.display();
        while (!year1.getEnemyList().isEmpty() & !Hero.isDead()) {

            boolean potion_choosed;
            boolean equipement_chosed = false;

            boolean spell_choosed_state = false;
            boolean dodge_selected = false;
            float oldAgility = 0;
            String attackResult = "";

            do {
                String Choice = Main.scanner.nextLine();
                potion_choosed = false;
                switch (Choice) {


//==========================SPELL========================
                    case "1":
                        //SPELL SCREEN
                        displayer.spellDisplayUpdate(year1.getEnemyList(), Hero, false);

                        ArrayList spellResult = spellSwitchCase(displayer, year1.getEnemyList(), Hero, spell_choosed_state, attackResult, year1.getCurrentState(), Choice);
                        spell_choosed_state = (boolean) spellResult.get(0);
                        attackResult = (String) spellResult.get(1);
                        break;

//==========================POTION========================
                    case "2":
                        //POTION SCREEN
                        displayer.potionDisplayUpdate(year1.getEnemyList(), Hero, false);
                        potionSwitchCase(displayer, year1.getEnemyList(), Hero, potion_choosed, year1.getCurrentState());
                        break;

//==========================EQUIPEMENT========================
                    case "3":

                            equipement_chosed=equipementSwitchCase(displayer,year1, year1.getEnemyList(), Hero,equipement_chosed, year1.getCurrentState());
                            break;

//==========================DODGE========================
                    case "4":
                        dodge_selected = true;
                        oldAgility = Hero.getDodgingChancePercentage();
                        if (Testmain.musicEnabled) {
                            SoundEffectPlayer.play(MusicLibrary.dodgeLoutre);
                            SoundEffectPlayer.setVolume(0.1F);
                        }
                        attackResult = "You are not attacking this turn, you restore " + ConsoleColors.RED_BOLD_BRIGHT + "❤ 30" + ConsoleColors.RESET + " and " +
                                ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 40 " + ConsoleColors.RESET + "\nYou also gain" + ConsoleColors.AGYLITYCOLOR_BOLD + " 💨 35%" + ConsoleColors.RESET + " more temporary Agility";
                        Hero.healthRegen(30);
                        Hero.manaRegen(40);
                        Hero.AgilityIncrease(35);
                        CurseCooldown( year1.getEnemyList(),  Hero);
                        break;






                    default:
                        displayer.mainDisplayUpdate(year1.getCurrentState(), year1.getEnemyList(), Hero, true, false);
                        break;
                }

            } while (!dodge_selected & !spell_choosed_state);

            //RESULT SCREEN FOR THE TURN (ATTACK OR DODGE)

            year1.setCurrentState(attackResult + "\n\n");


            for (Enemy enemy : year1.getEnemyList()) {
                if (!enemy.isDead() & !Hero.isDead()) {
                    year1.setCurrentState(year1.getCurrentState() + enemy.attack(Hero) + "\n");
                }
            }
            displayer.mainDisplayUpdate(year1.getCurrentState().stripLeading(), year1.getEnemyList(), Hero, false, true);


            if (dodge_selected) {
                Hero.setDodgingChancePercentage(oldAgility);
            }

        }
        Hero.setMaxYear(1);
        if (Hero.isDead()) {
            Thread.sleep(5000);
            year1.setCurrentState("You died <3");
            displayer.endDisplayUpdate(year1.getCurrentState().stripLeading(), year1.getEnemyList(), Hero, false);
            if (Testmain.musicEnabled) {
                MusicPlayer.stopMusic();
                MusicPlayer.play(MusicLibrary.deathMusicAstronomia);
                MusicPlayer.setVolume(Testmain.musicVolume);

            }

        }
        if (year1.getEnemyList().isEmpty()) {
            Thread.sleep(5000);
            year1.setCurrentState("You passed 1st year with honors !\n"
                    + ConsoleColors.BLUE_BOLD + "-------REWARDS-------" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.YELLOW + "100 \uD83D\uDCB0" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.ORANGE + "200 " + ConsoleColors.RESET + "Exp points");


            displayer.endDisplayUpdate(year1.getCurrentState().stripLeading(), year1.getEnemyList(), Hero, false);
        }
        while (true) { //Choice for shop or not

            String Choice = Main.scanner.nextLine();
            switch (Choice) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    displayer.endDisplayUpdate(year1.getCurrentState().stripLeading(), year1.getEnemyList(), Hero, true);
            }

        }
    }


    static boolean Year_2(Wizard Hero) throws InterruptedException {
        //initYear


        Year year2 = Year.year2Constructor();
        Hero.addSpell(Spell.Obscuro());
        Displayer displayer = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero, false) + "-".repeat(45) + "\n" + ActionCharacter.displayEnemyInfos(year2.getEnemyList(), false),
                year2.getCurrentState(),
                "1. Attack\n2. Use potion\n3. Equipements\n4. Dodge");


        displayer.display();
        while (!year2.getEnemyList().isEmpty() & !Hero.isDead()) {

            boolean potion_choosed = false;
            boolean equipement_chosed = false;
            boolean dodgeOrSpell = false;
            boolean spell_choosed_state = false;
            boolean dodge_selected = false;
            float oldAgility = 0;
            String attackResult = "";
            do {
                String Choice = Main.scanner.nextLine();
                potion_choosed = false;
                switch (Choice) {


//==========================SPELL========================
                    case "1":
                        //SPELL SCREEN
                        displayer.spellDisplayUpdate(year2.getEnemyList(), Hero, false);

                        ArrayList spellResult = spellSwitchCase(displayer, year2.getEnemyList(), Hero, spell_choosed_state, attackResult, year2.getCurrentState(), Choice);
                        spell_choosed_state = (boolean) spellResult.get(0);
                        attackResult = (String) spellResult.get(1);
                        break;

//==========================POTION========================
                    case "2":
                        //POTION SCREEN
                        displayer.potionDisplayUpdate(year2.getEnemyList(), Hero, false);
                        potionSwitchCase(displayer, year2.getEnemyList(), Hero, potion_choosed, year2.getCurrentState());
                        break;


//==========================EQUIPEMENT========================
                    case "3":

                            equipement_chosed=equipementSwitchCase(displayer,year2, year2.getEnemyList(), Hero,equipement_chosed, year2.getCurrentState());
                            break;

//==========================DODGE========================
                    case "4":
                        dodge_selected = true;
                        oldAgility = Hero.getDodgingChancePercentage();
                        if (Testmain.musicEnabled) {
                            SoundEffectPlayer.play(MusicLibrary.dodgeLoutre);
                            SoundEffectPlayer.setVolume(0.1F);
                        }
                        attackResult = "You are not attacking this turn, you restore " + ConsoleColors.RED_BOLD_BRIGHT + "❤ 30" + ConsoleColors.RESET + " and " +
                                ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 40 " + ConsoleColors.RESET + "\nYou also gain" + ConsoleColors.AGYLITYCOLOR_BOLD + " 💨 35%" + ConsoleColors.RESET + " more temporary Agility";
                        Hero.healthRegen(30);
                        Hero.manaRegen(40);
                        Hero.AgilityIncrease(35);
                        CurseCooldown( year2.getEnemyList(),  Hero);
                        break;
                    default:
                        System.out.println("Wrong input");
                        displayer.mainDisplayUpdate(year2.getCurrentState(), year2.getEnemyList(), Hero, true, false);
                        break;
                }

            } while (!dodge_selected & !spell_choosed_state);

            //RESULT SCREEN FOR THE TURN (ATTACK OR DODGE)
            year2.setCurrentState(attackResult + "\n\n");


            for (Enemy enemy : year2.getEnemyList()) {
                if (!enemy.isDead() & !Hero.isDead()) {
                    year2.appendToCurrentState(enemy.attack(Hero) + "\n");
                }
            }
            displayer.mainDisplayUpdate(year2.getCurrentState().stripLeading(), year2.getEnemyList(), Hero, false, true);


            if (dodge_selected) {
                Hero.setDodgingChancePercentage(oldAgility);
            }

        }
        Hero.setMaxYear(2);
        if (Hero.isDead()) {
            Thread.sleep(5000);
            year2.setCurrentState("You died <3");
            displayer.endDisplayUpdate(year2.getCurrentState().stripLeading(), year2.getEnemyList(), Hero, false);
            if (Testmain.musicEnabled) {
                MusicPlayer.stopMusic();
                MusicPlayer.play(MusicLibrary.deathMusicAstronomia);
                MusicPlayer.setVolume(Testmain.musicVolume);

            }

        }
        if (year2.getEnemyList().isEmpty()) {
            Thread.sleep(5000);
            year2.setCurrentState("You passed 2nd year with honors !\n"
                    + ConsoleColors.BLUE_BOLD + "-------REWARDS-------" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.YELLOW + "100 \uD83D\uDCB0" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.ORANGE + "200 " + ConsoleColors.RESET + "Exp points");


            displayer.endDisplayUpdate(year2.getCurrentState().stripLeading(), year2.getEnemyList(), Hero, false);
        }
        while (true) { //Choice for shop or not

            String Choice = Main.scanner.nextLine();
            switch (Choice) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    displayer.endDisplayUpdate(year2.getCurrentState().stripLeading(), year2.getEnemyList(), Hero, true);
            }

        }
    }


    static void Year_3(Wizard hero) {
        //TODO
    }

    static void Year_4(Wizard hero) {
        //TODO
    }

    static void Year_5(Wizard hero) {
        //TODO
    }

    static void Year_6(Wizard hero) {
        //TODO
    }

    static void Year_7(Wizard hero) {
        //TODO
    }


    static void ShopTime(@NotNull Wizard Hero, @NotNull Shop shop) {
        Displayer displayershop = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero, false) + "-".repeat(45),
                shop.displayShop(),
                "type the number of the item you want to buy  \ntype \"next\" to go to the next year");
        displayershop.display();
        String Choice = "";
        while (!Choice.equals("next")) {
            Choice = Main.scanner.nextLine();
            try {
                int choice = Integer.parseInt(Choice);

                if (choice > 0 & choice <= (shop.getAvaliablePotionMap().size() + shop.getAvaliableEquipementMap().size())) {
                    if (choice <= shop.getAvaliablePotionMap().size()) {
                        if (Hero.getGold() >= shop.getAvaliablePotionMap().values().toArray(new Integer[0])[choice - 1]) {
                            //ADDING POTION TO INVENTORY
                            Hero.addPotions(shop.getAvaliablePotionMap().keySet().toArray(new Potion[0])[choice - 1]);
                            //REMOVING GOLD
                            Hero.changeGold(-shop.getAvaliablePotionMap().values().toArray(new Integer[0])[choice - 1]);

                            String currentState = "\nYou bought " + shop.getAvaliablePotionMap()
                                    .keySet()
                                    .toArray(new Potion[0])[choice - 1]
                                    .getName() + " for " + ConsoleColors.YELLOW_BOLD + shop
                                    .getAvaliablePotionMap()
                                    .values()
                                    .toArray(new Integer[0])[choice - 1] + ConsoleColors.RESET + " \uD83D\uDCB0";
                            displayershop.shopDisplayUpdate(currentState, shop, Hero, false);
                        } else {
                            String currentState = "\nYou don't have enough " + ConsoleColors.YELLOW_BOLD + "gold" + ConsoleColors.RESET + " to buy this potion";
                            displayershop.shopDisplayUpdate(currentState, shop, Hero, false);
                        }

                    } else {
                        //TESTING IF THE PLAYER ALREADY HAVE THE EQUIPEMENT
                        if (Hero.getEquipements().contains(shop.getAvaliableEquipementMap()
                                .keySet()
                                .toArray(new Equipement[0])[choice - 1 - shop.getAvaliablePotionMap().size()])) {
                            String currentState = "\nYou already have this item";
                            displayershop.shopDisplayUpdate(currentState, shop, Hero, false);

                        } else if (Hero.getGold() >= shop.getAvaliableEquipementMap().values().toArray(new Integer[0])[choice - 1 - shop.getAvaliablePotionMap().size()]) {
                            Hero.addEquipement(shop.getAvaliableEquipementMap()
                                    .keySet()
                                    .toArray(new Equipement[0])[choice - 1 - shop.getAvaliablePotionMap().size()]);
                            Hero.changeGold(-shop.getAvaliableEquipementMap().values().toArray(new Integer[0])[choice - 1 - shop.getAvaliablePotionMap().size()]);
                            String currentState = "\nYou bought " + shop.getAvaliableEquipementMap()
                                    .keySet()
                                    .toArray(new Equipement[0])[choice - 1 - shop.getAvaliablePotionMap().size()].getName() + " for " + ConsoleColors.YELLOW_BOLD + shop.getAvaliableEquipementMap()
                                    .values()
                                    .toArray(new Integer[0])[choice - 1 - shop.getAvaliablePotionMap().size()] + ConsoleColors.RESET + " \uD83D\uDCB0";
                            displayershop.shopDisplayUpdate(currentState, shop, Hero, false);
                        } else {
                            String currentState = "\nYou don't have enough " + ConsoleColors.YELLOW_BOLD + "gold" + ConsoleColors.RESET + " to buy this item";
                            displayershop.shopDisplayUpdate(currentState, shop, Hero, false);
                        }

                    }
                } else {
                    String currentState = "\nPlease enter a valid number";
                    displayershop.shopDisplayUpdate(currentState, shop, Hero, false);
                }
            } catch (NumberFormatException e) {
                String currentState = "\nPlease enter a number";
                displayershop.shopDisplayUpdate(currentState, shop, Hero, false);
            }

        }

        System.out.println("test sortie");

    }
}
//TODO FADE IN MUSIC
//TODO FADE OUT MUSIC
//TODO REGLER ESPACEMENT ATTAQUES ENEMIES

