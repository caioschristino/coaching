package com.v2.coaching.Ui.Component;

import android.content.Context;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v2.coaching.Model.AvailableMode;
import com.v2.coaching.R;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.data.Repo.SessionRepository;
import com.v2.coaching.data.model.Knock;
import com.v2.coaching.data.model.SessionFight;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class DashBoardView extends RelativeLayout implements KnockOption.KnockOptionListener {
    boolean isChronometerRunnig = false;
    long stopTime = 0;

    SessionFight playerA;
    SessionFight playerB;
    DashBoardViewCicleLifeListener mListener;
    AvailableMode mLastMode;

    LayoutInflater mInflater;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.start_fight)
    ImageButton mButtonStart;
    @BindView(R.id.restart_fight)
    ImageButton mButtonRestart;
    @BindView(R.id.dashboard)
    View mDashboard;
    @BindView(R.id.input_player_a)
    TextInputLayout mNamePlayerA;
    @BindView(R.id.point_player_a)
    TextView mPointPlayerA;
    @BindView(R.id.input_player_b)
    TextInputLayout mNamePlayerB;
    @BindView(R.id.point_player_b)
    TextView mPointPlayerB;
    @BindView(R.id.container_player_b)
    CardView cardViewB;
    @BindView(R.id.container_player_a)
    CardView cardViewA;
    @BindView(R.id.fight_painel)
    FightPainelView mFightPainelView;

    public interface DashBoardViewCicleLifeListener {
        void onStartClicked();

        void onPauseClicked();

        void onRestartClicked();

        FightRepository getRepository(AvailableMode mode);

        SessionRepository getSessionRepository();
    }

    public DashBoardView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public DashBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public DashBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public void startMode(AvailableMode mode) {
        this.mLastMode = mode;
        this.mFightPainelView.setMode(mListener.getRepository(mode));
    }

    private boolean startSessionFight() {
        String playerA = this.mNamePlayerA.getEditText().getText().toString();
        String playerB = this.mNamePlayerB.getEditText().getText().toString();

        boolean start = true;
        this.mNamePlayerA.setErrorEnabled(false);
        this.mNamePlayerB.setErrorEnabled(false);
        if (playerA == null ||
                playerA.trim().equals("")) {
            this.mNamePlayerA.setError("Player A inválido");
            start = false;
        }

        if (playerB == null ||
                playerB.trim().equals("")) {
            this.mNamePlayerB.setError("Player B inválido");
            start = false;
        }

        if (start) {
            if(stopTime == 0){
                this.playerA = new SessionFight(playerA, FightPainelView.PLAYER_A);
                this.playerB = new SessionFight(playerB, FightPainelView.PLAYER_B);

                this.mNamePlayerA.setEnabled(false);
                this.mNamePlayerB.setEnabled(false);
            }
            showFightPainel();
        }
        return start;
    }

    private void restartSession() {
        isChronometerRunnig = false;
        startMode(mLastMode);

        this.playerA.restarSession();
        this.playerB.restarSession();
        this.mFightPainelView.clear();
        this.mNamePlayerA.setEnabled(true);
        this.mNamePlayerA.getEditText().setText(null);
        this.mNamePlayerB.setEnabled(true);
        this.mNamePlayerB.getEditText().setText(null);
        showFightPainel();
    }

    private void addPoint(int player) {
        if (isChronometerRunnig) {
            if (player == FightPainelView.PLAYER_A) {
                this.playerA.updatePoints(playerA.getLastBlow().getPoint());
                this.mFightPainelView.updateSessionFight(this.playerA);
            } else {
                this.playerB.updatePoints(playerB.getLastBlow().getPoint());
                this.mFightPainelView.updateSessionFight(this.playerB);
            }
        }
    }

    public void setDashBoardViewCicleLifeListener(DashBoardViewCicleLifeListener mListener) {
        this.mListener = mListener;
    }

    private void hideFightPainel() {
        cardViewA.setVisibility(VISIBLE);
        cardViewB.setVisibility(VISIBLE);
        mFightPainelView.setVisibility(GONE);
    }

    public SessionFight getPlayerA() {
        return playerA;
    }

    public SessionFight getPlayerB() {
        return playerB;
    }

    private void showFightPainel() {
        cardViewA.setVisibility(VISIBLE);
        cardViewB.setVisibility(VISIBLE);
        mFightPainelView.setVisibility(GONE);

        this.mNamePlayerB.getEditText().setText(playerB.getName());
        this.mPointPlayerB.setText(null);
        if (this.playerB.getPoints() > 0) {
            this.mPointPlayerB.setText(String.valueOf(this.playerB.getPoints()));
        }
        this.mNamePlayerA.getEditText().setText(playerA.getName());
        this.mPointPlayerA.setText(null);
        if (playerA.getPoints() > 0) {
            this.mPointPlayerA.setText(String.valueOf(this.playerA.getPoints()));
        }
        this.mFightPainelView.updateSessionFight(this.playerB);
        this.mFightPainelView.updateSessionFight(this.playerA);
    }

    private void hidePainelUser() {
        cardViewA.setVisibility(GONE);
        cardViewB.setVisibility(GONE);
        mFightPainelView.setVisibility(VISIBLE);
    }

    private void saveHistory() {
        mListener.getSessionRepository().saveHistory(playerA);
        mListener.getSessionRepository().saveHistory(playerB);
    }

    private void init() {
        View v = mInflater.inflate(R.layout.dashboard_frame, this, true);
        ButterKnife.bind(this, v);

        mFightPainelView.setKnockOptionListener(this);
        mFightPainelView.setOnPause(onPauseRound);
        mButtonStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startSessionFight()) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
                    chronometer.start();
                    mButtonStart.setVisibility(GONE);
                    mButtonRestart.setVisibility(GONE);
                    isChronometerRunnig = true;
                    mListener.onStartClicked();
                    hidePainelUser();
                }
            }
        });

        mButtonRestart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                stopTime = 0;
                chronometer.stop();
                mButtonStart.setVisibility(VISIBLE);
                mButtonRestart.setVisibility(VISIBLE);
                mListener.onRestartClicked();
                saveHistory();
                restartSession();
            }
        });
    }

    @Override
    public void updatePoint(int player, Knock knock) {
        if (player == FightPainelView.PLAYER_A) {
            this.playerA.updateLastBlow(knock);
        } else {
            this.playerB.updateLastBlow(knock);
        }
        addPoint(player);
    }

    public OnClickListener onPauseRound = new OnClickListener() {
        @Override
        public void onClick(View v) {
            stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
            chronometer.stop();
            mButtonStart.setVisibility(VISIBLE);
            mButtonRestart.setVisibility(VISIBLE);
            isChronometerRunnig = false;
            hideFightPainel();
            mListener.onPauseClicked();
        }
    };
}