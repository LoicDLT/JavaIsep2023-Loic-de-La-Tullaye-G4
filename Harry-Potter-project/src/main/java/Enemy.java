import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Enemy extends AbstractEnemy {

    private boolean isSelected;
    @Override
    public void attack() {
    }
}
