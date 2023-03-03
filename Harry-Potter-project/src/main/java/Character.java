import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public abstract class Character {
    private String firstname;
    private String lastname;
    private int maxHealthPoints;
    private int currenthHealthPoints;



    public abstract void attack();


}
