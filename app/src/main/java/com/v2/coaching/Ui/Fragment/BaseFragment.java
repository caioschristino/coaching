package com.v2.coaching.Ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v2.coaching.CoachingApplication;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.data.Repo.SessionRepository;
import com.v2.coaching.di.component.DaggerFragmentComponent;
import com.v2.coaching.di.component.FragmentComponent;
import com.v2.coaching.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public abstract class BaseFragment extends Fragment {
    FragmentComponent mFragmentComponent;
    View view;

    abstract void initViews();

    abstract int setContent();

    @Inject
    SessionRepository sessionRepository;

    @Inject
    FightRepository mFightRepository;


    private FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(CoachingApplication.getApp().getComponent())
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(setContent(), null);
        ButterKnife.bind(this, view);
        getFragmentComponent().inject(this);

        initViews();
        return view;
    }
}
