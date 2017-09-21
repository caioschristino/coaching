package com.v2.coaching.Ui.Activity;

import com.v2.coaching.CoachingApplication;
import com.v2.coaching.R;
import com.v2.coaching.Ui.Fragment.JiuJitsuFragment;
import com.v2.coaching.data.DataManager;

import javax.inject.Inject;


public class MainActivity extends BaseMain {
    @Override
    void initViews() {
        pushFragment(new JiuJitsuFragment());
    }

    @Override
    int setContainer() {
        return R.id.container_main;
    }

    @Override
    int setContent() {
        return R.layout.aactivity_main;
    }
}
