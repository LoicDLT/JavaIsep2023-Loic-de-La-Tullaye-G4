package org.exemple.demo.GameplayMaterial;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.exemple.demo.Characters.Enemy;
import org.exemple.demo.Characters.Wizard;
import org.exemple.demo.Tools.ConsoleColors;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor
public class Displayer {
    private String characterInfos;
    @NotNull
    private String whatHappend;
    @NotNull
    private String playerOptions;
    private final String barreLimite = "―".repeat(100);
    private final String barreEnemy = "-".repeat(45);


    public int numberOfLines(String string) {
        Matcher m = Pattern.compile("(\r\n)|(\n)|(\r)").matcher(string);
        int lines = 1;
        while (m.find()) {
            lines++;
        }
        return lines;
    }
    public void display() {

        //top line
        System.out.println(barreLimite);

        System.out.println(characterInfos);

        //information on the actions
        System.out.println(whatHappend);

        //blank space
        System.out.println("\n".repeat(18-numberOfLines(characterInfos+whatHappend+playerOptions)));

        //what the player needs to do
        System.out.println(playerOptions);

        //bottom line
        System.out.println(barreLimite);

    }
    public void mainDisplayUpdate(String whatHappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error, boolean applyCurseDamage) {

        this.characterInfos=(displayPlayerInfos(Hero,applyCurseDamage)
                + "-".repeat(41)
                + "\n" + displayEnemyInfos(enemyList,applyCurseDamage));

        this.whatHappend=(whatHappend);
        this.playerOptions=("1. Attack\n2. Use potion\n3. Equipements\n4. Dodge" + ((error) ? "\nPlease select one of those 3 options" : ""));
        display();
    }

    //TARGET MENU
    public void targetDisplayUpdate( String whatHappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(displayPlayerInfos(Hero,false) + barreEnemy + "\n" + displayEnemyInfos(enemyList,false));
        this.whatHappend=(whatHappend);
        this.playerOptions=("Type the name of the Target to attack" + ((error) ? "\nPlease select a valid target" : ""));
        display();
    }

    //POTION CHOSING MENU
    public void potionDisplayUpdate( ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(displayPlayerInfos(Hero,false) + barreEnemy + "\n" + displayEnemyInfos(enemyList,false));
        this.whatHappend=(Hero.getPotionsNames().get(0).toString());
        this.playerOptions=("Type the name of the Potion you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid potion name" : ""));

        display();
    }
    public void equipementDisplayUpdate( ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(displayPlayerInfos(Hero,false) + barreEnemy + "\n" + displayEnemyInfos(enemyList,false));
        this.whatHappend=(Hero.getEquipementNames());
        this.playerOptions=("Type the name of the item you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid item name" : ""));

        display();
    }

    //SPELL CHOOSING MENU
    public void spellDisplayUpdate( ArrayList<Enemy> enemyList, Wizard Hero, boolean error,boolean notEnoughMana) {
        this.characterInfos=(displayPlayerInfos(Hero,false) + barreEnemy + "\n" + displayEnemyInfos(enemyList,false));
        this.whatHappend=(Hero.getKnownSpellsNames()+((notEnoughMana)?"\n\nYou don't have enough mana to cast this spell":""));
        this.playerOptions=("Type the name of the Spell you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid spell name" : ""));

        display();
    }

    //ENDGAME MENU
    public void endDisplayUpdate( String whatHappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(displayPlayerInfos(Hero,false) + barreEnemy + "\n" + displayEnemyInfos(enemyList,false));
        this.whatHappend=(whatHappend);
        this.playerOptions=(((error) ?"Please Enter a valid Option\n\n":"")+"1. Next Year\n2. Shop");

        display();
    }

    public void shopDisplayUpdate(String whatHappend, String displayShop, Wizard Hero, boolean error) {
        this.characterInfos=(displayPlayerInfos(Hero,false));
        this.whatHappend= displayShop+"\n"+ whatHappend ;
        this.playerOptions=("type the number of the item you want to buy  \ntype \"next\" to go to the next year");
        display();
    }

    public String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;
    //TODO DISPLAY THE EMOJI OF THE CHOSEN HOUSE
    public String displayPlayerInfos(@NotNull Wizard wizard , boolean applyCurseDamage) {
        String test = String.format("%-18s test","anniv");

        return String.format(ConsoleColors.CYAN_BOLD + "%-14s " + separator +
                        ConsoleColors.ORANGE_BOLD + "%-8s" + separator +
                        ConsoleColors.RED_BOLD_BRIGHT + "❤ %-9s" + separator +
                        ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 %.0f/%.0f " + separator +
                        ConsoleColors.GREEN_BOLD_BRIGHT + " \uD83C\uDF40 %.0f %% " + separator +
                        ConsoleColors.YELLOW_BOLD_BRIGHT + " \uD83D\uDCAA %.0f %% " + separator +
                        ConsoleColors.AGYLITYCOLOR_BOLD + " \uD83D\uDCA8 %.0f %% " + separator +
                        ConsoleColors.YELLOW_BOLD + "\uD83D\uDCB0 %d "+ConsoleColors.RESET+
                        ((wizard.getCurseList().keySet().isEmpty())? "": separator + wizard.getCursesNames(applyCurseDamage))+"\n",

                "\uD83E\uDDD9 "+wizard.getFirstname() + " " + wizard.getLastname(),
                "Level "+wizard.getLevel(),
                Math.round(wizard.getCurrentHealthPoints())
                        +"/"+Math.round(wizard.getMaxHealthPoints()),
                wizard.getCurrentManaPoints(),
                wizard.getMaxManaPoints(),
                wizard.getCurrentLuckPoints(),
                wizard.getCurrentStrengthPoints(),
                wizard.getDodgingChancePercentage(),
                wizard.getGold());

    }

    public String displayEnemyInfos(@NotNull ArrayList<Enemy> enemyList, boolean applyCurseDamage) {
        String string = "";
        int i=1;
        for (Enemy enemy : enemyList) {
            string+=String.format("%s[38;5;160m%-11s" + separator +
                            ConsoleColors.ORANGE_BOLD + "%-8s" + separator +
                            ConsoleColors.RED_BOLD_BRIGHT + "❤ %-9s" +separator + "%s\n"+ ConsoleColors.RESET,
                    "("+i+") ",enemy.getFirstname() + " " + enemy.getLastname(),
                    "Level " + enemy.getLevel(),
                    Math.round(enemy.getCurrentHealthPoints())
                            + "/" + Math.round(enemy.getMaxHealthPoints()) ,
                            ((enemy.getCurseList().keySet().isEmpty())? "" : enemy.getCursesNames(applyCurseDamage)));
            i++;
        }
        return string.stripTrailing();
    }
    public void eventDisplayUpdate( String whatHappend,String playerOptions, Wizard Hero) {
        this.characterInfos="\n".repeat(7);
        this.whatHappend=whatHappend ;
        this.playerOptions=playerOptions;
        display();
    }
}
