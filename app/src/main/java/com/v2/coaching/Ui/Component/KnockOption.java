package com.v2.coaching.Ui.Component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

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
 * Created by CaioSChristino on 04/09/17.
 */

public class KnockOption extends RelativeLayout {
    LayoutInflater mInflater;
    @BindView(R.id.knock_options)
    RadialButtonLayout mRadialMenu;

    public interface KnockOptionListener {
        void updatePoint(int player, Knock lastBlow);
    }

    public KnockOption(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public KnockOption(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public KnockOption(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public void setKnockOptionListener(KnockOptionListener mListener) {
        mRadialMenu.setKnockOptionListener(mListener);
    }

    private void init() {
        View v = mInflater.inflate(R.layout.knock_option_frame, this, true);
        ButterKnife.bind(this, v);
    }

    public void setKnocks(FightRepository repository) {
        mRadialMenu.showOptionButton(repository.getKnocks());
    }

    public void setCurrentPlayer(int mCurrentPlayer) {
        mRadialMenu.setCurrentPlayer(mCurrentPlayer);
    }
}