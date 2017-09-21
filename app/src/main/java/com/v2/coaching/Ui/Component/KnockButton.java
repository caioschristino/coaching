package com.v2.coaching.Ui.Component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v2.coaching.R;
import com.v2.coaching.data.Repo.FightRepository;
import com.v2.coaching.data.model.Knock;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 21/09/17.
 */

public class KnockButton extends RelativeLayout {
    LayoutInflater mInflater;
    @BindView(R.id.title_knock)
    AutoResizeTextView mTitle;

    public interface KnockOptionListener {
        void updatePoint(int player, Knock lastBlow);
    }

    public KnockButton(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public KnockButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public KnockButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        View v = mInflater.inflate(R.layout.knock_button, this, true);
        ButterKnife.bind(this, v);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }
}