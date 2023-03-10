package SPELLS;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class Spell extends AbstractSpell{


    public static Spell Wingardium_Leviosa(){
        Spell Wingardium_Leviosa =
                Spell.builder()
                        .name("Wingardium Leviosa")
                        .manaCost(50)
                        .chanceOfSuccess(80)
                        .script("You make objects fly above your enemy before releasing them all on him")
                        .damage(200)
                        .didNotWork("Unfortunatly it missed")
                        .build();
        return Wingardium_Leviosa;
    }

}
