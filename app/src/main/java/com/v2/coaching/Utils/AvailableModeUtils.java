package com.v2.coaching.Utils;

import com.v2.coaching.Model.AvailableMode;
import com.v2.coaching.data.model.Knock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class AvailableModeUtils {
    public static final int JiuJitsuMode = 1;
    public static final String JiuJitsu = "JiuJitsu";

    public static HashMap<String, Integer> getKnocks(AvailableMode mode) {
        HashMap<String, Integer> knocks = new HashMap<>();

        switch (mode){
            case JIUSITSU:
                knocks = getJiuJitsuKnocks();
                break;
        }

        return knocks;
    }

    public static HashMap<String, Integer> getJiuJitsuKnocks() {
        HashMap<String, Integer> knocks = new HashMap<>();
        knocks.put("Queda", 2);
        knocks.put("Passagem de guarda", 2);
        knocks.put("Pegada costas", 2);
        knocks.put("Joelho na barriga", 2);
        knocks.put("Montada", 2);
        knocks.put("Raspagem", 2);
        knocks.put("Finalização", 2);
        return knocks;
    }
}
