package com.v2.coaching.di.module;

import android.content.Context;

import com.v2.coaching.Ui.Activity.BaseMain;
import com.v2.coaching.Ui.Fragment.BaseFragment;
import com.v2.coaching.di.ActivityContext;
import com.v2.coaching.di.FragmentContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaioSChristino on 16/09/17.
 */
@Module
public class FragmentModule {
    private BaseFragment mBaseFragment;

    public FragmentModule(BaseFragment baseFragment) {
        mBaseFragment = baseFragment;
    }

    @Provides
    @FragmentContext
    Context provideContext() {
        return mBaseFragment.getActivity();
    }

    @Provides
    BaseFragment provideFragment() {
        return mBaseFragment;
    }
}