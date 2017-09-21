package com.v2.coaching.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by CaioSChristino on 17/09/17.
 */

@DatabaseTable
public class KnockSessionFight {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SessionFight mSessionFight;
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    private Knock mKnock;

    public KnockSessionFight(){}

    public KnockSessionFight(Knock knock, SessionFight sessionFight){
        mSessionFight = sessionFight;
        mKnock = knock;
    }

    public Knock getmKnock() {
        return mKnock;
    }

    public void setmKnock(Knock mKnock) {
        this.mKnock = mKnock;
    }

    public SessionFight getmSessionFight() {
        return mSessionFight;
    }
}
