package com.v2.coaching.data.Repo;

import com.j256.ormlite.dao.Dao;
import com.v2.coaching.data.DataManager;
import com.v2.coaching.data.model.KnockSessionFight;
import com.v2.coaching.data.model.SessionFight;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by CaioSChristino on 17/09/17.
 */

@Singleton
public class SessionRepository {
    DataManager mDataManager;
    Dao mDaoKnockHistory;
    Dao mDaoHistory;
    Repo mRepo;


    @Inject
    public SessionRepository(DataManager dataManager) {
        mDataManager = dataManager;
        mRepo = new Repo();
        try {
            mDaoHistory = mDataManager.getmDbHelper().getDao(SessionFight.class);
            mDaoKnockHistory = mDataManager.getmDbHelper().getDao(KnockSessionFight.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SessionFight> getAll() {
        try {
            return mDaoHistory.queryForAll();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveHistory(SessionFight session) {
        if (createSession(session).isCreated()) {
            session.getAllBlow().forEach(knock -> {
                mRepo.createOrUpdate(new KnockSessionFight(knock, session));
            });
            updateSession(session);
        }
    }

    private void updateSession(SessionFight session) {
        try {
            mDaoHistory.createOrUpdate(session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao.CreateOrUpdateStatus createSession(SessionFight session) {
        try {
            return mDaoHistory.createOrUpdate(session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Dao.CreateOrUpdateStatus(false, false, 0);
    }

    class Repo implements Repository<KnockSessionFight> {
        @Override
        public List<KnockSessionFight> getAll() {
            try {
                return mDaoKnockHistory.queryForAll();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        @Override
        public KnockSessionFight findById(Object aId) {
            try {
                return (KnockSessionFight) mDaoKnockHistory.queryForId(aId);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public <R> Dao.CreateOrUpdateStatus createOrUpdate(KnockSessionFight item) {
            try {
                return mDaoKnockHistory.createOrUpdate(item);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }

            return new Dao.CreateOrUpdateStatus(false, false, 0);
        }

        @Override
        public void remove(Object aId) {
            try {
                mDaoKnockHistory.deleteById(aId);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public List<KnockSessionFight> query(Map<String, Object> aMap) {
            try {
                return mDaoKnockHistory.queryForFieldValues(aMap);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}