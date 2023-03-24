package org.exemple.demo.WizardCreation;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.exemple.demo.Executables.Main;

@ToString
@AllArgsConstructor
public enum House {
    GRYPHONDOR(1),
    HUFFLEPUFF(1.20F),
    RAVENCLAW(1),
    SLYTHERIN(1);
    private float PotionBuff;

    public static House ChooseHouse() {
        System.out.println("HELLO THERE !\nPLEASE CHOOSE A HOUSE BETWEEN THOSE ONES :\n" +
                "GRYPHONDOR\n" +
                "HUFFLEPUFF\n" +
                "RAVENCLAW\n" +
                "SLYTHERIN");
        String temphouse = null;
        while (true) {
            temphouse = Main.scanner.nextLine();
            House result = houseNotContains(temphouse);

            if (result==null) {
                System.out.println("please enter a valid House name");
            } else {
                System.out.printf("I SEE U CHOOSED %s, IT'S A GOOD IDEA", result.name());
                return result;
            }
        }
    }


    public static House houseNotContains(String test) {

        for (House c : House.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return c;
            }
        }

        return null;
    }


}