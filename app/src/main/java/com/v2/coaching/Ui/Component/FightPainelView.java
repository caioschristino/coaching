package com.v2.coaching.Ui.Component;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v2.coaching.Model.AvailableMode;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.data.model.Knock;
import com.v2.coaching.data.model.SessionFight;
import com.v2.coaching.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 06/09/17.
 */

public class FightPainelView extends RelativeLayout {
    public static int PLAYER_A = 0;
    public static int PLAYER_B = 1;
    LayoutInflater mInflater;

    @BindView(R.id.title_player_b)
    TextView mNamePlayerB;
    @BindView(R.id.point_player_b)
    TextView mPointPlayerB;
    @BindView(R.id.title_player_a)
    TextView mNamePlayerA;
    @BindView(R.id.point_player_a)
    TextView mPointPlayerA;
    @BindView(R.id.btn_pause)
    FloatingActionButton mPauseButton;
    @BindView(R.id.knock_option_player_a)
    KnockOption mKnockOptionPlayerA;
    @BindView(R.id.knock_option_player_b)
    KnockOption mKnockOptionPlayerB;

    public FightPainelView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public FightPainelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public FightPainelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        View v = mInflater.inflate(R.layout.fightpainel_frame, this, true);
        ButterKnife.bind(this, v);
    }

    public void setMode(FightRepository repository) {
        this.mKnockOptionPlayerA.setKnocks(repository);
        this.mKnockOptionPlayerB.setKnocks(repository);
    }

    public void setKnockOptionListener(KnockOption.KnockOptionListener mListener) {
        this.mKnockOptionPlayerA.setCurrentPlayer(PLAYER_A);
        this.mKnockOptionPlayerA.setKnockOptionListener(mListener);
        this.mKnockOptionPlayerB.setCurrentPlayer(PLAYER_B);
        this.mKnockOptionPlayerB.setKnockOptionListener(mListener);
    }

    public void updateSessionFight(SessionFight sessionFight) {
        if (sessionFight.getPlayer() == FightPainelView.PLAYER_A) {
            this.mNamePlayerA.setText(sessionFight.getName());
            this.mPointPlayerA.setText(String.valueOf(sessionFight.getPoints()));
        } else {
            this.mNamePlayerB.setText(sessionFight.getName());
            this.mPointPlayerB.setText(String.valueOf(sessionFight.getPoints()));
        }
    }

    public void restartSession(SessionFight playerA, SessionFight playerB) {
        this.mNamePlayerA.setText(playerA.getName());
        this.mPointPlayerA.setText(String.valueOf(playerA.getPoints()));
        this.mNamePlayerB.setText(playerB.getName());
        this.mPointPlayerB.setText(String.valueOf(playerB.getPoints()));
    }

    public void setOnPause(OnClickListener onPauseRound) {
        mPauseButton.setOnClickListener(onPauseRound);
    }

    public void clear() {
        SessionFight clear  = new SessionFight();
        this.mNamePlayerA.setText(clear.getName());
        this.mPointPlayerA.setText(String.valueOf(clear.getPoints()));
        this.mNamePlayerB.setText(clear.getName());
        this.mPointPlayerB.setText(String.valueOf(clear.getPoints()));
    }
}