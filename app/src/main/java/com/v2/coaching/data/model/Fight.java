package com.v2.coaching.data.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CaioSChristino on 21/09/17.
 */

@DatabaseTable
public class Fight {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    @ForeignCollectionField
    private Collection<Knock> mKnockForeignCollection;

    public Fight(){}

    public Fight(String name){
        this.name = name;
    }

    public void setKnocks(List<Knock> knocks) {
        this.mKnockForeignCollection = knocks;
    }

    public List<Knock> getKnocks() {
        ArrayList<Knock> itemList = new ArrayList<>();
        for (Knock item : mKnockForeignCollection) {
            itemList.add(item);
        }
        return itemList;
    }

    public int getId() {
        return id;
    }
}
