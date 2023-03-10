import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
enum House {
    GRYPHONDOR,
    HUFFLEPUFF,
    RAVENCLAW,
    SLYTHERIN;

    public static House ChooseHouse() {
        System.out.println("HELLO THERE !\nPLEASE CHOOSE A HOUSE BETWEEN THOSE ONES :\n" +
                "GRYPHONDOR\n" +
                "HUFFLEPUFF\n" +
                "RAVENCLAW\n" +
                "SLYTHERIN");
        String temphouse = null;
        while (true) {
            temphouse = Main.scanner.nextLine();
            if (houseNotContains(temphouse)) {
                System.out.println("please enter a valid House name");
            } else {
                break;
            }
        }
        House result = null;
        for (House house : values()) {
            if (house.name().equalsIgnoreCase(temphouse)) {
                result = house;
                break;
            }
            }
        House chosenHouse = result;
        System.out.printf("I SEE U CHOOSED %s, IT'S A GOOD IDEA BUT BE CAREFUL U LITTLE SHIT", chosenHouse.name());

        return chosenHouse;


            }
    public static boolean houseNotContains(String test) {

        for (House c : House.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return false;
            }
        }

        return true;
    }


}