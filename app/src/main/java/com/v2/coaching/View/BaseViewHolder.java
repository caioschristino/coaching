package com.v2.coaching.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
