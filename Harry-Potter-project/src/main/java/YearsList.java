import java.util.ArrayList;

import Music.MusicLibrary;
import Music.MusicPlayer;
import Music.SoundEffectPlayer;
import SPELLS.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface YearsList {

    //MAIN MENU
    static void mainDisplayUpdate(@NotNull Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("1. Attack\n2. Use potion\n3. Dodge" + ((error) ? "\nPlease select one of those 3 options" : ""));
        displayer.display();
    }

    //TARGET MENU
    static void targetDisplayUpdate(@NotNull Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("Type the name of the Target to attack" + ((error) ? "\nPlease select a valid target" : ""));
        displayer.display();
    }

    //POTION CHOSING MENU
    static void potionDisplayUpdate(@NotNull Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(Hero.getPotionsNames().get(0).toString());
        displayer.setPlayerOptions("Type the name of the Potion you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid potion name" : ""));

        displayer.display();
    }

    //SPELL CHOOSING MENU
    static void spellDisplayUpdate(@NotNull Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(Hero.getKnownSpellsNames());
        displayer.setPlayerOptions("Type the name of the Spell you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid spell name" : ""));

        displayer.display();
    }

    //ENDGAME MENU
    static void endDisplayUpdate(@NotNull Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("1. Next Year\n2. Shop");

        displayer.display();
    }

    //POTION SWITCH CASE
    static void potionSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean potion_choosed, String currentState) {
        while (!potion_choosed) {

            String Choice = Main.scanner.nextLine();
            ArrayList<Potion> found = (ArrayList<Potion>) Hero.getPotionsNames().get(1);

            if (Choice.equals("back")) {
                //MAIN SCREEN
                mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                potion_choosed = true;
            }

            try {
                int selected = Integer.parseInt(Choice);
                if (selected >= 1 & selected <= found.size()) {
                    Wizard.usePotion(found.get(selected - 1), Hero);
                    SoundEffectPlayer.play(MusicLibrary.potionSlurpFortnite);
                    currentState = "\n\n\n" + found.get(selected - 1).getName() + " used successfully!";
                    potion_choosed = true;
                    System.out.println("test");
                    mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                } else {
                    potionDisplayUpdate(displayer, enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                potionDisplayUpdate(displayer, enemyList, Hero, true);
            }

        }

    }

    //POTION SWITCH CASE
    static @NotNull ArrayList spellSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean spell_choosed_state, String attackResult, String currentState, String Choice) {
        ArrayList spellReturn = new ArrayList();
        spellReturn.add(spell_choosed_state);
        spellReturn.add(attackResult);
        while (!spell_choosed_state) {

            Choice = Main.scanner.nextLine();
            if (Choice.equals("back")) {
                //BACK TO MAIN SCREEN
                mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                break;

            }
            try {

                int selectedSpellIndex = Integer.parseInt(Choice);

                if (selectedSpellIndex >= 0 & selectedSpellIndex <= Hero.getKnownSpells().size()) {

                    AbstractSpell spell_choosed = Hero.getKnownSpells().get(selectedSpellIndex - 1);

                    targetDisplayUpdate(displayer, "", enemyList, Hero, false);
                    while (!spell_choosed_state) {
                        Choice = Main.scanner.nextLine();
                        try {

                            int selectedTargetIndex = Integer.parseInt(Choice);


                            if (selectedTargetIndex < 0 | selectedTargetIndex > enemyList.size()) {
                                targetDisplayUpdate(displayer, "", enemyList, Hero, true);
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
                            targetDisplayUpdate(displayer, "", enemyList, Hero, true);
                        }
                    }


                } else {
                    spellDisplayUpdate(displayer, enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                spellDisplayUpdate(displayer, enemyList, Hero, true);
            }

        }

        return spellReturn;
    }


    static void Year_1(@NotNull Wizard Hero) throws InterruptedException {

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
                        spellDisplayUpdate(displayer, enemyList, Hero, false);

                        ArrayList spellResult = spellSwitchCase(displayer, enemyList, Hero, spell_choosed_state, attackResult, currentState, Choice);
                        spell_choosed_state = (boolean) spellResult.get(0);
                        attackResult = (String) spellResult.get(1);
                        break;

//==========================POTION========================
                    case "2":
                        //POTION SCREEN
                        potionDisplayUpdate(displayer, enemyList, Hero, false);
                        potionSwitchCase(displayer, enemyList, Hero, potion_choosed, currentState);
                        break;


//==========================DODGE========================
                    case "3":
                        dodge_selected = true;
                        oldAgility = Hero.getDodgingChancePercentage();
                        SoundEffectPlayer.play(MusicLibrary.dodgeLoutre);
                        SoundEffectPlayer.setVolume(0.1F);
                        attackResult = "You are not attacking this turn, you restore " + ConsoleColors.RED_BOLD_BRIGHT + "❤ 30" + ConsoleColors.RESET + " and " +
                                ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 40 " + ConsoleColors.RESET + "\nYou also gain" + ConsoleColors.AGYLITYCOLOR_BOLD + " 💨 35%" + ConsoleColors.RESET + " more temporary Agility";
                        Effect.healthRegen(30, Hero);
                        Effect.manaRegen(40, Hero);
                        Effect.AgilityIncrease(35, Hero);

                        break;


                    default:
                        mainDisplayUpdate(displayer, currentState, enemyList, Hero, true);
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
            mainDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, false);
            //Choice = Main.scanner.nextLine();


            if (dodge_selected) {
                Hero.setDodgingChancePercentage(oldAgility);
            }

        }
        Hero.setMaxYear(1);
        if (Hero.isDead()) {
            Thread.sleep(5000);
            currentState = "You died <3";
            endDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, false);
            MusicPlayer.stop();
            MusicPlayer.play(MusicLibrary.deathMusicAstronomia);
            MusicPlayer.setVolume(0.05F);


        }
        if (enemyList.isEmpty()) {
            Thread.sleep(5000);
            currentState = "You passed 1st year with honors !\n"
                    + ConsoleColors.BLUE_BOLD + "-------REWARDS-------" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.YELLOW + "100 \uD83D\uDCB0" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.ORANGE + "200 " + ConsoleColors.RESET + "Exp points";


            endDisplayUpdate(displayer, currentState.stripLeading(), enemyList, Hero, false);
        }
    }
    //TODO MUSIK KAN DEGA PRI
    //TODO FADE IN MUSIC
    //TODO REGLER ESPACEMENT ATTAQUES ENEMIES
}
