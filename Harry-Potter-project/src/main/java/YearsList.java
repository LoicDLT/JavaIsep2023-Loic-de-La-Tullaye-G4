import java.util.ArrayList;

import Music.MusicLibrary;
import Music.MusicPlayer;
import Music.SoundEffectPlayer;
import SPELLS.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface YearsList {

    //MAIN MENU

    //POTION SWITCH CASE
    static void potionSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean potion_choosed, String currentState) {
        while (!potion_choosed) {

            String Choice = Main.scanner.nextLine();
            ArrayList<Potion> found = (ArrayList<Potion>) Hero.getPotionsNames().get(1);

            if (Choice.equals("back")) {
                //MAIN SCREEN
                Displayer.mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                potion_choosed = true;
            }

            try {
                int selected = Integer.parseInt(Choice);
                if (selected >= 1 & selected <= found.size()) {
                    Wizard.usePotion(found.get(selected - 1), Hero);
                    if (Testmain.musicEnabled) {
                        SoundEffectPlayer.play(MusicLibrary.potionSlurpFortnite);
                        SoundEffectPlayer.setVolume(0.1F);
                    }
                    currentState = "\n\n\n" + found.get(selected - 1).getName() + " used successfully!";
                    potion_choosed = true;
                    System.out.println("test");
                    Displayer.mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                } else {
                    Displayer.potionDisplayUpdate(displayer, enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                Displayer.potionDisplayUpdate(displayer, enemyList, Hero, true);
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
                Displayer.mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
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
                    Displayer.targetDisplayUpdate(displayer, "", enemyList, Hero, false);
                    while (!spell_choosed_state) {
                        Choice = Main.scanner.nextLine();
                        try {

                            int selectedTargetIndex = Integer.parseInt(Choice);


                            if (selectedTargetIndex < 0 | selectedTargetIndex > enemyList.size()) {
                                Displayer.targetDisplayUpdate(displayer, "", enemyList, Hero, true);
                            } else {
                                Enemy enemy = enemyList.get(selectedTargetIndex - 1);
                                spell_choosed_state = true;

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
                            Displayer.targetDisplayUpdate(displayer, "", enemyList, Hero, true);
                        }
                    }


                } else {
                    Displayer.spellDisplayUpdate(displayer, enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                Displayer.spellDisplayUpdate(displayer, enemyList, Hero, true);
            }

        }

        return spellReturn;
    }

//=========================================================================YEARLIST=============================================================

    static boolean Year_1(@NotNull Wizard Hero) throws InterruptedException {

        //initYear


        Enemy target;
        String currentState = "Un Troll Vient d'arriver et il est pas content donc bagar";
        AbstractSpell spell_choosed;


        ArrayList<EnemySpell> listTrollAttacks = new ArrayList<EnemySpell>();
        listTrollAttacks.add(EnemySpell.Troll_Hit());
        listTrollAttacks.add(EnemySpell.Troll_Throw());


        ArrayList<Enemy> enemyList = new ArrayList<>();

        //building enemies

        enemyList.add(Enemy.Troll(listTrollAttacks));
        //enemyList.add(Enemy.Trollette(listTrollAttacks));
        Hero.addSpell(Spell.Wingardium_Leviosa());


        Displayer displayer = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList),
                currentState,
                "1. Attack\n2. Use potion\n3. Dodge");


        displayer.display();
        while (!enemyList.isEmpty() & !Hero.isDead()) {

            boolean potion_choosed = false;
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
                        Displayer.spellDisplayUpdate(displayer, enemyList, Hero, false);

                        ArrayList spellResult = spellSwitchCase(displayer, enemyList, Hero, spell_choosed_state, attackResult, currentState, Choice);
                        spell_choosed_state = (boolean) spellResult.get(0);
                        attackResult = (String) spellResult.get(1);
                        break;

//==========================POTION========================
                    case "2":
                        //POTION SCREEN
                        Displayer.potionDisplayUpdate(displayer, enemyList, Hero, false);
                        potionSwitchCase(displayer, enemyList, Hero, potion_choosed, currentState);
                        break;


//==========================DODGE========================
                    case "3":
                        dodge_selected = true;
                        oldAgility = Hero.getDodgingChancePercentage();
                        if (Testmain.musicEnabled) {
                            SoundEffectPlayer.play(MusicLibrary.dodgeLoutre);
                            SoundEffectPlayer.setVolume(0.1F);
                        }
                        attackResult = "You are not attacking this turn, you restore " + ConsoleColors.RED_BOLD_BRIGHT + "❤ 30" + ConsoleColors.RESET + " and " +
                                ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 40 " + ConsoleColors.RESET + "\nYou also gain" + ConsoleColors.AGYLITYCOLOR_BOLD + " 💨 35%" + ConsoleColors.RESET + " more temporary Agility";
                        Effect.healthRegen(30, Hero);
                        Effect.manaRegen(40, Hero);
                        Effect.AgilityIncrease(35, Hero);

                        break;


                    default:
                        Displayer.mainDisplayUpdate(displayer, currentState, enemyList, Hero, true);
                        break;
                }

            } while (!dodge_selected & !spell_choosed_state);

            currentState = attackResult;

            currentState += "\n\n";
            for (Enemy enemy : enemyList) {
                if (!enemy.isDead() & !Hero.isDead()) {
                    currentState += enemy.attack(Hero) + "\n";
                }
            }
            Displayer.mainDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, false);
            //Choice = Main.scanner.nextLine();


            if (dodge_selected) {
                Hero.setDodgingChancePercentage(oldAgility);
            }

        }
        Hero.setMaxYear(1);
        if (Hero.isDead()) {
            Thread.sleep(5000);
            currentState = "You died <3";
            Displayer.endDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, false);
            if (Testmain.musicEnabled) {
                MusicPlayer.stopMusic();
                MusicPlayer.play(MusicLibrary.deathMusicAstronomia);
                MusicPlayer.setVolume(Testmain.musicVolume);

            }

        }
        if (enemyList.isEmpty()) {
            Thread.sleep(5000);
            currentState = "You passed 1st year with honors !\n"
                    + ConsoleColors.BLUE_BOLD + "-------REWARDS-------" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.YELLOW + "100 \uD83D\uDCB0" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.ORANGE + "200 " + ConsoleColors.RESET + "Exp points";


            Displayer.endDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, false);
        }
        while (true) { //Choice for shop or not

            String Choice = Main.scanner.nextLine();
            switch (Choice) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    Displayer.endDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, true);
            }

        }
    }


    static void ShopTime(@NotNull Wizard Hero, @NotNull Shop shop) {
        Displayer displayershop = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41),
                shop.displayShop(),
                "type the number of the item you want to buy  \ntype \"next\" to go to the next year");
        displayershop.display();
        String Choice = "";
        while (!Choice.equals("next")) {
            Choice = Main.scanner.nextLine();
            try {
                int choice = Integer.parseInt(Choice);

                if (choice > 0 & choice <= (shop.getAvaliablePotionMap().size()+shop.getAvaliableEquipementMap().size())) {
                    if (choice <= shop.getAvaliablePotionMap().size()) {
                        if (Hero.getGold() >= shop.getAvaliablePotionMap().values().toArray(new Integer[0])[choice - 1]) {
                            //ADDING POTION TO INVENTORY
                            Hero.addPotions(shop.getAvaliablePotionMap().keySet().toArray(new Potion[0])[choice - 1]);
                            //REMOVING GOLD
                            Hero.changeGold(-shop.getAvaliablePotionMap().values().toArray(new Integer[0])[choice - 1]);

                            String currentState = "\nYou bought " + shop.getAvaliablePotionMap()
                                    .keySet()
                                    .toArray(new Potion[0])[choice - 1]
                                    .getName() + " for " + shop
                                    .getAvaliablePotionMap()
                                    .values()
                                    .toArray(new Integer[0])[choice - 1] + " \uD83D\uDCB0";
                            Displayer.shopDisplayUpdate(displayershop, currentState, shop, Hero, false);
                        } else {
                            String currentState = "\nYou don't have enough "+ConsoleColors.YELLOW_BOLD+"gold"+ConsoleColors.RESET+" to buy this potion";
                            Displayer.shopDisplayUpdate(displayershop, currentState, shop, Hero, false);
                        }

                    } else {
                        if  (Hero.getGold() >= shop.getAvaliableEquipementMap().values().toArray(new Integer[0])[choice - 1 - shop.getAvaliablePotionMap().size()]) {
                            Hero.addEquipement(shop.getAvaliableEquipementMap()
                                    .keySet()
                                    .toArray(new Equipement[0])[choice - 1 - shop.getAvaliablePotionMap().size()]);
                            Hero.changeGold(-shop.getAvaliableEquipementMap().values().toArray(new Integer[0])[choice - 1 - shop.getAvaliablePotionMap().size()]);
                            String currentState = "\nYou bought " + shop.getAvaliableEquipementMap()
                                    .keySet()
                                    .toArray(new Equipement[0])[choice - 1 - shop.getAvaliablePotionMap().size()].getName() + " for " + shop.getAvaliableEquipementMap()
                                    .values()
                                    .toArray(new Integer[0])[choice - 1 - shop.getAvaliablePotionMap().size()] + " \uD83D\uDCB0";
                            Displayer.shopDisplayUpdate(displayershop, currentState, shop, Hero, false);
                        } else {
                            String currentState = "\nYou don't have enough "+ConsoleColors.YELLOW_BOLD+"gold"+ConsoleColors.RESET+" to buy this item";
                            Displayer.shopDisplayUpdate(displayershop, currentState, shop, Hero, false);
                        }

                    }
                }
                else {
                    String currentState = "\nPlease enter a valid number";
                    Displayer.shopDisplayUpdate(displayershop, currentState, shop, Hero, false);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }

        }
        System.out.println("test sortie");

    }


}


//TODO MUSIK KAN DEGA PRI
//TODO FADE IN MUSIC
//TODO FADE OUT MUSIC
//TODO REGLER ESPACEMENT ATTAQUES ENEMIES

