package org.exemple.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
 class Equipement {
    private String name;
    private int price;
    private String color;
    private boolean disapearAfterUse = false;

    public static Equipement sword(){
        Equipement sword = new Equipement("Sword", 100, ConsoleColors.GREEN, false);
        return sword;
    }
}