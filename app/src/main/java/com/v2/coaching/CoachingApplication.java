package com.v2.coaching;

import android.app.Application;

import com.v2.coaching.data.DataManager;
import com.v2.coaching.di.component.ApplicationComponent;
import com.v2.coaching.di.component.DaggerApplicationComponent;
import com.v2.coaching.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by CaioSChristino on 14/09/17.
 */

public class CoachingApplication extends Application {
    private static CoachingApplication instance;
    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public CoachingApplication() {
        instance = this;
    }

    public static synchronized CoachingApplication getApp() {
        return instance;
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
}
