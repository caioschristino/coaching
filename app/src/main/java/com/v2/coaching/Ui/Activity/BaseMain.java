package com.v2.coaching.Ui.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.v2.coaching.CoachingApplication;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.di.component.ActivityComponent;
import com.v2.coaching.di.component.DaggerActivityComponent;
import com.v2.coaching.di.module.ActivityModule;

import java.util.Stack;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public abstract class BaseMain extends FragmentActivity {
    private FragmentManager fragmentManager;
    private Stack<Fragment> fragmentStack;
    private ActivityComponent mActivityComponent;
    abstract int setContainer();

    abstract int setContent();

    abstract void initViews();

    @Inject
    FightRepository mFightRepository;

    private ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(CoachingApplication.getApp().getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContent());
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        mFightRepository.createDefaultAvailableKnock();

        fragmentStack = new Stack<Fragment>();
        fragmentManager = getSupportFragmentManager();
        initViews();
    }

    public void popFragment() {
        if (fragmentStack.size() > 1) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragmentStack.lastElement().onPause();
            ft.remove(fragmentStack.pop());
            fragmentStack.lastElement().onResume();
            ft.show(fragmentStack.lastElement());
            ft.commit();
        } else {
            super.onBackPressed();
        }
    }

    public void pushFragment(Fragment f) {
        if (f != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.add(setContainer(), f);
            if (fragmentStack.size() > 0) {
                Fragment last = fragmentStack.lastElement();
                if (last != null) {
                    last.onPause();
                    ft.hide(last);
                }
            }
            fragmentStack.push(f);
            ft.commit();
        }
    }
}
