package org.exemple.demo.GameplayMaterial;

import org.exemple.demo.Characters.Wizard;

public class Playgame {


    public static void playgame(Wizard Hero) throws InterruptedException {

        /*boolean shopOrNot = YearsList.year_1(Hero);
        if (Hero.isDead()) {
            return;
        }
        if (shopOrNot) {
            Shop.year1Shop().ShopTime(Hero);
            shopOrNot = YearsList.year_2(Hero);
        } else {
            shopOrNot = YearsList.year_2(Hero);
        }
        if (Hero.isDead()) {
            return;
        }
        if (shopOrNot) {
            Shop.year2Shop().ShopTime(Hero);
            shopOrNot = YearsList.year_3(Hero);
        } else {
            shopOrNot = YearsList.year_3(Hero);
        }
        if (Hero.isDead()) {
            return;
        }
        if (shopOrNot) {
            Shop.year3Shop().ShopTime(Hero);
            shopOrNot = YearsList.year_4(Hero);
        } else {
            shopOrNot = YearsList.year_4(Hero);
        }*/
        YearsList.year_4(Hero);

    }}

