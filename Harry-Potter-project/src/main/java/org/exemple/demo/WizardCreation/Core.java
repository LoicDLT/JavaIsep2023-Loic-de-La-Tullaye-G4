package org.exemple.demo.WizardCreation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.exemple.demo.Executables.Main;

@AllArgsConstructor
@ToString
@Getter
public enum Core {
    PHOENIX_FEATHER,
    DRAGON_HEARTSTRING,
    UNICORN_TAIL_HAIR;

    public static Core ChooseCore() {
        System.out.println("\nNOW PLEASE CHOOSE A CORE BETWEEN THOSE ONES :\n" +
                "PHOENIX_FEATHER\n" +
                "DRAGON_HEARTSTRIN  G\n" +
                "UNICORN_TAIL_HAIR");

        String tempcore = null;
        tempcore = Main.scanner.nextLine();
        while (true) {
            tempcore = Main.scanner.nextLine();
            if (coreNotContains(tempcore)) {
                System.out.println("please enter a valid Core name");
            } else {
                break;
            }

        }
        Core result = null;
        for (Core core : values()) {
            if (core.name().equalsIgnoreCase(tempcore)) {
                result = core;
                break;
            }
        }
        Core chosenCore = result;


        return chosenCore;


    }

    public static boolean coreNotContains(String test) {

        for (Core c : Core.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return false;
            }
        }

        return true;
    }
}
