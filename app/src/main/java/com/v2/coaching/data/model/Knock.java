package com.v2.coaching.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by CaioSChristino on 03/09/17.
 */

@DatabaseTable
public class Knock {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField
    private int point;
    @DatabaseField
    private String name;
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    private Fight fight;
    @ForeignCollectionField
    private Collection<KnockSessionFight> mKnockForeignCollection = new ArrayList<>();

    public Knock() {
    }

    public Knock(String name, int point, Fight fight) {
        this.point = point;
        this.fight = fight;
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
