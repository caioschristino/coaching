package com.v2.coaching.di.component;

import com.v2.coaching.Ui.Activity.BaseMain;
import com.v2.coaching.Ui.Activity.MainActivity;
import com.v2.coaching.di.PerActivity;
import com.v2.coaching.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by janisharali on 08/12/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseMain mBaseMain);
}
