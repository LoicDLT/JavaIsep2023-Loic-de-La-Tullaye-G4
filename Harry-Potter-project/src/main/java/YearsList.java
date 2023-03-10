import java.util.ArrayList;
import java.util.LinkedHashMap;

import SPELLS.*;
import org.jetbrains.annotations.NotNull;


public interface YearsList {


    static void mainDisplayUpdate(Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("1. Attack\n2. Use potion\n3. Dodge" + ((error) ? "\nPlease select one of those 3 options" : ""));
        displayer.display();
    }

    static void targetDisplayUpdate(Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("Type the name of the Target to attack" + ((error) ? "\nPlease select a valid target" : ""));
        displayer.display();
    }

    static void potionDisplayUpdate(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(Hero.getPotionsNames());
        displayer.setPlayerOptions("Type the name of the Potion you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid potion name" : ""));

        displayer.display();
    }

    static void spellDisplayUpdate(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(Hero.getKnownSpellsNames());
        displayer.setPlayerOptions("Type the name of the Spell you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid spell name" : ""));

        displayer.display();
    }

    static void runningDisplayUpdate(Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("");

        displayer.display();
    }

    static void potionSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean potion_choosed, String currentState) {
        while (!potion_choosed) {

            String Choice = Main.scanner.nextLine();
            if (Choice.equals("back")) {
                //MAIN SCREEN
                mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                potion_choosed = true;

            } else if (Hero.stringToPotion(Choice) != null) {
                Wizard.usePotion(Hero.stringToPotion(Choice), Hero);
                currentState = "Potion Utilisée !";
                potion_choosed = true;
                mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);

            } else {
                potionDisplayUpdate(displayer, enemyList, Hero, true);
            }

        }

    }

    static ArrayList spellSwitchCase(Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean spell_choosed_state, String attackResult, String currentState, String Choice) {
        ArrayList spellReturn = new ArrayList();
        spellReturn.add(spell_choosed_state);
        spellReturn.add(attackResult);
        while (!spell_choosed_state) {

            Choice = Main.scanner.nextLine();
            if (Choice.equals("back")) {
                //BACK TO MAIN SCREEN
                mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
                break;

            } else if (Hero.stringToSpell(Choice) != null) {

                AbstractSpell spell_choosed = Hero.stringToSpell(Choice);


                targetDisplayUpdate(displayer, "", enemyList, Hero, false);
                while (!spell_choosed_state) {
                    Choice = Main.scanner.nextLine();
                    if (stringToEnemy(Choice, enemyList) == null) {
                        targetDisplayUpdate(displayer, "", enemyList, Hero, true);
                    } else {

                        spell_choosed_state = true;

                        attackResult = Hero.attack(stringToEnemy(Choice, enemyList), spell_choosed);
                        spellReturn.clear();
                        spellReturn.add(spell_choosed_state);
                        spellReturn.add(attackResult);
                        return spellReturn;

                    }
                }


            } else {
                spellDisplayUpdate(displayer, enemyList, Hero, true);
            }

        }

        return spellReturn;
    }

    static Enemy stringToEnemy(String enemyName, ArrayList<Enemy> enemyList) {
        for (Enemy enemy : enemyList) {
            if (enemy.getFirstname().equalsIgnoreCase(enemyName)) {
                return enemy;
            }
        }
        return null;
    }

    static void Year_1(Wizard Hero) {

        //initYear
        boolean completed = false;

        Enemy target;
        String currentState = "Un Troll Vient d'arriver et il est pas content donc bagar";
        AbstractSpell spell_choosed;


        ArrayList<EnemySpell> listTrollAttacks = new ArrayList<EnemySpell>();
        listTrollAttacks.add(EnemySpell.Troll_Hit());
        listTrollAttacks.add(EnemySpell.Troll_Throw());


        ArrayList<Enemy> enemyList = new ArrayList<>();

        //building enemies
        Enemy Troll = Enemy.builder()
                .firstname("Troll")
                .level(14)
                .lastname("")
                .maxHealthPoints(1200)
                .attackList(listTrollAttacks)
                .currentHealthPoints(1200)
                .dodgingChancePercentage(20)
                .maxDodgingChancePercentage(100)
                .build();
        enemyList.add(Troll);


        Hero.addSpell(Spell.Wingardium_Leviosa());


        Displayer displayer = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero) + "-".repeat(41) + "\n" + ActionCharacter.displayEnemyInfos(enemyList),
                currentState,
                "1. Attack\n2. Use potion\n3. Dodge");


        displayer.display();
        while (!completed) {

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

                        attackResult = "You are not attacking this turn, you restore " + ConsoleColors.RED_BOLD_BRIGHT + "❤ 30" + ConsoleColors.RESET + " and " +
                                ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 40 " + ConsoleColors.RESET + "\nYou also gain" + ConsoleColors.AGYLITYCOLOR_BOLD + " 💨 35%" + ConsoleColors.RESET + " more temporary Agility";
                        Effect.healthRegen(30, Hero);
                        Effect.manaRegen(40, Hero);
                        Effect.AgilityIncrease(35, Hero);

                        break;
                }

            } while (!dodge_selected & !spell_choosed_state);

            currentState = attackResult;


            for (Enemy enemy : enemyList) {
                currentState += "\n\n" + enemy.attack(Hero);

            }


            mainDisplayUpdate(displayer, currentState, enemyList, Hero, false);
            //Choice = Main.scanner.nextLine();


            if (dodge_selected) {
                Hero.setDodgingChancePercentage(oldAgility);
            }
        }
    }
}
