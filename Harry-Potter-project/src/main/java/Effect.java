public class Effect{
    static void healthRegen(int amountOfHeal,  Character character) {
        int temphealth = character.getCurrenthHealthPoints();
        int tempmaxhealth = character.getMaxHealthPoints();

        if ((temphealth + amountOfHeal) > tempmaxhealth) {
            character.setCurrenthHealthPoints(tempmaxhealth);
        } else {
            character.setCurrenthHealthPoints(temphealth + amountOfHeal);
        }
    }

    static void manaRegen(int amountOfMana,Wizard wizard ) {
        int tempmana = wizard.getCurrentManaPoints();
        int tempmaxmana = wizard.getMaxManaPoints();

        if ((tempmana + amountOfMana) > tempmaxmana) {
            wizard.setCurrentManaPoints(tempmaxmana);
        } else {
            wizard.setCurrentManaPoints(tempmana + amountOfMana);
        }
    }
}
