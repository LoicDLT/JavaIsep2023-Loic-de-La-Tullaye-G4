package SPELLS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public abstract class AbstractSpell {
    private String name;
    private int manaCost;
    private SpellEffect spellEffect;

    private String color;
}
