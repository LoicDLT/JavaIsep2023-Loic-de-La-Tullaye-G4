package org.exemple.demo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.lang.Math;
public interface ActionCharacter {
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;
    //TODO DISPLAY THE EMOJI OF THE CHOSEN HOUSE
    static String displayPlayerInfos(@NotNull Wizard wizard ,boolean applyCurseDamage) {

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

    static String displayEnemyInfos(@NotNull ArrayList<Enemy> enemyList,boolean applyCurseDamage) {
        String string = "";
        int i=1;
        for (Enemy enemy : enemyList) {
             string+=String.format("%s[38;5;160m%-11s" + separator +
                            ConsoleColors.ORANGE_BOLD + "%-8s" + separator +
                            ConsoleColors.RED_BOLD_BRIGHT + "❤ %-9s" + separator +"\n"+ ConsoleColors.RESET,
                     "("+i+") ",enemy.getFirstname() + " " + enemy.getLastname(),
                    "Level " + enemy.getLevel(),
                     Math.round(enemy.getCurrentHealthPoints())
                             + "/" + Math.round(enemy.getMaxHealthPoints()) +
                             ((enemy.getCurseList().keySet().isEmpty())? "":  enemy.getCursesNames(applyCurseDamage)));
             i++;
        }
        return string.stripTrailing();
    }

}
