package com.v2.coaching.di.component;

import com.v2.coaching.Ui.Fragment.BaseFragment;
import com.v2.coaching.di.PerActivity;
import com.v2.coaching.di.module.FragmentModule;

import dagger.Component;

/**
 * Created by janisharali on 08/12/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment mBaseFragment);
}
