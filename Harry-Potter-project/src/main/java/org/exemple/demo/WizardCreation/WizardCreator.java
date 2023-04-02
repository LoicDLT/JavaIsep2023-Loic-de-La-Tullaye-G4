package org.exemple.demo.WizardCreation;

import org.exemple.demo.Characters.Wizard;
import org.exemple.demo.Executables.Main;
import org.exemple.demo.Spells.AbstractSpell;
import org.exemple.demo.Spells.Curse;
import org.exemple.demo.Usables.Equipement;
import org.exemple.demo.Usables.Potion;

import java.util.ArrayList;
import java.util.HashMap;

public class WizardCreator {

    public static String chooseFirstName() {
        System.out.printf("Please enter your first name: ");
        return Main.scanner.nextLine();
    }
    public static String chooseLastName() {
        System.out.printf("Please enter your last name: ");
        return Main.scanner.nextLine();
    }
    public static Wizard createWizard() {



        return Wizard.builder()
                .level(1)
                .firstname(chooseFirstName())
                .lastname(chooseLastName())
                .levelUpRatio(0.20F)
                .maxHealthPoints(600)
                .currentHealthPoints(600)
                .knownSpells(new ArrayList<AbstractSpell>())
                .potions(new ArrayList<Potion>())
                .equipements(new ArrayList<Equipement>())
                .curseList(new HashMap<Curse, Integer>())
                .maxManaPoints(600)
                .currentManaPoints(600)
                .currentLuckPoints(0)
                .maxLuckPoints(100)
                .currentStrengthPoints(0)
                .maxStrengthPoints(999)
                .dodgingChancePercentage(0)
                .maxDodgingChancePercentage(100)
                .gold(10)
                .house(House.ChooseHouse())
                .pet(Pet.ChoosePet())
                .wand(Wand.ChooseWand())
                .build();
    }
}
