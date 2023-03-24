package org.exemple.demo.Spells;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Curse {
    CurseOfBlindness("The Curse Of Blindness","Blinded", 4, 30,1,0.6f );

    private String curseName;
    private String adjective;
    private int curseDuration;
    private int curseDamage;
    private float curseDamageMultiplier;

    private float curseDodgingMultiplier;

}
