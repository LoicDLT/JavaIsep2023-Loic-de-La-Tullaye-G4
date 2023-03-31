package org.exemple.demo.GameplayMaterial;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.exemple.demo.Characters.Enemy;
import org.exemple.demo.Characters.Wizard;
import org.exemple.demo.Executables.Main;
import org.exemple.demo.Executables.Testmain;
import org.exemple.demo.Music.MusicLibrary;
import org.exemple.demo.Music.MusicPlayer;
import org.exemple.demo.Music.SoundEffectPlayer;
import org.exemple.demo.Spells.AbstractSpell;
import org.exemple.demo.Spells.Curse;
import org.exemple.demo.Spells.EnemySpell;
import org.exemple.demo.Tools.ConsoleColors;
import org.exemple.demo.Usables.Equipement;
import org.exemple.demo.Usables.Potion;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor()
public class Year {
    @NonNull
    private int yearNumber;
    @NonNull
    private String currentState;
    @NonNull
    private ArrayList<Enemy> enemyList;

    private boolean potion_choosed = false;

    private boolean equipement_choosed_state = false;

    private boolean spell_choosed_state = false;

    private boolean dodge_selected = false;

    private float oldAgility = 0;

    private String attackResult = "";

    private int turn = 0;

    private int nbaccio = 0;

    private final int mainEventTurn;

    private boolean exitYear = false;

    public static Year year1Constructor() {

        ArrayList<EnemySpell> listTrollAttacks = new ArrayList<>();
        listTrollAttacks.add(EnemySpell.Troll_Hit());
        listTrollAttacks.add(EnemySpell.Troll_Throw());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Troll(listTrollAttacks));
        enemyList.add(Enemy.Trollette(listTrollAttacks));

        Year year1 = new Year(1, "You enter the bathroom and you see, standing in front of you, a giant Troll looking at you with menacing eyes", enemyList, -1);

        return year1;
    }

    public static Year year2Constructor() {

        ArrayList<EnemySpell> listBasilikAttacks = new ArrayList<>();
        listBasilikAttacks.add(EnemySpell.Basiliks_Tail());
        listBasilikAttacks.add(EnemySpell.Basiliks_bite());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Basilisk(listBasilikAttacks));


        Year year2 = new Year(2, "You find yourself in the basilisk lair", enemyList, 5);

        return year2;
    }

    public static Year year3Constructor() {

        ArrayList<EnemySpell> listDementorAttacks = new ArrayList<>();
        listDementorAttacks.add(EnemySpell.Dementor_Psychological_attack());

        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Dementor1(listDementorAttacks));
        enemyList.add(Enemy.Dementor2(listDementorAttacks));
        enemyList.add(Enemy.Dementor3(listDementorAttacks));

        Year year3 = new Year(3, "you are near the lake and dementors are comming at you", enemyList, -1);

        return year3;
    }

    public static Year year4Constructor() {

        ArrayList<EnemySpell> listVoldemortAttacks = new ArrayList<>();
        ArrayList<EnemySpell> listPettigrowAttacks = new ArrayList<>();
        listVoldemortAttacks.add(EnemySpell.Voldemort_Crucio());
        listPettigrowAttacks.add(EnemySpell.Pettigrow_Confringo());
        //building enemies
        ArrayList<Enemy> enemyList = new ArrayList<>();
        enemyList.add(Enemy.Voldemort(listVoldemortAttacks));
        enemyList.add(Enemy.Pettigrow(listPettigrowAttacks));


        Year year4 = new Year(4, "You are in a cemetery facing Voldemort and Peter Pettigrew. \n" +
                "Your only chance of escape is to get closer to the Portkey to attract it to you", enemyList, -1);

        return year4;
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
            Thread.sleep(3000);
            currentState = ("You died <3");
            displayer.endDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false);
            if (Testmain.musicEnabled) {
                MusicPlayer.stopMusic();
                MusicPlayer.play(MusicLibrary.deathMusicAstronomia);
                MusicPlayer.setVolume(Testmain.musicVolume);

            }

        } else {
            Thread.sleep(3000);
            currentState = ("You passed year " + yearNumber + " with honors !\n"
                    + ConsoleColors.BLUE_BOLD + "-------REWARDS-------" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.YELLOW + "100 \uD83D\uDCB0" + ConsoleColors.RESET + "\n"
                    + ConsoleColors.ORANGE + "200 " + ConsoleColors.RESET + "Exp points");


            displayer.endDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false);
        }
    }

    public void enemyAttack(Wizard Hero, Displayer displayer) {
        for (Enemy enemy : enemyList) {
            if (!enemy.isDead() & !Hero.isDead() & !(enemy.getCurseList().containsKey(Curse.CurseOfFear))) {
                currentState += enemy.attack(Hero) + "\n";
            }
        }

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

    public void equipementSwitchCase(Displayer displayer, @NotNull Wizard Hero) {

        if (Hero.getEquipements().isEmpty()) {
            currentState = ("\n\nYou don't have any equipement ");
            displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);

        } else if (equipement_choosed_state) {
            currentState = ("\n\nYou have already used equipement this turn ");
            displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);

        } else {
            displayer.equipementDisplayUpdate(enemyList, Hero, false);


            while (!equipement_choosed_state) {

                String Choice = Main.scanner.nextLine();

                if (Choice.equals("back")) {
                    //MAIN SCREEN
                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);

                    equipement_choosed_state = false;
                }

                try {
                    int selected = Integer.parseInt(Choice);
                    if (selected > 0 & selected <= Hero.getEquipements().size()) {
                        Equipement equipement_choosed = Hero.getEquipements().get(selected - 1);
                        displayer.targetDisplayUpdate("", enemyList, Hero, false);
                        while (!equipement_choosed_state) {
                            Choice = Main.scanner.nextLine();
                            try {

                                int selectedTargetIndex = Integer.parseInt(Choice);


                                if (selectedTargetIndex < 0 | selectedTargetIndex > enemyList.size()) {
                                    displayer.targetDisplayUpdate("", enemyList, Hero, true);
                                } else {
                                    //get target
                                    Enemy enemy = enemyList.get(selectedTargetIndex - 1);
                                    //currentState = "\n\n\n" + equipement_choosed.getName() + " used successfully!";
                                    equipement_choosed_state = true;
                                    currentState = Hero.useEquipement(equipement_choosed, enemy);
                                    if (enemy.isDead()) {
                                        currentState += "\n" + Hero.getRewardFrom(enemy);
                                        enemyList.remove(enemy);
                                    }
                                    displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);

                                }
                            } catch (NumberFormatException e) {
                                displayer.targetDisplayUpdate("", enemyList, Hero, true);
                            }


                        }
                    } else {
                        displayer.equipementDisplayUpdate(enemyList, Hero, true);
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

                if (selectedSpellIndex > 0 & selectedSpellIndex <= Hero.getKnownSpells().size()) {

                    AbstractSpell spell_choosed = Hero.getKnownSpells().get(selectedSpellIndex - 1);
                    if (spell_choosed.getManaCost() > Hero.getCurrentManaPoints()) {
                        displayer.spellDisplayUpdate(enemyList, Hero, false, true);
                    } else {
                        if (spell_choosed.getId() == 4 & yearNumber == 4) {
                            if(accioCase(Hero, displayer));break;
                        }


                        if (Testmain.musicEnabled) {
                            if (spell_choosed.getSoundEffect() != null) {
                                SoundEffectPlayer.play(spell_choosed.getSoundEffect());
                                SoundEffectPlayer.setVolume(0.2F);
                            }
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
                    }

                } else {
                    displayer.spellDisplayUpdate(enemyList, Hero, true, true);
                }
            } catch (NumberFormatException e) {
                displayer.spellDisplayUpdate(enemyList, Hero, true, true);
            }

        }

    }

    public boolean level(Wizard Hero) throws InterruptedException {
        Displayer displayer = new Displayer(
                currentState,
                "1. Attack\n2. Use potion\n3. Equipements\n4. Dodge");
        displayer.mainDisplayUpdate(currentState, enemyList, Hero, false, false);
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
                    displayer.spellDisplayUpdate(enemyList, Hero, false, false);
                    spellSwitchCase(displayer, Hero, Choice);
                    break;

//==========================POTION========================
                case "2":
                    //POTION SCREEN
                    displayer.potionDisplayUpdate(enemyList, Hero, false);
                    potionSwitchCase(displayer, Hero);
                    break;

//==========================EQUIPEMENT========================
                case "3":

                    equipementSwitchCase(displayer, Hero);
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

    public void whenAlive(Wizard Hero, Displayer displayer) throws InterruptedException {
        while (!enemyList.isEmpty() & !Hero.isDead()) {

            resetStates(); //RESET ALL STATES
            switchLevel(Hero, displayer); //SWITCH FOR THE 4 OPTIONS


            //RESULT SCREEN FOR THE TURN (ATTACK OR DODGE)
            currentState = attackResult + "\n\n";

            if (exitYear) {
                displayer.mainDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false, true);
                break;
            }

            enemyAttack(Hero, displayer);
            if (turn == mainEventTurn) {
                mainEvent(Hero, displayer);
            }

            displayer.mainDisplayUpdate(currentState.stripLeading(), enemyList, Hero, false, true);

            if (dodge_selected) {
                Hero.setDodgingChancePercentage(oldAgility);
            }
            turn++;
        }
    }

    public void resetStates() {
        equipement_choosed_state = false;
        spell_choosed_state = false;
        dodge_selected = false;
        oldAgility = 0;
        attackResult = "";
    }

    public void mainEvent(Wizard Hero, Displayer displayer) {
        switch (yearNumber) {
            case 2:
                displayer.eventDisplayUpdate("while fighting, you knocked off one of the basilisk's fangs. you get " + ConsoleColors.GREEN + "basilisk fang" + ConsoleColors.RESET + "\n" + Equipement.basiliskFang().getDescription(), "press enter to continue", Hero);
                Hero.addEquipement(Equipement.basiliskFang());
                Main.scanner.nextLine();

        }
    }
    public boolean accioCase(Wizard Hero, Displayer displayer) {

            nbaccio += 1;
            attackResult = "\n\nYou cast accio ! moving the Portkey closer to you";
            spell_choosed_state = true;
            if (nbaccio == 6) {
                attackResult += "\n You have moved the Portkey to your location, you use it to escape";
                spell_choosed_state = true;
                exitYear = true;
                return true;
            }
            return false;
        }
}