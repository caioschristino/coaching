package com.v2.coaching.di.module;
import android.content.Context;


import com.v2.coaching.Ui.Activity.BaseMain;
import com.v2.coaching.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by janisharali on 08/12/16.
 */

@Module
public class ActivityModule {
    private BaseMain mBaseMain;

    public ActivityModule(BaseMain baseMain) {
        mBaseMain = baseMain;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mBaseMain;
    }

    @Provides
    BaseMain provideActivity() {
        return mBaseMain;
    }
}
