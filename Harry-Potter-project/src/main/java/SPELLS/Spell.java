package SPELLS;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class Spell extends AbstractSpell{


    public void initSpell(){
        Spell Wingardium_Leviosa = Spell.builder().name("Wingardium_Leviosa").manaCost(50).build();

    }

}
