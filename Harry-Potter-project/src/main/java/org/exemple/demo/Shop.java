package org.exemple.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
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
                shopDisplay += "(" + index + ") " + equipement.getName() + " : " + ConsoleColors.YELLOW_BOLD + avaliableEquipementMap.get(equipement) + "\uD83D\uDCB0" + ConsoleColors.RESET + "\n";
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
}

