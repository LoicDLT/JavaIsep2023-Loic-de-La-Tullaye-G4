package org.exemple.demo.GameplayMaterial;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Characters.Wizard;
import org.exemple.demo.Tools.ConsoleColors;
import org.exemple.demo.Executables.Main;
import org.exemple.demo.Usables.Equipement;
import org.exemple.demo.Usables.Potion;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
public class Shop {
    private LinkedHashMap<Potion, Integer> avaliablePotionMap;
    private LinkedHashMap<Equipement, Integer> avaliableEquipementMap;


    public String displayShop() {
        int index = 1;
        String shopDisplay = "Welcome to Hogwarts' shop !\n";
        if (avaliableEquipementMap.size() > 0) {
            shopDisplay += "Here is the list of the potions available :\n";
            for (Potion potion : avaliablePotionMap.keySet()) {
                shopDisplay += "(" + index + ") " + potion.getColor() + potion.getName() + ConsoleColors.RESET + " : " + ConsoleColors.YELLOW_BOLD + avaliablePotionMap.get(potion) + " \uD83D\uDCB0" + ConsoleColors.RESET + "\n";
                index += 1;
            }
        }

        if (avaliableEquipementMap.size() > 0) {

            shopDisplay += "\nHere is the list of the equipements available :\n";
            for (Equipement equipement : avaliableEquipementMap.keySet()) {
                shopDisplay += "(" + index + ") " + equipement.getColor() + equipement.getName() + ConsoleColors.RESET + " : " + ConsoleColors.YELLOW_BOLD + avaliableEquipementMap.get(equipement) + "\uD83D\uDCB0" + ConsoleColors.RESET + "\n";
            }
        }
        return shopDisplay;
    }

    public static @NotNull Shop year1Shop() {
        LinkedHashMap<Potion, Integer> potionMap = new LinkedHashMap<>();
        LinkedHashMap<Equipement, Integer> equipementMap = new LinkedHashMap<>();
        potionMap.put(Potion.health_Potion(), 30);
        potionMap.put(Potion.mana_Potion(), 30);
        potionMap.put(Potion.luck_Potion(), 50);
        potionMap.put(Potion.strength_Potion(), 50);
        equipementMap.put(Equipement.sword(), 150);

        Shop shop = new Shop(potionMap, equipementMap);


        return shop;


    }

    public void ShopTime(@NotNull Wizard Hero) {
        Displayer displayershop = new Displayer(
                displayShop(),
                "type the number of the item you want to buy  \ntype \"next\" to go to the next year");
        displayershop.shopDisplayUpdate("", displayShop(), Hero, false);
        String Choice = "";
        while (!Choice.equals("next")) {
            Choice = Main.scanner.nextLine();
            try {
                int choice = Integer.parseInt(Choice);

                if (choice > 0 & choice <= (avaliablePotionMap.size() + avaliableEquipementMap.size())) {

                    if (choice <= avaliablePotionMap.size()) {

                        int price = avaliablePotionMap.values().toArray(new Integer[0])[choice - 1];
                        Potion choosedPotion = (Potion) avaliablePotionMap.keySet().toArray()[choice - 1];

                        potionChoosed(Hero, displayershop, choice, choosedPotion, price);
                    } else {

                        int price = avaliableEquipementMap.values().toArray(new Integer[0])[choice - 1 - avaliablePotionMap.size()];
                        Equipement choosedEquipement = (Equipement) avaliableEquipementMap.keySet().toArray()[choice - 1 - avaliablePotionMap.size()];

                        equipementChoosed(Hero, displayershop, choice, choosedEquipement, price);
                    }
                } else {
                    String currentState = "\nPlease enter a valid number";
                    displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);
                }
            } catch (NumberFormatException e) {
                String currentState = "\nPlease enter a number";
                displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);
            }
        }
    }

    public void equipementChoosed(Wizard Hero, Displayer displayershop, int choice, Equipement choosedEquipement, int price) {

        //TESTING IF THE PLAYER ALREADY HAVE THE EQUIPEMENT
        if (Hero.getEquipements().contains(choosedEquipement)) {
            String currentState = "\nYou already have this item";
            displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);

        } else if (Hero.getGold() >= price) {
            Hero.addEquipement(choosedEquipement);
            Hero.changeGold(-price);
            String currentState = "\nYou bought " + choosedEquipement.getColor() + choosedEquipement.getName() + ConsoleColors.RESET + " for " + ConsoleColors.YELLOW_BOLD + price + ConsoleColors.RESET + " \uD83D\uDCB0";
            displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);
        } else {
            String currentState = "\nYou don't have enough " + ConsoleColors.YELLOW_BOLD + "gold" + ConsoleColors.RESET + " to buy this item";
            displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);
        }
    }

    public void potionChoosed(Wizard Hero, Displayer displayershop, int choice, Potion choosedPotion, int price) {
        if (Hero.getGold() >= price) {
            //ADDING POTION TO INVENTORY
            Hero.addPotions(choosedPotion);
            //REMOVING GOLD
            Hero.changeGold(-price);

            String currentState = "\nYou bought " + choosedPotion.getColor() + choosedPotion
                    .getName() + ConsoleColors.RESET + " for " + ConsoleColors.YELLOW_BOLD + price + ConsoleColors.RESET + " \uD83D\uDCB0";
            displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);
        } else {
            String currentState = "\nYou don't have enough " + ConsoleColors.YELLOW_BOLD + "gold" + ConsoleColors.RESET + " to buy this potion";
            displayershop.shopDisplayUpdate(currentState, displayShop(), Hero, false);
        }
    }


}

