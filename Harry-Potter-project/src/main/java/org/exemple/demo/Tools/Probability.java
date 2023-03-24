package org.exemple.demo.Tools;

import net.andreinc.mockneat.MockNeat;

public class Probability {
    private static MockNeat m = MockNeat.threadLocal();

    public static boolean YesOrNo(float probability){
        return m.bools().probability(probability).val();
    }
}
