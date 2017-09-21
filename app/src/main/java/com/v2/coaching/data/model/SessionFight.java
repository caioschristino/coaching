package com.v2.coaching.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CaioSChristino on 07/09/17.
 */
public class SessionFight {
    private List<Knock> mLastBlow = new ArrayList<>();

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int player;
    @DatabaseField
    private int points;

    @ForeignCollectionField
    private Collection<KnockSessionFight> mKnockForeignCollection;

    public SessionFight() {
    }

    public SessionFight(String name, int player) {
        this.name = name;
        this.player = player;
    }

    public Knock getLastBlow() {
        return mLastBlow.get(0);
    }

    public List<Knock> getAllBlow() {
        return mLastBlow;
    }

    public String getName() {
        return name;
    }

    public void updateLastBlow(Knock knock) {
        this.mLastBlow.add(knock);
    }

    public void updatePoints(int point) {
        this.points += point;
    }

    public int getPoints() {
        return points;
    }

    public int getPlayer() {
        return player;
    }

    public void clearSession() {
        name = null;
        mLastBlow.clear();
        points = 0;
    }

    public void restarSession() {
        mLastBlow.clear();
        points = 0;
    }
}
