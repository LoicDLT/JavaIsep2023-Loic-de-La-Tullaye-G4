package org.exemple.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.SPELLS.EnemySpell;

import java.util.ArrayList;

@Data
@AllArgsConstructor()
public class Year {

    private String currentState;
    private ArrayList<Enemy> enemyList;


    public void appendToCurrentState(String textToAppend) {
        currentState = currentState + textToAppend;
    }

    public static Year year1Constructor() {

        ArrayList<EnemySpell> listTrollAttacks = new ArrayList<>();
        listTrollAttacks.add(EnemySpell.Troll_Hit());
        listTrollAttacks.add(EnemySpell.Troll_Throw());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Troll(listTrollAttacks));
        enemyList.add(Enemy.Trollette(listTrollAttacks));

        Year year1 = new Year("Un Troll Vient d'arriver et il est pas content donc bagar", enemyList);

        return year1;
    }
    public static Year year2Constructor() {

        ArrayList<EnemySpell> listBasilikAttacks = new ArrayList<>();
        listBasilikAttacks.add(EnemySpell.Basiliks_Tail());
        listBasilikAttacks.add(EnemySpell.Basiliks_bite());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Basilisk(listBasilikAttacks));


        Year year2 = new Year("Vous vous trouvez dans l'antre du Basilic", enemyList);

        return year2;
    }

}