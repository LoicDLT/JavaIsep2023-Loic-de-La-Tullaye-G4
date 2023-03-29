package org.exemple.demo.Usables;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Tools.ConsoleColors;

@Data
@AllArgsConstructor
 public class Equipement {

    private String name;
    private String color;
    private String script;
    private int damage;
    private boolean disapearAfterUse = false;




    public static Equipement swordOfGryphondor(){
        Equipement swordOfGryphondor = new Equipement("Sword of Gryffindor", ConsoleColors.GREEN, "",15,false);
        return swordOfGryphondor;
    }
    public static Equipement basiliskFang(){
        Equipement basiliskFang = new Equipement("Basilisk Fang", ConsoleColors.GREEN, "",600,true);
        return basiliskFang;
    }
}