package org.exemple.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.exemple.demo.Music.MusicLibrary;
import org.exemple.demo.Music.MusicPlayer;
import org.exemple.demo.Music.SoundEffectPlayer;
import org.exemple.demo.SPELLS.AbstractSpell;
import org.exemple.demo.SPELLS.EnemySpell;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor()
public class Year {
    @NonNull
    private String currentState;
    @NonNull
    private ArrayList<Enemy> enemyList;
    private boolean potion_choosed = false;
    private boolean equipement_chosed = false;
    private boolean spell_choosed_state = false;
    private boolean dodge_selected = false;
    private float oldAgility = 0;
    private String attackResult = "";

    public static Year year1Constructor() {

        ArrayList<EnemySpell> listTrollAttacks = new ArrayList<>();
        listTrollAttacks.add(EnemySpell.Troll_Hit());
        listTrollAttacks.add(EnemySpell.Troll_Throw());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Troll(listTrollAttacks));
        enemyList.add(Enemy.Trollette(listTrollAttacks));

        Year year1 = new Year("Un Troll Vient d'arriver et il est pas content donc bagar", enemyList);

        return year1;
    }
    public static Year year2Constructor() {

        ArrayList<EnemySpell> listBasilikAttacks = new ArrayList<>();
        listBasilikAttacks.add(EnemySpell.Basiliks_Tail());
        listBasilikAttacks.add(EnemySpell.Basiliks_bite());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Basilisk(listBasilikAttacks));


        Year year2 = new Year("Vous vous trouvez dans l'antre du Basilic", enemyList);

        return year2;
    }
    public boolean choiceForShop(Wizard Hero, Displayer displayer) {
        while (true) {
            //Choice for shop or not

            String Choice = Main.scanner.nextLine();
            switch (Choice) {
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    displayer.endDisplayUpdate(currentState.stripLeading(), enemyList, Hero, true);
            }

        }
    }
    public void winOrLoose(Wizard Hero, Displayer displayer) throws InterruptedException {
        if (Hero.isDead()) {
            Thread.sleep(5000);
            currentState = ("You died <3");
            displayer.endDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false);
            if (Testmain.musicEnabled) {
                MusicPlayer.stopMusic();
                MusicPlayer.play(MusicLibrary.deathMusicAstronomia);
                MusicPlayer.setVolume(Testmain.musicVolume);

            }

        }
        if (enemyList.isEmpty()) {
            Thread.sleep(5000);
            currentState = ("You passed 1st year with honors !\n"
                    + ConsoleColors.BLUE_BOLD + "-------REWARDS-------" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.YELLOW + "100 \uD83D\uDCB0" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.ORANGE + "200 " + ConsoleColors.RESET + "Exp points");


            displayer.endDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false);
        }
    }
    public void enemyAttack(Wizard Hero, Displayer displayer) {
        for (Enemy enemy : enemyList) {
            if (!enemy.isDead() & !Hero.isDead()) {
                currentState += enemy.attack(Hero) + "\n";
            }
        }
        displayer.mainDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false, true);
    }
    public void CurseCooldown(Wizard Hero) {
        for (Enemy enemy : enemyList) {
            enemy.reduceAllCurseTime();
        }
        Hero.reduceAllCurseTime();
    }
    public void potionSwitchCase(Displayer displayer, Wizard Hero) {
        while (!potion_choosed) {

            String Choice = Main.scanner.nextLine();
            ArrayList<Potion> found = (ArrayList<Potion>) Hero.getPotionsNames().get(1);

            if (Choice.equals("back")) {
                //MAIN SCREEN
                displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                potion_choosed = true;
            }

            try {
                int selected = Integer.parseInt(Choice);
                if (selected >= 1 & selected <= found.size()) {
                    Hero.usePotion(found.get(selected - 1));
                    if (Testmain.musicEnabled) {
                        SoundEffectPlayer.play(MusicLibrary.potionSlurpFortnite);
                        SoundEffectPlayer.setVolume(0.1F);
                    }
                    currentState = "\n\n\n" + found.get(selected - 1).getName() + " used successfully!";
                    potion_choosed = true;

                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                } else {
                    displayer.potionDisplayUpdate(enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                displayer.potionDisplayUpdate(enemyList, Hero, true);
            }

        }

    }
    public boolean equipementSwitchCase(Displayer displayer, Wizard Hero) {

        if (Hero.getEquipements().isEmpty()) {
            currentState = ("\n\nYou don't have any equipement ");
            displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
            return equipement_chosed;
        } else if (equipement_chosed) {
            currentState = ("\n\nYou have already used equipement this turn ");
            displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
            return equipement_chosed;
        } else {
            displayer.equipementDisplayUpdate(enemyList, Hero, false);


            while (true) {

                String Choice = Main.scanner.nextLine();

                if (Choice.equals("back")) {
                    //MAIN SCREEN
                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);

                    return false;
                }

                try {
                    int selected = Integer.parseInt(Choice);
                    if (selected >= 1 & selected <= Hero.getEquipements().size()) {
                        Hero.useEquipement(Hero.getEquipements().get(selected - 1));
                        currentState = "\n\n\n" + Hero.getEquipements().get(selected - 1).getName() + " used successfully!";
                        displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                        return true;
                    } else {
                        displayer.potionDisplayUpdate(enemyList, Hero, true);
                    }
                } catch (NumberFormatException e) {
                    displayer.potionDisplayUpdate(enemyList, Hero, true);
                }

            }
        }
    }
    public @NotNull void spellSwitchCase(Displayer displayer, Wizard Hero, String Choice) {
        while (!spell_choosed_state) {

            Choice = Main.scanner.nextLine();
            if (Choice.equals("back")) {
                //BACK TO MAIN SCREEN
                displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
                break;

            }
            try {

                int selectedSpellIndex = Integer.parseInt(Choice);

                if (selectedSpellIndex >= 0 & selectedSpellIndex <= Hero.getKnownSpells().size()) {

                    AbstractSpell spell_choosed = Hero.getKnownSpells().get(selectedSpellIndex - 1);
                    if (Testmain.musicEnabled) {
                        SoundEffectPlayer.play(spell_choosed.getSoundEffect());
                        SoundEffectPlayer.setVolume(0.2F);
                    }
                    displayer.targetDisplayUpdate("", enemyList, Hero, false);
                    while (!spell_choosed_state) {
                        Choice = Main.scanner.nextLine();
                        try {

                            int selectedTargetIndex = Integer.parseInt(Choice);


                            if (selectedTargetIndex < 0 | selectedTargetIndex > enemyList.size()) {
                                displayer.targetDisplayUpdate("", enemyList, Hero, true);
                            } else {
                                //get target
                                Enemy enemy = enemyList.get(selectedTargetIndex - 1);
                                spell_choosed_state = true;
                                //reduce all curse cooldowns
                                CurseCooldown(Hero);

                                attackResult = Hero.attack(enemy, spell_choosed);
                                if (enemy.isDead()) {
                                    attackResult += Hero.getRewardFrom(enemy);
                                    enemyList.remove(enemy);
                                }



                            }
                        } catch (NumberFormatException e) {
                            displayer.targetDisplayUpdate("", enemyList, Hero, true);
                        }
                    }


                } else {
                    displayer.spellDisplayUpdate(enemyList, Hero, true);
                }
            } catch (NumberFormatException e) {
                displayer.spellDisplayUpdate(enemyList, Hero, true);
            }

        }


    }
    public boolean level(Wizard Hero) throws InterruptedException {
        Displayer displayer = new Displayer(
                ActionCharacter.displayPlayerInfos(Hero, false) + "-".repeat(45) + "\n" + ActionCharacter.displayEnemyInfos(enemyList, false),
                currentState,
                "1. Attack\n2. Use potion\n3. Equipements\n4. Dodge");
        System.out.println(enemyList.get(0).getCurseList().keySet());
        displayer.display();
        whenAlive(Hero, displayer);
        Hero.setMaxYear(1);
        winOrLoose(Hero, displayer);
        return choiceForShop(Hero, displayer);

    }
    public void switchLevel(Wizard Hero, Displayer displayer) throws InterruptedException {
        do {
            String Choice = Main.scanner.nextLine();
            potion_choosed = false;


            switch (Choice) {


//==========================SPELL========================
                case "1":
                    //SPELL SCREEN
                    displayer.spellDisplayUpdate(enemyList, Hero, false);
                    spellSwitchCase(displayer,Hero,Choice);
                    break;

//==========================POTION========================
                case "2":
                    //POTION SCREEN
                    displayer.potionDisplayUpdate(enemyList, Hero, false);
                    potionSwitchCase(displayer, Hero);
                    break;

//==========================EQUIPEMENT========================
                case "3":

                    equipement_chosed = equipementSwitchCase(displayer, Hero);
                    break;

//==========================DODGE========================
                case "4":
                    dodge_selected = true;
                    oldAgility = Hero.getDodgingChancePercentage();
                    if (Testmain.musicEnabled) {
                        SoundEffectPlayer.play(MusicLibrary.dodgeLoutre);
                        SoundEffectPlayer.setVolume(0.1F);
                    }
                    attackResult = "You are not attacking this turn, you restore " + ConsoleColors.RED_BOLD_BRIGHT + "❤ 10" + ConsoleColors.RESET + " and " +
                            ConsoleColors.BLUE_BOLD_BRIGHT + "\uD83C\uDF22 25 " + ConsoleColors.RESET + "\nYou also gain" + ConsoleColors.AGYLITYCOLOR_BOLD + " 💨 35%" + ConsoleColors.RESET + " more temporary Agility";
                    Hero.healthRegen(10);
                    Hero.manaRegen(25);
                    Hero.AgilityIncrease(35);
                    CurseCooldown(Hero);
                    break;

                default:
                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, true, false);
                    break;
            }

        } while (!dodge_selected & !spell_choosed_state);
}
    public void whenAlive(Wizard Hero,Displayer displayer) throws InterruptedException {
        while (!enemyList.isEmpty() & !Hero.isDead()) {

        resetStates(); //RESET ALL STATES
        switchLevel(Hero, displayer); //SWITCH FOR THE 4 OPTIONS


        //RESULT SCREEN FOR THE TURN (ATTACK OR DODGE)
        currentState = attackResult + "\n\n";
        enemyAttack(Hero, displayer);

        if (dodge_selected) {
            Hero.setDodgingChancePercentage(oldAgility);
        }

    }}
    public void resetStates(){
        equipement_chosed = false;
        spell_choosed_state = false;
        dodge_selected = false;
        oldAgility = 0;
        attackResult = "";
    }
}