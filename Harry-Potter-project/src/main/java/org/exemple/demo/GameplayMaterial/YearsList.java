package org.exemple.demo.GameplayMaterial;

import org.exemple.demo.Characters.Wizard;
import org.exemple.demo.Spells.ForbiddenSpell;
import org.exemple.demo.Spells.Spell;
import org.jetbrains.annotations.NotNull;


public class YearsList {
    //=========================================================================YEARLIST===================================================================================

    static boolean year_1(@NotNull Wizard Hero) throws InterruptedException {

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
    static boolean year_2(Wizard hero) throws InterruptedException {
        //initYear

        Year year2 = Year.year2Constructor();
        hero.addSpell(Spell.Obscuro());
        return year2.level(hero);
    }


    static boolean year_3(Wizard hero) throws InterruptedException {
        Year year3 = Year.year3Constructor();
        hero.addSpell(Spell.expectoPatronum());
        return year3.level(hero);

    }

    static boolean year_4(Wizard hero) throws InterruptedException {
        Year year4 = Year.year4Constructor();
        hero.addSpell(Spell.Accio());
        return year4.level(hero);
    }

    static boolean year_5(Wizard hero) throws InterruptedException {
        Year year5 = Year.year5Constructor();
        hero.addSpell(Spell.cheating());
        return year5.level(hero);
    }

    static boolean year_6(Wizard hero) throws InterruptedException {
        hero.removeSpell(Spell.cheating());
        Year year6 = Year.year6Constructor();
        hero.addSpell(ForbiddenSpell.sectumsempra());
        return year6.level(hero);
    }

    static boolean year_7(Wizard hero) throws InterruptedException {
        Year year7 = Year.year7Constructor();
        hero.addSpell(Spell.expelliarmus());
        return year7.level(hero);
    }




}
//TODO FADE IN MUSIC
//TODO FADE OUT MUSIC


