package org.exemple.demo.Usables;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Spells.Curse;
import org.exemple.demo.Tools.ConsoleColors;

@Data
@AllArgsConstructor
 public class Equipement {

    private int id;
    private String name;

    private String color;

    private String script;

    private String description;

    private int damage;

    private float percentOfLifeDamage;

    private Curse curse;

    private boolean disapearAfterUse = false;

    public static Equipement swordOfGryphondor(){
        Equipement swordOfGryphondor = new Equipement(1,"Sword of Gryffindor",
                ConsoleColors.GREEN,
                "you swing your sword and hit your enemy",
                "The sword of Gryphondor is higly effective against the basilik",
                30,
                0,
                null,
                false);
        return swordOfGryphondor;
    }
    public static Equipement basiliskFang(){
        Equipement basiliskFang = new Equipement(2,"Basilisk Fang",
                ConsoleColors.GREEN,
                "you strike your enemy with the basilisk fang, dealing 60% of his life points",
                "The basilik Fang can remove 60% of the enemy's life points, but can only be used once",
                0,
                50,
                null,
                true);
        return basiliskFang;
    }
}