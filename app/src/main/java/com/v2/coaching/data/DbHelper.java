package com.v2.coaching.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.v2.coaching.data.model.Fight;
import com.v2.coaching.data.model.Knock;
import com.v2.coaching.data.model.KnockSessionFight;
import com.v2.coaching.data.model.SessionFight;
import com.v2.coaching.di.ApplicationContext;
import com.v2.coaching.di.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by CaioSChristino on 15/09/17.
 */
@Singleton
public class DbHelper extends OrmLiteSqliteOpenHelper {
    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, Fight.class);
            TableUtils.createTable(cs, Knock.class);
            TableUtils.createTable(cs, SessionFight.class);
            TableUtils.createTable(cs, KnockSessionFight.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {

    }
}