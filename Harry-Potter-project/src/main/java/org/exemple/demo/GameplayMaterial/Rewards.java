package org.exemple.demo.GameplayMaterial;

import org.exemple.demo.Spells.AbstractSpell;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.exemple.demo.Usables.Potion;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Rewards {
    private int gold;
    private ArrayList<Potion> potionArrayList;
    private int experienceGain;
    private ArrayList<AbstractSpell> newSpells;


}



