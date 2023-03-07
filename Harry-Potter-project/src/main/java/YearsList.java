import java.util.ArrayList;

public interface YearsList {
    public static void Year_1(Wizard Hero){
        boolean completed = false;
        ArrayList enemyList = new ArrayList<>();
        Enemy Troll = Enemy.builder()

                .firstname("Troll")
                .level(14)
                .lastname("")
                .maxHealthPoints(1200)
                .currenthHealthPoints(1200)
                .build();
        String todisplay = "";
        todisplay=ActionCharacter.displayEnemyInfos(Troll)+todisplay;
        todisplay=ActionCharacter.displayPlayerInfos(Hero)+todisplay;
        ActionCharacter.displayer(todisplay);

        String firstname=Main.scanner.nextLine();
    }
}
