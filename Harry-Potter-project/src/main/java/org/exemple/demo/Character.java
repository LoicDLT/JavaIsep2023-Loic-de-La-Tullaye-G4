package org.exemple.demo;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@Data
@SuperBuilder(toBuilder = true)
public abstract class Character {
    private boolean dead;
    private String firstname;
    private String lastname;
    private float maxHealthPoints;
    private float currentHealthPoints;
    private float dodgingChancePercentage;
    private float maxDodgingChancePercentage;
    private HashMap<Curse,Integer> curseList;

    public void healthRegen(float amountOfHeal) {
        float tempHealth = currentHealthPoints;
        float tempMaxHealth = maxHealthPoints;

        if ((tempHealth + amountOfHeal) > tempMaxHealth) {
            currentHealthPoints=tempMaxHealth;
        } else if ((tempHealth + amountOfHeal) <= 0){
            currentHealthPoints=0;
            dead=true;
        }
        else currentHealthPoints=(tempHealth + amountOfHeal);
    }

    public void AgilityIncrease(float amountOfAbility ) {
        float tempAbility = dodgingChancePercentage;
        float tempMaxAbility = maxDodgingChancePercentage;

        if ((tempAbility + amountOfAbility) > tempMaxAbility) {
            dodgingChancePercentage=(tempMaxAbility);
        } else {
            dodgingChancePercentage=(tempAbility + amountOfAbility);
        }
    }



    public String ApplyCurse(Curse curse,boolean wizardOrEnemy){
            curseList.put(curse,curse.getCurseDuration());
            return (wizardOrEnemy ? "You" : "The enemy") + " have been cursed by " + curse.getCurseName() + " for " + curse.getCurseDuration() + " turns";
    }
    public String getCursesNames(boolean applyCurseDamage){
        String cursesNames = "(";
        int i = 1;
        int SIZE = curseList.keySet().size();
        float CurseDamageCharacter = 0;



        for (Curse curse : curseList.keySet()) {
            cursesNames += curse.getAdjective() + " "+ ConsoleColors.BLUE_BOLD+curseList.get(curse) +ConsoleColors.RESET + " turns left"+((i == SIZE)? "" : ", ");
            CurseDamageCharacter += curse.getCurseDamage();
            i++;
        }
        if (applyCurseDamage){
            healthRegen(-CurseDamageCharacter);
            if (currentHealthPoints <= 0){
                dead = true;
            }
        }
        cursesNames+=")"+ ((applyCurseDamage &CurseDamageCharacter!=0)? " Curse damage : -" + ConsoleColors.RED_BOLD_BRIGHT + Math.round(CurseDamageCharacter)+" ❤"+ConsoleColors.RESET : "");
        return cursesNames;

    }
    public void removeCurse(Curse curse){
        curseList.remove(curse);
    }
    public void reduceAllCursetime(){
        if (curseList==null) return;
        for (Curse curse : curseList.keySet()) {
            curseList.put(curse,curseList.get(curse)-1);
            if (curseList.get(curse) < 0){
                removeCurse(curse);
            }
        }
    }
    public void reduceCursetime(Curse curse, int turns){
            if (curseList.get(curse) > 1){
            curseList.put(curse, curseList.get(curse) - turns);
                if (curseList.get(curse) < 0){
                    removeCurse(curse);
                }
        }
    }
}
