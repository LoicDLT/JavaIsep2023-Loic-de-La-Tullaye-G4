package org.exemple.demo.Spells;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Curse {
    CurseOfBlindness("The Curse Of Blindness","Blinded", 4, 0,1,0.6f ),
    CurseOfFear("The Curse Of Fear","Terrified", 3, 50,0,0.8f );
    private String curseName;
    private String adjective;
    private int curseDuration;
    private int curseDamage;
    private float curseDamageMultiplier;
    private float curseDodgingMultiplier;

}
