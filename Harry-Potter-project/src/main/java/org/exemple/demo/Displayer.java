package org.exemple.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class Displayer {
    private String characterInfos;
    private String whatHappend;
    private String playerOptions;
    private final String barreLimite = "=".repeat(150);
    private static final String barreEnemy = "-".repeat(45);

    public static void shopDisplayUpdate(Displayer displayer, String displayShop, Wizard hero) {
    }

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
        System.out.println("\n".repeat(17-numberOfLines(characterInfos+whatHappend+playerOptions)));

        //what the player needs to do
        System.out.println(playerOptions);

        //bottom line
        System.out.println(barreLimite);

    }
    public static void mainDisplayUpdate(@NotNull Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error,boolean applyCurseDamage) {

        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero,applyCurseDamage)
                + "-".repeat(41)
                + "\n" + ActionCharacter.displayEnemyInfos(enemyList,applyCurseDamage));

        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("1. Attack\n2. Use potion\n3. Dodge" + ((error) ? "\nPlease select one of those 3 options" : ""));
        displayer.display();
    }

    //TARGET MENU
    public static void targetDisplayUpdate(@NotNull Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions("Type the name of the Target to attack" + ((error) ? "\nPlease select a valid target" : ""));
        displayer.display();
    }

    //POTION CHOSING MENU
    public static void potionDisplayUpdate(@NotNull Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        displayer.setWhatHappend(Hero.getPotionsNames().get(0).toString());
        displayer.setPlayerOptions("Type the name of the Potion you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid potion name" : ""));

        displayer.display();
    }

    //SPELL CHOOSING MENU
    public static void spellDisplayUpdate(@NotNull Displayer displayer, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        displayer.setWhatHappend(Hero.getKnownSpellsNames());
        displayer.setPlayerOptions("Type the name of the Spell you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid spell name" : ""));

        displayer.display();
    }

    //ENDGAME MENU
    public static void endDisplayUpdate(@NotNull Displayer displayer, String whathappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        displayer.setWhatHappend(whathappend);
        displayer.setPlayerOptions(((error) ?"Please Enter a valid Option\n\n":"")+"1. Next Year\n2. Shop");

        displayer.display();
    }

    public static void shopDisplayUpdate(@NotNull Displayer displayer, String whatHappend, @NotNull Shop shop, Wizard Hero, boolean error) {
        displayer.setCharacterInfos(ActionCharacter.displayPlayerInfos(Hero,false));
        displayer.setWhatHappend(shop.displayShop()+"\n"+whatHappend);
        displayer.setPlayerOptions("type the number of the item you want to buy  \ntype \"next\" to go to the next year");
        displayer.display();
    }

}
