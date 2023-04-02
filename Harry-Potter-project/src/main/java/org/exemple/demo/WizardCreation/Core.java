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
        System.out.println("\nPlease chose a core for your wand :\n" +
                "(1) Phoenix Feather\n" +
                "(2) Dragon Heartstring\n" +
                "(3) Unicorn Tail Hair\n\n");

        while (true) {
            Main.scanner.nextLine();
            String choice = Main.scanner.nextLine();
            switch (choice) {
                case "1":
                    return PHOENIX_FEATHER;
                case "2":
                    return DRAGON_HEARTSTRING;
                case "3":
                    return UNICORN_TAIL_HAIR;
                default:
                    System.out.println("please enter a valid number");
            }

        }
    }
}