import SPELLS.AbstractSpell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class Character {
    private boolean dead;
    private String firstname;
    private String lastname;
    private float maxHealthPoints;
    private float currentHealthPoints;
    private float dodgingChancePercentage;
    private float maxDodgingChancePercentage;


    //===============================================================COMBAT=================================================================



}
