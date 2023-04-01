package org.exemple.demo.GameplayMaterial;

import org.exemple.demo.Characters.Wizard;

public class Playgame {


    public static void playgame(Wizard Hero) throws InterruptedException {

        boolean shopOrNot = YearsList.year_1(Hero);
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
        }
        if (Hero.isDead()) {
            return;
        }
        if (shopOrNot) {
            Shop.year4Shop().ShopTime(Hero);
            shopOrNot = YearsList.year_5(Hero);
        } else {

            shopOrNot = YearsList.year_5(Hero);
        }
        if (Hero.isDead()) {
            return;
        }

        if (shopOrNot) {
            Shop.year5Shop().ShopTime(Hero);
            shopOrNot = YearsList.year_6(Hero);
        } else {
            shopOrNot = YearsList.year_6(Hero);
        }
        if (Hero.isDead()) {
            return;
        }
        if (shopOrNot) {
            Shop.year6Shop().ShopTime(Hero);
            YearsList.year_7(Hero);
        } else {
            YearsList.year_7(Hero);
        }

    }}

