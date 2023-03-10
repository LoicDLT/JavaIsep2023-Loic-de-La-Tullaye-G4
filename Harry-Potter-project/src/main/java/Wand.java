import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.InputMismatchException;


@Data
@AllArgsConstructor
public class Wand {
    private Core core;
    private int size;

    public static Wand ChooseWand() throws InterruptedException {
        System.out.println("\nNOW PLEASE CHOOSE A SIZE FOR YOUR WAND, it should be between "
                +ConsoleColors.BLUE+"24 "+ ConsoleColors.RESET+"cm and "+ConsoleColors.BLUE+"37 "+ConsoleColors.RESET+"cm \n");

        int tempwandsize = 0;
        while (true) {
            try {
                tempwandsize = Main.scanner.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Error – Please enter an integer value");
            }
            if (tempwandsize >37 || tempwandsize <24) {
                System.out.println("please enter a valid Wand size");
                Main.scanner.nextLine();
            } else {
                break;
            }
        }
        Thread.sleep(200);
        Core chosenCore=Core.ChooseCore();



        Wand chosenWand = new Wand(chosenCore, tempwandsize);


        return chosenWand;


    }

}
