package org.exemple.demo.Usables;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Tools.ConsoleColors;


@Data
@AllArgsConstructor
public class Potion {
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;
    private String name;
    private int price;
    private int amountOfHealthRegen;
    private int amountOfManaRegen;
    private int amountOfLuck;
    private int amountOfStrength;
    private String color;

    public String proprieties() {
        return String.format("%s :\n" +
                        ConsoleColors.RED_BOLD_BRIGHT + " ❤ %-4d" + separator +
                        ConsoleColors.BLUE_BOLD_BRIGHT + " \uD83C\uDF22 %-4d" + separator +
                        ConsoleColors.GREEN_BOLD_BRIGHT + " \uD83C\uDF40 %-4d" + separator +
                        ConsoleColors.YELLOW_BOLD_BRIGHT + " \uD83D\uDCAA %-4d\n" + ConsoleColors.RESET,
                name,
                amountOfHealthRegen,
                amountOfManaRegen,
                amountOfLuck,
                amountOfStrength);

    }

    public static Potion health_Potion() {
        Potion potion = new Potion("Health Potion",30, 100, 0, 0, 0,ConsoleColors.RED_BOLD_BRIGHT);
        return potion;
    }

    public static Potion mana_Potion() {
        Potion potion = new Potion("Mana Potion",30, 0, 150, 0, 0,ConsoleColors.BLUE_BOLD_BRIGHT);
        return potion;
    }

    public static Potion luck_Potion() {
        Potion potion = new Potion("Luck Potion",50, 0, 0, 25, 0,ConsoleColors.GREEN_BOLD_BRIGHT);
        return potion;
    }

    public static Potion strength_Potion() {
        Potion strengthPotion = new Potion("Strength Potion", 50, 0, 0, 0, 60, ConsoleColors.YELLOW_BOLD_BRIGHT);
        return strengthPotion;
    }


}
