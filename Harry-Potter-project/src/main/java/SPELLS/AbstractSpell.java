package SPELLS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public abstract class AbstractSpell {
    private String name;
    private String script;
    private int manaCost;
    private float chanceOfSuccess;
    private int damage;
    private String didNotWork;
    private String soundEffect;

}
