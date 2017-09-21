package com.v2.coaching.data.Repo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.v2.coaching.Model.AvailableMode;
import com.v2.coaching.Utils.AvailableModeUtils;
import com.v2.coaching.data.DataManager;
import com.v2.coaching.data.model.Fight;
import com.v2.coaching.data.model.Knock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by CaioSChristino on 21/09/17.
 */

@Singleton
public class FightRepository {
    AvailableMode mode;
    DataManager mDataManager;
    Dao mDaoKnock;
    Dao mDaoFight;
    Repo mRepo;

    @Inject
    public FightRepository(DataManager dataManager) {
        mDataManager = dataManager;
        mRepo = new Repo();

        try {
            mDaoFight = mDataManager.getmDbHelper().getDao(Fight.class);
            mDaoKnock = mDataManager.getmDbHelper().getDao(Knock.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDefaultAvailableKnock() {
        createJiuMode();
    }

    private void createJiuMode() {
        AvailableMode jiu = AvailableMode.get(AvailableModeUtils.JiuJitsuMode);
        if (!existsFight(jiu.getId())) {

            Fight newType = new Fight(jiu.getName());
            Dao.CreateOrUpdateStatus status = createFight(newType);
            jiu.getKnocks().entrySet().forEach(knock -> {
                mRepo.createOrUpdate(new Knock(knock.getKey(), knock.getValue(), newType));
            });

            newType.setKnocks(mRepo.getAll());
            updateFight(newType);
            newType.getKnocks();
        }
    }

    public void setMode(AvailableMode mode) {
        this.mode = mode;
    }

    private void updateFight(Fight newType) {
        try {
            mDaoFight.createOrUpdate(newType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao.CreateOrUpdateStatus createFight(Fight newType) {
        try {
            mDaoFight.createOrUpdate(newType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Dao.CreateOrUpdateStatus(false, false, 0);
    }

    private boolean existsFight(Object aId) {
        boolean result = false;
        try {
            return mDaoFight.queryForId(aId) == null ? false
                    : true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Fight getFight(Object aId) {
        try {
            return (Fight) mDaoFight.queryForId(aId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Knock> getKnocks() {
        Fight fight = getFight(mode.getId());
        if (fight != null) {
            return fight.getKnocks();
        }
        return null;
    }

    class Repo implements Repository<Knock> {
        @Override
        public List<Knock> getAll() {
            try {
                return mDaoKnock.queryForAll();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        @Override
        public Knock findById(Object aId) {
            return null;
        }

        @Override
        public <R> Dao.CreateOrUpdateStatus createOrUpdate(Knock item) {
            try {
                return mDaoKnock.createOrUpdate(item);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }

            return new Dao.CreateOrUpdateStatus(false, false, 0);
        }

        @Override
        public void remove(Object aId) {

        }

        @Override
        public List<Knock> query(Map<String, Object> aMap) {
            return null;
        }

        public ForeignCollection<Knock> findKnocksById(Object aId) {
            try {
                return (ForeignCollection<Knock>) mDaoKnock.queryForId(aId);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}