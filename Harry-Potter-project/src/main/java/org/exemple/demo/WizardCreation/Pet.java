package org.exemple.demo.WizardCreation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.exemple.demo.Executables.Main;

@AllArgsConstructor
@ToString
@Getter
public enum Pet {
    OWL,
    RAT,
    CAT,
    TOAD,
    PUFF;

    public static Pet ChoosePet() {
        System.out.println("\nPlease choose a pet :\n" +
                "(1) Owl\n" +
                "(2) Rat \n" +
                "(3) Cat \n" +
                "(4) Toad \n" +
                "(5) Puff\n\n");

        while (true) {
            String choice = Main.scanner.nextLine();
            switch (choice) {
                case "1":
                    return OWL;
                case "2":
                    return RAT;
                case "3":
                    return CAT;
                case "4":
                    return TOAD;
                case "5":
                    return PUFF;
                default:
                    System.out.println("Please enter a valid number");

            }


        }

    }
}