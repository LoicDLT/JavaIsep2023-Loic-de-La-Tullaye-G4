public class Effect{
    static void healthRegen(float amountOfHeal,  Character character) {
        float tempHealth = character.getCurrentHealthPoints();
        float tempMaxHealth = character.getMaxHealthPoints();

        if ((tempHealth + amountOfHeal) > tempMaxHealth) {
            character.setCurrentHealthPoints(tempMaxHealth);
        } else if ((tempHealth + amountOfHeal) <= 0){
            character.setCurrentHealthPoints(0);
            character.setDead(true);
        }
        else character.setCurrentHealthPoints(tempHealth + amountOfHeal);
    }

    static void manaRegen(float amountOfMana,Wizard wizard ) {
        float tempMana = wizard.getCurrentManaPoints();
        float tempMaxMana = wizard.getMaxManaPoints();

        if ((tempMana + amountOfMana) > tempMaxMana) {
            wizard.setCurrentManaPoints(tempMaxMana);
        } else {
            wizard.setCurrentManaPoints(tempMana + amountOfMana);
        }
    }


    static void LuckIncrease(float amountOfLuck,Wizard wizard ) {
        float tempLuck = wizard.getCurrentLuckPoints();
        float tempMaxLuck = wizard.getMaxLuckPoints();

        if ((tempLuck + amountOfLuck) > tempMaxLuck) {
            wizard.setCurrentLuckPoints(tempMaxLuck);
        } else {
            wizard.setCurrentLuckPoints(tempLuck + amountOfLuck);
        }
    }
    static void StrengthIncrease(float amountOfStrength,Wizard wizard ) {
        float tempStrength = wizard.getCurrentStrengthPoints();
        float tempMaxStength = wizard.getMaxStrengthPoints();

        if ((tempStrength + amountOfStrength) > tempMaxStength) {
            wizard.setCurrentStrengthPoints(tempMaxStength);
        } else {
            wizard.setCurrentStrengthPoints(tempStrength + amountOfStrength);
        }
    }
    static void AgilityIncrease(float amountOfAbility,Wizard wizard ) {
        float tempAbility = wizard.getDodgingChancePercentage();
        float tempMaxAbility = wizard.getMaxDodgingChancePercentage();

        if ((tempAbility + amountOfAbility) > tempMaxAbility) {
            wizard.setDodgingChancePercentage(tempMaxAbility);
        } else {
            wizard.setDodgingChancePercentage(tempAbility + amountOfAbility);
        }
    }
}
