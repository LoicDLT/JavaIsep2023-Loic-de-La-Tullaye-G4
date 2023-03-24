package org.exemple.demo.Usables;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Tools.ConsoleColors;

@Data
@AllArgsConstructor
 public class Equipement {
    private String name;
    private int price;
    private String color;
    private boolean disapearAfterUse = false;

    public static Equipement sword(){
        Equipement sword = new Equipement("Sword", 100, ConsoleColors.GREEN, false);
        return sword;
    }
}