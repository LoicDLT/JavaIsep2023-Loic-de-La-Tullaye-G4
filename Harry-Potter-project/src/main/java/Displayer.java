import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class Displayer {
    private String characterInfos;
    private String whatHappend;
    private String playerOptions;

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
        System.out.println("=".repeat(100));

        System.out.println(characterInfos);

        //information on the actions
        System.out.println(whatHappend);

        //blank space
        System.out.println("\n".repeat(17-numberOfLines(characterInfos+whatHappend+playerOptions)));

        //what the player needs to do
        System.out.println(playerOptions);

        //bottom line
        System.out.println("=".repeat(100));

    }
    public static void printWithTypewriterEffect(String input) {
        if (input.length() == 0) {
            return;
        }
        System.out.print(input.charAt(0));
        try {
            Thread.sleep(60);
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printWithTypewriterEffect(input.substring(1));
    }


}