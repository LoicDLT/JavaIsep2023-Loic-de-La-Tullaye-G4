import SPELLS.AbstractSpell;
import com.sun.jdi.Method;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Rewards {
    private ArrayList<Potion> potionArrayList;
    private int experienceGain;
    private ArrayList<AbstractSpell> newSpells;


}



