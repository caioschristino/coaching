package com.v2.coaching.di.component;

import android.app.Application;
import android.content.Context;


import com.v2.coaching.CoachingApplication;
import com.v2.coaching.data.DataManager;
import com.v2.coaching.data.DbHelper;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.data.Repo.SessionRepository;
import com.v2.coaching.data.SharedPrefsHelper;
import com.v2.coaching.di.ApplicationContext;
import com.v2.coaching.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by janisharali on 08/12/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(CoachingApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DbHelper getDbHelper();

    SessionRepository getSessionRepostory();

    FightRepository getKnockRepository();
}
