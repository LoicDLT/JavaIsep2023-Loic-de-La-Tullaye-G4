package org.exemple.demo.WizardCreation;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.exemple.demo.Executables.Main;

@ToString
@AllArgsConstructor
public enum House {
    GRYPHONDOR(1),
    HUFFLEPUFF(1.30F),
    RAVENCLAW(1),
    SLYTHERIN(1);
    private float PotionBuff;

    public static House ChooseHouse() {
        System.out.println("\nPlease choose which quality describes you the best :\n" +
                "(1) Courage\n" +
                "(2) Loyalty \n" +
                "(3) Intelligence \n" +
                "(4) Ambition \n\n");

        while (true) {
            String choice = Main.scanner.nextLine();
            switch (choice) {
                case "1":
                    return GRYPHONDOR;
                case "2":
                    return HUFFLEPUFF;
                case "3":
                    return RAVENCLAW;
                case "4":
                    return SLYTHERIN;
                default:
                    System.out.println("Please enter a valid number");
            }


        }
    }

}
