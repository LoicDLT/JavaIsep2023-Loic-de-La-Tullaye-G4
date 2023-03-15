import java.util.Scanner;

import static java.lang.String.valueOf;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        House chosenHouse = House.ChooseHouse();
        System.out.printf("\nwell well well little %s guy ", chosenHouse.name()); //TEST FOR HOUSES

        Pet chosenPet = Pet.ChoosePet();
        System.out.printf("\nwell well well little %s guy ", chosenPet.name()); //TEST FOR PET

        Wand chosenWand = Wand.ChooseWand();
        System.out.printf("\nthen you choosed a %d cm wand with %s as it's core\n", chosenWand.getSize(), valueOf((chosenWand.getCore().name()))); //TEST FOR WAND

        Wizard Hero = Wizard.builder()
                .level(1)
                .firstname("Jo")
                .lastname("Mama")
                .maxHealthPoints(150)
                .currentHealthPoints(150)
                .knownSpells(null)
                .potions(null)
                .maxManaPoints(300)
                .currentManaPoints(300)
                .pet(chosenPet)
                .wand(chosenWand)
                .house(chosenHouse)
                .build();

        ActionCharacter.displayPlayerInfos(Hero);


    }
}

