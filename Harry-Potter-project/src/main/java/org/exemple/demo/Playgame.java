package org.exemple.demo;

public class Playgame {


    public static void playgame(Wizard Hero) throws InterruptedException {

            boolean shopOrNot = YearsList.Year_1(Hero);
            if (Hero.isDead()) {
                return;
            }
            if (shopOrNot) {
                YearsList.ShopTime(Hero, Shop.year1Shop());
                YearsList.Year_2(Hero);
            }
            else{
                YearsList.Year_2(Hero);
            }


        }

    }

