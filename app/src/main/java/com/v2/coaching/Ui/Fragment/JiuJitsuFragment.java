package com.v2.coaching.Ui.Fragment;

import android.util.Log;

import com.v2.coaching.Model.AvailableMode;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.data.Repo.SessionRepository;
import com.v2.coaching.data.model.SessionFight;
import com.v2.coaching.R;
import com.v2.coaching.Ui.Component.DashBoardView;
import com.v2.coaching.Utils.KeyBoardUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class JiuJitsuFragment extends BaseFragment
        implements DashBoardView.DashBoardViewCicleLifeListener {
    @BindView(R.id.dashboard_fight)
    DashBoardView mDashBoardView;

    @Override
    void initViews() {
        mDashBoardView.setDashBoardViewCicleLifeListener(this);
        mDashBoardView.startMode(AvailableMode.JIUSITSU);
    }

    @Override
    int setContent() {
        return R.layout.jiu_jitsu_fragment;
    }

    @Override
    public void onStartClicked() {
        KeyBoardUtils.hideKeyboard(getActivity());
    }

    @Override
    public void onPauseClicked() {
        sessionRepository.saveHistory(mDashBoardView.getPlayerA());
        sessionRepository.saveHistory(mDashBoardView.getPlayerB());
    }

    @Override
    public void onRestartClicked() {
        List<SessionFight> items = sessionRepository.getAll();
        Log.e("-->", items.toString());
    }

    @Override
    public FightRepository getRepository(AvailableMode mode) {
        mFightRepository.setMode(mode);
        return mFightRepository;
    }

    @Override
    public SessionRepository getSessionRepository() {
        return sessionRepository;
    }
}
