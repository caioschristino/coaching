package com.v2.coaching.View;

import android.view.View;
import android.widget.TextView;

import com.v2.coaching.R;

import butterknife.BindView;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class HomeViewHolder extends BaseViewHolder {
    @BindView(R.id.tx_modality)
    TextView modality;

    public HomeViewHolder(View view) {
        super(view);
    }

    public void setModalityTitle(String modality) {
        this.modality.setText(modality);
    }
}