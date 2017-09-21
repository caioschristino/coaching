package com.v2.coaching.Ui.Component;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.FrameLayout;

import com.v2.coaching.R;
import com.v2.coaching.data.model.Knock;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CaioSChristino on 06/09/17.
 */

public class RadialButtonLayout extends FrameLayout {
    final static long DURATION_SHORT = 400;
    KnockOption.KnockOptionListener mListener;
    int mCurrentPlayer;
    List<Knock> mKnocks;

    @BindView(R.id.btn_main)
    KnockButton btnMain;
    @BindView(R.id.btn_one)
    KnockButton mButtonOne;
    @BindView(R.id.btn_two)
    KnockButton mButtonTwo;
    @BindView(R.id.btn_three)
    KnockButton mButtonThree;
    @BindView(R.id.btn_four)
    KnockButton mButtonFour;
    @BindView(R.id.btn_five)
    KnockButton mButtonFive;
    @BindView(R.id.btn_six)
    KnockButton mButtonSix;
    @BindView(R.id.btn_seven)
    KnockButton mButtonSeven;
    @BindView(R.id.btn_eight)
    KnockButton mButtonEight;

    public RadialButtonLayout(final Context context) {
        this(context, null);
    }

    public RadialButtonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_radial_buttons, this);
        if (isInEditMode()) {
            //
        } else {
            ButterKnife.bind(this);
        }

        showTop(mButtonOne, 1, 300);
        showTop(mButtonTwo, 2, 300);
        showTop(mButtonThree, 3, 300);
        showTop(mButtonFour, 4, 300);
        showTop(mButtonFive, 5, 300);
        showBotton(mButtonSix, 6, 300);
        showBotton(mButtonSeven, 7, 300);
        showBotton(mButtonEight, 8, 300);
    }

    @OnClick(R.id.btn_main)
    public void onMainButtonClicked(final View mBtnMain) {

    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four,
            R.id.btn_five, R.id.btn_six, R.id.btn_seven, R.id.btn_eight})
    public void onButtonClicked(final View btn) {
        Knock knock = null;
        switch (btn.getId()) {
            case R.id.btn_one:
                knock = mKnocks.get(0);
                break;
            case R.id.btn_two:
                knock = mKnocks.get(1);
                break;
            case R.id.btn_three:
                knock = mKnocks.get(2);
                break;
            case R.id.btn_four:
                knock = mKnocks.get(3);
                break;
            case R.id.btn_five:
                knock = mKnocks.get(4);
                break;
            case R.id.btn_six:
                knock = mKnocks.get(5);
                break;
            case R.id.btn_seven:
                knock = mKnocks.get(6);
                break;
            case R.id.btn_eight:
                knock = mKnocks.get(7);
                break;
        }

        if (knock != null) {
            mListener.updatePoint(mCurrentPlayer, knock);
            btn.playSoundEffect(SoundEffectConstants.CLICK);
        }
    }

    private final void hide(final View child) {
        child.animate()
                .setDuration(DURATION_SHORT)
                .translationX(0)
                .translationY(0)
                .start();
    }

    public void setKnockOptionListener(KnockOption.KnockOptionListener mListener) {
        this.mListener = mListener;
    }

    private final void showTop(final View child, final int position, final int radius) {
        float angleDeg = 180.f;
        int dist = radius;
        switch (position) {
            case 1:
                angleDeg += 0.f;
                break;
            case 2:
                angleDeg += 45.f;
                break;
            case 3:
                angleDeg += 90.f;
                break;
            case 4:
                angleDeg += 135.f;
                break;
            case 5:
                angleDeg += 180.f;
                break;
        }

        final float angleRad = (float) (angleDeg * Math.PI / 180.f);
        final Float x = dist * (float) Math.cos(angleRad);
        final Float y = dist * (float) Math.sin(angleRad);
        child.animate()
                .setDuration(DURATION_SHORT)
                .translationX(x)
                .translationY(y)
                .start();
    }

    private final void showBotton(final View child, final int position, final int radius) {
        float angleDeg = -180.f;
        int dist = radius;
        switch (position) {
            case 6:
                angleDeg += 45.f;
                break;
            case 7:
                angleDeg += 90.f;
                break;
            case 8:
                angleDeg += 135.f;
                break;
        }

        final float angleRad = (float) (angleDeg * Math.PI / -180.f);
        final Float x = dist * (float) Math.cos(angleRad);
        final Float y = dist * (float) Math.sin(angleRad);
        child.animate()
                .setDuration(DURATION_SHORT)
                .translationX(x)
                .translationY(y)
                .start();
    }

    public void showOptionButton(List<Knock> knocks) {
        mKnocks = knocks;
        for (int i = 0; i < mKnocks.size(); i++) {
            switch (i) {
                case 1:
                    mButtonOne.setVisibility(VISIBLE);
                    mButtonOne.setTitle(mKnocks.get(i).getName());
                    break;
                case 2:
                    mButtonTwo.setVisibility(VISIBLE);
                    mButtonTwo.setTitle(mKnocks.get(i).getName());
                    break;
                case 3:
                    mButtonThree.setVisibility(VISIBLE);
                    mButtonThree.setTitle(mKnocks.get(i).getName());
                    break;
                case 4:
                    mButtonFour.setVisibility(VISIBLE);
                    mButtonFour.setTitle(mKnocks.get(i).getName());
                    break;
                case 5:
                    mButtonFive.setVisibility(VISIBLE);
                    mButtonFive.setTitle(mKnocks.get(i).getName());
                    break;
                case 6:
                    mButtonSix.setVisibility(VISIBLE);
                    mButtonSix.setTitle(mKnocks.get(i).getName());
                    break;
                case 7:
                    mButtonSeven.setVisibility(VISIBLE);
                    mButtonSeven.setTitle(mKnocks.get(i).getName());
                    break;
                case 8:
                    mButtonEight.setVisibility(VISIBLE);
                    mButtonEight.setTitle(mKnocks.get(i).getName());
                    break;
            }
        }
    }

    public void setCurrentPlayer(int currentPlayer) {
        mCurrentPlayer = currentPlayer;
    }
}