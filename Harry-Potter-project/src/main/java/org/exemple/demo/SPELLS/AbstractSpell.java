package org.exemple.demo.SPELLS;
import org.exemple.demo.Curse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public abstract class AbstractSpell {
    private int id;
    private String name;
    private String script;
    private int manaCost;
    private float chanceOfSuccess;
    private int damage;
    private String didNotWork;
    private String soundEffect;
    private Curse curse;

}
