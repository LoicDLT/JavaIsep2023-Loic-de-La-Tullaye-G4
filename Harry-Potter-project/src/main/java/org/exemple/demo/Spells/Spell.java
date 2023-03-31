package org.exemple.demo.Spells;

import org.exemple.demo.Music.MusicLibrary;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class Spell extends AbstractSpell{

    public static Spell Wingardium_Leviosa(){
        Spell Wingardium_Leviosa =
                Spell.builder()
                        .id(1)
                        .name("Wingardium Leviosa")
                        .manaCost(50)
                        .chanceOfSuccess(90)
                        .script("You make objects fly above your enemy before releasing them all on him")
                        .damage(500)
                        .didNotWork("Unfortunatly it missed")
                        .soundEffect(MusicLibrary.wingardiumLeviosa)
                        .build();
        return Wingardium_Leviosa;
    }
    public static Spell Obscuro(){
        Spell Obscuro =
                Spell.builder()
                        .id(2)
                        .name("Obscuro")
                        .manaCost(150)
                        .chanceOfSuccess(100)
                        .script("You conjure a blindfold over the eyes of your enemy")
                        .damage(100)
                        .didNotWork("Unfortunatly the spell did not work")
                        .curse(Curse.CurseOfBlindness)
                        .soundEffect(MusicLibrary.wingardiumLeviosa)
                        .build();
        return Obscuro;
    }

    public static Spell expectoPatronum(){
        Spell expectoPatronum =
                Spell.builder()
                        .id(3)
                        .name("Expecto Patronum")
                        .manaCost(200)
                        .chanceOfSuccess(100)
                        .script("You conjure a Patronus to protect you")
                        .damage(50)
                        .didNotWork("Unfortunatly the spell did not work")
                        .curse(null)
                        .soundEffect(null)
                        .build();
        return expectoPatronum;
    }
    public static Spell Accio(){
        Spell Accio =
                Spell.builder()
                        .id(4)
                        .name("Accio")
                        .manaCost(200)
                        .chanceOfSuccess(100)
                        .script("")
                        .damage(50)
                        .didNotWork("Unfortunatly the spell did not work")
                        .curse(null)
                        .soundEffect(null)
                        .build();
        return Accio;
    }

}
