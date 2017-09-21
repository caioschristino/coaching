package com.v2.coaching.Model;

import com.v2.coaching.Utils.AvailableModeUtils;
import com.v2.coaching.data.model.Knock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CaioSChristino on 03/09/17.
 */
public enum AvailableMode {
    JIUSITSU(AvailableModeUtils.JiuJitsu, AvailableModeUtils.JiuJitsuMode, AvailableModeUtils.getJiuJitsuKnocks());
    final Integer mValue;
    HashMap<String, Integer> mKnocks;
    String mName;

    private AvailableMode(String name, int value, HashMap<String, Integer> knocks) {
        mValue = value;
        mKnocks = knocks;
        mName = name;
    }

    private static final Map<Integer, AvailableMode> lookup = new HashMap<>();
    static {
        for (AvailableMode d : AvailableMode.values()) {
            lookup.put(d.getId(), d);
        }
    }

    public Integer getId() {
        return mValue;
    }

    public HashMap<String, Integer> getKnocks() {
        return mKnocks;
    }

    public String getName() {
        return mName;
    }

    public static AvailableMode get(Integer id) {
        return lookup.get(id);
    }
}
