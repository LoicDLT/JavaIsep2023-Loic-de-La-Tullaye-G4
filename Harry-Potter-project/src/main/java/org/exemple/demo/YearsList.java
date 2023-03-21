package org.exemple.demo;

import org.exemple.demo.Music.MusicLibrary;
import org.exemple.demo.Music.MusicPlayer;
import org.exemple.demo.Music.SoundEffectPlayer;
import org.exemple.demo.SPELLS.AbstractSpell;
import org.exemple.demo.SPELLS.Spell;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public interface YearsList {
    //=========================================================================YEARLIST===================================================================================

    static boolean Year_1(@NotNull Wizard Hero) throws InterruptedException {

        //initYear
        Year year1 = Year.year1Constructor();
        Hero.addSpell(Spell.Wingardium_Leviosa());
        return year1.level(Hero);

    }

//TODO EQUILIBRAGE
    //TODO SPELLS
    //TODO EQUIPEMENTS
    //TODO POTION
    //TODO GOlD REWARDS AND PRICES
//TODO SWORD AND TEETH
//
    static boolean Year_2(Wizard Hero) throws InterruptedException {
        //initYear

        Year year2 = Year.year2Constructor();
        Hero.addSpell(Spell.Obscuro());
        return year2.level(Hero);
    }


    static void Year_3(Wizard hero) {
        //TODO
    }

    static void Year_4(Wizard hero) {
        //TODO
    }

    static void Year_5(Wizard hero) {
        //TODO
    }

    static void Year_6(Wizard hero) {
        //TODO
    }

    static void Year_7(Wizard hero) {
        //TODO
    }



}
//TODO FADE IN MUSIC
//TODO FADE OUT MUSIC


