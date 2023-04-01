package org.exemple.demo.Spells;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ForbiddenSpell extends AbstractSpell{
    public static ForbiddenSpell sectumsempra(){
        ForbiddenSpell sectumsempra =
                ForbiddenSpell.builder()
                        .id(4)
                        .name("Sectumsempra")
                        .manaCost(200)
                        .chanceOfSuccess(70)
                        .script("You cast Sectumsempra on your enemy, This spell violently injures the target, causing deep cuts and severe blood loss.")
                        .damage(200)
                        .didNotWork("Unfortunatly it did not reach your enemy")
                        .curse(null)
                        .soundEffect(null)
                        .build();
        return sectumsempra;
    }
}
