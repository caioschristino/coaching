package com.v2.coaching.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v2.coaching.Model.AvailableMode;
import com.v2.coaching.data.model.Knock;
import com.v2.coaching.R;
import com.v2.coaching.Utils.AvailableModeUtils;
import com.v2.coaching.View.KnockViewHolder;

import java.util.List;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class KnockAdapter extends RecyclerView.Adapter {
    List<Knock> mKnockList;
    Context mContext;
    AdapterListener mListener;
    AvailableMode mode;

    public interface AdapterListener {
        View.OnClickListener setOnClickListener(Knock knock);
    }

    public KnockAdapter(Context context, AvailableMode mode) {
        this.mContext = context;
        this.mode = mode;
        this.mKnockList = getKnocks(mode);
    }

    public void setmListener(AdapterListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.knock_view_holder, parent, false);
        KnockViewHolder holder = new KnockViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {
        KnockViewHolder holder = (KnockViewHolder) viewHolder;
        Knock knock = mKnockList.get(position);
        holder.setModalityTitle("");
        holder.setOnClick(mListener.setOnClickListener(knock));
    }

    @Override
    public int getItemCount() {
        return this.mKnockList.size();
    }


    private List<Knock> getKnocks(AvailableMode mode) {
        return null;
    }
}