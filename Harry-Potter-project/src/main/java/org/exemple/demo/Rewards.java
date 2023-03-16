package org.exemple.demo;

import org.exemple.demo.SPELLS.AbstractSpell;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Rewards {
    private int gold;
    private ArrayList<Potion> potionArrayList;
    private int experienceGain;
    private ArrayList<AbstractSpell> newSpells;


}



