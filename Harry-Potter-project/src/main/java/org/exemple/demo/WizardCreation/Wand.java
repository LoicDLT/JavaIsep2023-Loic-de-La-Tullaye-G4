package org.exemple.demo.WizardCreation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Executables.Main;
import org.exemple.demo.Tools.ConsoleColors;

import java.util.InputMismatchException;


@Data
@AllArgsConstructor
public class Wand {
    private Core core;
    private int size;

    public static Wand ChooseWand()  {
        System.out.println("\nPlease choose a size for your wand, it should be between "
                + ConsoleColors.BLUE+"24 "+ ConsoleColors.RESET+"cm and "+ConsoleColors.BLUE+"37 "+ConsoleColors.RESET+"cm \n");

        int tempwandsize = 0;
        while (true) {
            try {
                tempwandsize = Main.scanner.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Please enter an integer value");
            }
            if (tempwandsize >37 || tempwandsize <24) {
                System.out.println("Please enter a valid Wand size");
                Main.scanner.nextLine();
            } else {
                break;
            }
        }

        return new Wand(Core.ChooseCore(), tempwandsize);


    }

}
