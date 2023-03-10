package SPELLS;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ForbiddenSpell extends AbstractSpell{
    private int enduranceCost;

}
