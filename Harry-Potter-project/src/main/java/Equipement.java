import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
 class Equipement {
    private String name;
    private int price;

    public static Equipement sword(){
        Equipement sword = new Equipement("Sword", 100);
        return sword;
    }
}