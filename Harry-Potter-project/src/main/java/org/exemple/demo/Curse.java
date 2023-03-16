package org.exemple.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Curse {
    CurseOfBlindness("The Curse Of Blindness","Blinded", 3, 0,1,0.6f );

    private String curseName;
    private String adjective;
    private int curseDuration;
    private int curseDamage;
    private float curseDamageMultiplier;

    private float curseDodgingMultiplier;

}
