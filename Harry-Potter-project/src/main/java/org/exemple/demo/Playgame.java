package org.exemple.demo;

public class Playgame {


    public static void playgame(Wizard Hero) throws InterruptedException {

            boolean shopOrNot = YearsList.Year_1(Hero);
            if (Hero.isDead()) {
                return;
            }
            if (shopOrNot) {
                Shop.year1Shop().ShopTime(Hero);
                YearsList.Year_2(Hero);
            }
            else{
                YearsList.Year_2(Hero);
            }


        }

    }

