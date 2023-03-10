import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
enum Pet {
    OWL,
    RAT,
    CAT,
    TOAD,
    PUFF;

    public static Pet ChoosePet() {
        System.out.println("\n\nNOW PLEASE CHOOSE A PET BETWEEN THOSE ONES :\n" +
                "OWL\n" +
                "RAT\n" +
                "CAT\n" +
                "TOAD\n" +
                "PUFF");

        String temppet = null;
        while (true) {
            temppet = Main.scanner.nextLine();
            if (petNotContains(temppet)) {
                System.out.println("please enter a valid Pet name");
            } else {
                break;
            }
        }
        Pet result = null;
        for (Pet pet : values()) {
            if (pet.name().equalsIgnoreCase(temppet)) {
                result = pet;
                break;
            }
        }
        Pet chosenPet = result;
        System.out.printf("I SEE U CHOOSED %s, IT'S A GOOD IDEA BUT BE CAREFUL U LITTLE SHIT", chosenPet.name());

        return chosenPet;


    }

    public static boolean petNotContains(String test) {

        for (Pet c : Pet.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return false;
            }
        }

        return true;
    }

}

