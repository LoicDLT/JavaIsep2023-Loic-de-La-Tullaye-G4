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

    public String ApplyCurse(Curse curse,boolean wizardOrEnemy){
            curseList.put(curse,curse.getCurseDuration());
            return (wizardOrEnemy ? "You" : "The enemy") + " have been cursed by " + curse.getCurseName() + " for " + curse.getCurseDuration() + " turns";
    }
    public String getCursesNames(){
        String cursesNames = "(";
        for (Curse curse : curseList.keySet()) {
            cursesNames += curse.getAdjective() + " "+ ConsoleColors.BLUE_BOLD+curseList.get(curse) +ConsoleColors.RESET + " turns left)";
        }
        return cursesNames;

    }
    public void removeCurse(Curse curse){
        curseList.remove(curse);
    }
    public void reduceAllCursetime(){
        if (curseList==null) return;
        for (Curse curse : curseList.keySet()) {
            curseList.put(curse,curseList.get(curse)-1);
            if (curseList.get(curse) == 0){
                removeCurse(curse);
            }
        }
    }
    public void reduceCursetime(Curse curse, int turns){
            if (curseList.get(curse) > 1){
            curseList.put(curse, curseList.get(curse) - turns);
                if (curseList.get(curse) == 0){
                    removeCurse(curse);
                }
        }
    }
}
