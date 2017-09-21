package com.v2.coaching.View;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.v2.coaching.R;

import butterknife.BindView;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class KnockViewHolder extends BaseViewHolder {
    @BindView(R.id.knock)
    RelativeLayout knock;
    @BindView(R.id.tx_knock)
    TextView title;

    public KnockViewHolder(View view) {
        super(view);
    }

    public void setOnClick(View.OnClickListener l){
        this.knock.setOnClickListener(l);
    }

    public void setModalityTitle(String knock) {
        this.title.setText(knock);
    }
}