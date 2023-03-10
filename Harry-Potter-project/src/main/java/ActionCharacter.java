import java.util.ArrayList;
import java.lang.Math;
public interface ActionCharacter {
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;



    static String displayPlayerInfos(Wizard wizard) {

        return String.format(ConsoleColors.CYAN_BOLD + "%-9s " + separator +
                        ConsoleColors.ORANGE_BOLD + "%-8s" + separator +
                        ConsoleColors.RED_BOLD_BRIGHT + "❤ %-9s" + separator +
                        ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 %.0f/%.0f " + separator +
                        ConsoleColors.GREEN_BOLD_BRIGHT + " \uD83C\uDF40 %.0f %% " + separator +
                        ConsoleColors.YELLOW_BOLD_BRIGHT + " \uD83D\uDCAA %.0f %% " + separator +
                        ConsoleColors.AGYLITYCOLOR_BOLD + " \uD83D\uDCA8 %.0f %%\n" + ConsoleColors.RESET,
                wizard.getFirstname() + " " + wizard.getLastname(),
                "Level "+wizard.getLevel(),
                Math.round(wizard.getCurrentHealthPoints())
                +"/"+Math.round(wizard.getMaxHealthPoints()),
                wizard.getCurrentManaPoints(),
                wizard.getMaxManaPoints(),
                wizard.getCurrentLuckPoints(),
                wizard.getCurrentStrengthPoints(),
                wizard.getDodgingChancePercentage());

    }

    static String displayEnemyInfos(ArrayList<Enemy> enemyList) {
        String string = "";
        for (Enemy enemy : enemyList) {
             string+=String.format("\033[38;5;160m%-10s" + separator +
                            ConsoleColors.ORANGE_BOLD + "%-8s" + separator +
                            ConsoleColors.RED_BOLD_BRIGHT + "❤ %-9s" + separator + ConsoleColors.RESET,
                    enemy.getFirstname() + " " + enemy.getLastname(),
                    "Level " + enemy.getLevel(),
                     Math.round(enemy.getCurrentHealthPoints())
                            + "/" + Math.round(enemy.getMaxHealthPoints()));

        }
        return string.stripTrailing();
    }
}