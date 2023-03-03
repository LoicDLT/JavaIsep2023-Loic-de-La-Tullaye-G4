
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ActionCharacter {
    public static String separator = ConsoleColors.PURPLE_BOLD_BRIGHT + " || " + ConsoleColors.RESET;

    public static int numberOfLines(String string) {
        Matcher m = Pattern.compile("(\r\n)|(\n)|(\r)").matcher(string);
        int lines = 1;
        while (m.find()) {
            lines++;
        }
        return lines;
    }

    public static void displayer(String string) {

        System.out.println("=".repeat(100));
        System.out.println(string);
        System.out.println("\n".repeat(12-numberOfLines(string)));
        System.out.println("=".repeat(100));

    }

    static String displayPlayerInfos(Wizard wizard) {

        return String.format(ConsoleColors.WHITE_BOLD + "%s %s" + separator +
                        ConsoleColors.ORANGE_BOLD + "Level %d" + separator +
                        ConsoleColors.RED_BOLD_BRIGHT + "❤ %d/%d" + separator +
                        ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 %d/%d \n" + ConsoleColors.RESET,
                wizard.getFirstname(),
                wizard.getLastname(),
                wizard.getLevel(),
                wizard.getCurrenthHealthPoints(),
                wizard.getMaxHealthPoints(),
                wizard.getCurrentManaPoints(),
                wizard.getMaxManaPoints());
    }
}
