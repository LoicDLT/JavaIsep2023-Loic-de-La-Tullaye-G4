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
    public void mainDisplayUpdate( String whatHappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error,boolean applyCurseDamage) {

        this.characterInfos=(ActionCharacter.displayPlayerInfos(Hero,applyCurseDamage)
                + "-".repeat(41)
                + "\n" + ActionCharacter.displayEnemyInfos(enemyList,applyCurseDamage));

        this.whatHappend=(whatHappend);
        this.playerOptions=("1. Attack\n2. Use potion\n3. Dodge" + ((error) ? "\nPlease select one of those 3 options" : ""));
        display();
    }

    //TARGET MENU
    public void targetDisplayUpdate( String whatHappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        this.whatHappend=(whatHappend);
        this.playerOptions=("Type the name of the Target to attack" + ((error) ? "\nPlease select a valid target" : ""));
        display();
    }

    //POTION CHOSING MENU
    public void potionDisplayUpdate( ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        this.whatHappend=(Hero.getPotionsNames().get(0).toString());
        this.playerOptions=("Type the name of the Potion you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid potion name" : ""));

        display();
    }

    //SPELL CHOOSING MENU
    public void spellDisplayUpdate( ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        this.whatHappend=(Hero.getKnownSpellsNames());
        this.playerOptions=("Type the name of the Spell you want to use, type \"back\" if you want to go back" + ((error) ? "\nPlease select a valid spell name" : ""));

        display();
    }

    //ENDGAME MENU
    public void endDisplayUpdate( String whatHappend, ArrayList<Enemy> enemyList, Wizard Hero, boolean error) {
        this.characterInfos=(ActionCharacter.displayPlayerInfos(Hero,false) + barreEnemy + "\n" + ActionCharacter.displayEnemyInfos(enemyList,false));
        this.whatHappend=(whatHappend);
        this.playerOptions=(((error) ?"Please Enter a valid Option\n\n":"")+"1. Next Year\n2. Shop");

        display();
    }

    public void shopDisplayUpdate(String whatHappend, @NotNull Shop shop, Wizard Hero, boolean error) {
        this.characterInfos=(ActionCharacter.displayPlayerInfos(Hero,false));
        this.whatHappend= shop.displayShop()+"\n"+ whatHappend ;
        this.playerOptions=("type the number of the item you want to buy  \ntype \"next\" to go to the next year");
        display();
    }

}
